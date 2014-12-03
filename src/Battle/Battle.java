package Battle;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

import dataStore.NetworkPlayer;
import dataStore.Player;
//import dataStore.Player;
import dataStore.Pokemon;

//need to fix some null pointer exceptions


public class Battle extends RecursiveTask<Boolean> {
	private static final long serialVersionUID = -5076483035782993247L;
	//String input;
	String p1Input;
	String p2Input;
	private boolean player1Switch;
	private boolean player2Switch;
	private boolean winnerDetermined;
	private int firstPlayerToQuit;
	private int firstPlayerToSwitch;
	private final int PLAYERONE = 1;
	private final int PLAYERTWO = 2;
	String winner;
	private NetworkPlayer p1;
	private NetworkPlayer p2;
	
	
	public Battle(){
		winnerDetermined = false;
		setTurnVariables();
	}
	
	public Battle(NetworkPlayer p1, NetworkPlayer p2){
		System.out.println("INSIDE BATTLE CONSTRUCTOR");
		this.p1 = p1;
		this.p2 = p2;
		System.out.println(this.p1);
		winnerDetermined = false;
		setTurnVariables();
		p1.getPw().println("GO");
		p1.getPw().flush();
		p2.getPw().println("GO");
		p2.getPw().flush();
	}
	
	public static void main(String[] args) {
		Player player = new Player();
		Player player2 = new Player();
		
		
		NetworkPlayer p1 = new NetworkPlayer(player);
		NetworkPlayer p2 = new NetworkPlayer(player2);
		
		
		
		Battle b = new Battle(p1, p2);
		
		
		b.compute();
	}

	@Override
	protected Boolean compute() {
		//each pla
		Pokemon mePoke = p1.getCurrentPokemon();
		Pokemon enemyPoke = p2.getCurrentPokemon();
		
		String me = "swap"+p1.getName()+"?"+mePoke.getName()+"|"+mePoke.getLevel()+"!"+mePoke.getHealth()+":"+mePoke.getMaxHealth();
		String enemy = "swap"+p2.getName()+"?"+enemyPoke.getName()+"|"+enemyPoke.getLevel()+"!"+enemyPoke.getHealth()+":"+enemyPoke.getMaxHealth();
		
		p1.getPw().println(me);
		p1.getPw().println(enemy);
		p1.getPw().flush();
		
		p2.getPw().println(enemy);
		p2.getPw().println(me);
		p2.getPw().flush();
		
		
		Boolean output = null;
		while (!winnerDetermined){

			p1Input = "";
			p2Input = "";
			
			try {
				p1Input = p1.getBr().readLine();
				p2Input = p2.getBr().readLine();

			} catch (Exception e) {
				//e.printStackTrace();

				//p1Input = "At_Tackle|6";
				//p2Input = "At_HURT|9";
				
			}
			
			parse();
				
			if (firstPlayerToQuit != 0)
				return endBattle(firstPlayerToQuit);
			
			if (firstPlayerToSwitch == 1){
				doSwitch(PLAYERONE);
				if (player2Switch)
					doSwitch(PLAYERTWO);
				continue;
			}else if (firstPlayerToSwitch == 2){
				doSwitch(PLAYERTWO);
				if (player1Switch)
					doSwitch(PLAYERONE);
				continue;
			}
			
			interpretAttack();			
			//checkForWinner(PLAYERTWO);//might go somewhere else			
			setTurnVariables();
			
			p1.getPw().write("DONE");
			p2.getPw().write("DONE");
			p1.getPw().flush();
			p2.getPw().flush();
			
		}
		
		p1.getPw().write("END");
		p2.getPw().write("END");
		p1.getPw().flush();
		p2.getPw().flush();
		System.out.println("The winner is: " + winner);
		
		return output;
	}
	
	private boolean checkForWinner(int player) {//checking to see if player passed in has pokemon left
		if(player == PLAYERONE){
			int count = 0;
			for(int i = 0; i < p1.getPokemonList().size(); i++){
				if(p1.getPokemonList().get(i).getHealth() == 0)
					count++;
			}
			System.out.println(count + "COUNT");
			if(count == 3)
				return true;
			else return false;
		}
		
		else if(player == PLAYERTWO){
			int count = 0;
			for(int i = 0; i < p2.getPokemonList().size(); i++){
				if(p2.getPokemonList().get(i).getHealth() == 0)
					count++;
			}
			System.out.println(count + "");
			if(count == 3)
				return true;
			else return false;
		}
		
		return false;
	}

	/*private boolean checkForFaint(int player) {
		
		
		
		return false;
	}*/

	private void parse() { //only parses for surrender and switch, attack must be done later
		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		
		if(p1message.equals("Su") || p2message.equals("Su")){
			System.out.println(p1message + " P1messagesu ");
			System.out.println(p2message + " P2messagesu ");
			interpretSurrender();
			return;
		}
		
		if(p1message.equals("Sw") || p2message.equals("Sw")){
			System.out.println(p1message + " P1messagesw ");
			System.out.println(p2message + " P2messagesw ");
			interpretSwap();	
		}
	}

	private void setTurnVariables(){
		firstPlayerToQuit = 0;
		firstPlayerToSwitch = 0;
		player1Switch = false;
		player2Switch = false;
	}
	
	private void doSwitch(int player){
		if(player == 2){
			String fromClient = "";
			System.out.println(p2.getCurrentPokemon().getName());
			if (p2.getCurrentPokemon().getHealth() == 0){ //added check to see if already fainted or not
				try {
					fromClient = p2.getBr().readLine();
					//fromClient = "Sw_1";
					//if(p2.getPokemonList().get(1).getHealth() == 0){
						//System.out.println("INHERE");
						//fromClient = "Sw_2";
					//}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int index = Integer.parseInt(fromClient.substring(fromClient.length() - 1)); //added -1
			String swapMessage = "swap_%s?%s|%d!%d:%d";
			swapMessage = String.format(swapMessage, p2.getName(), p2.getPokemonList().get(index),
			p2.getPokemonList().get(index).getLevel(), 
			p2.getPokemonList().get(index).getHealth(), 
			p2.getPokemonList().get(index).getMaxHealth());
	
			p2.setCurrentPokemonIndex(index);
			
			p1.getPw().write(swapMessage);
			p2.getPw().write(swapMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(swapMessage);

			
			String swapDisplayMessage = "%s sent out %s to battle";
			swapDisplayMessage = String.format(swapDisplayMessage, p2.getName(), p2.getPokemonList().get(index).getName());
			
			p1.getPw().write(swapDisplayMessage);
			p2.getPw().write(swapDisplayMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			
			System.out.println(swapDisplayMessage);

			try {
				p1.getBr().readLine();
				p2.getBr().readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else{
			String fromClient = "";
			if (p1.getCurrentPokemon().getHealth() == 0){
				try {
					fromClient = p1.getBr().readLine();
				} catch (Exception e) {
				//	fromClient = "Sw_1";
					//if(p1.getPokemonList().get(1).getHealth() == 0){
						//fromClient = "Sw_2";
				//	}
					//e.printStackTrace();
				}
			}
			int index = Integer.parseInt(fromClient.substring(fromClient.length() - 1));
			String swapMessage = "swap_%s?%s|%d!%d:%d";
			swapMessage = String.format(swapMessage, p1.getName(), p1.getPokemonList().get(index),
			p1.getPokemonList().get(index).getLevel(), 
			p1.getPokemonList().get(index).getHealth(), 
			p1.getPokemonList().get(index).getMaxHealth());
	
			p1.setCurrentPokemonIndex(index);
			
			p1.getPw().write(swapMessage);
			p2.getPw().write(swapMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(swapMessage);

			
			String swapDisplayMessage = "%s sent out %s to battle";
			swapDisplayMessage = String.format(swapDisplayMessage, p1.getName(), p1.getPokemonList().get(index));
			
			p1.getPw().write(swapDisplayMessage);
			p2.getPw().write(swapDisplayMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(swapDisplayMessage);

			
			try {
				p1.getBr().readLine();
				p2.getBr().readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private Boolean endBattle(int playerLoser){
		if (playerLoser == 1)
			return false;
		else
			return true;
	}
	
	private void interpretAttack(){
		try {
			Thread.sleep(90);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		if(p1message.equals("At") && p2message.equals("At")){
			if(turnOrder() == 1){
				if(!doDamage(p1)){
					doDamage(p2);
				}
				
				//checkForFaint(PLAYERONE);
			}
			else{
				if(!doDamage(p2)){
					doDamage(p1);
				}
				
			}
		}
		
		else if(p1message.equals("At")){
			doDamage(p1);
			//checkForFaint(PLAYERTWO);
		}
		
		else{
			doDamage(p2);
			//checkForFaint(PLAYERONE);
		}
		
		//System.out.println(input);
	}
		
	private boolean doDamage(NetworkPlayer p) {//returns true if winner is deterrmined or pokemon fainted
		if(p.equals(p1)){
			int moveNameStartIndex = p1Input.indexOf("_") + 1;
			int moveNameEndIndex = p1Input.indexOf("|");
			int powerStartIndex = p1Input.indexOf("|") + 1;
			int powerEndIndex = p1Input.length();
			String moveName = p1Input.substring(moveNameStartIndex, moveNameEndIndex);
			int power = Integer.parseInt(p1Input.substring(powerStartIndex, powerEndIndex));
			int damage = calculateDamage(power);
			int remain = p2.getCurrentPokemon().getHealth() - damage;
			if(remain < 0)
				remain = 0;
			
			p2.getCurrentPokemon().setHealth(remain);
			
			String initialMessage = "hit_" + PLAYERTWO + "_" + remain;
			p1.getPw().write(initialMessage);
			p2.getPw().write(initialMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			//checkForFaint(PLAYERTWO);
			System.out.println(initialMessage);

			
			String out = "%s attacked with %s and did %d damage"; //moved this statement chunk from the if statement below
			out = String.format(out, p.getName(), moveName, damage);
			p1.getPw().write(out);
			p2.getPw().write(out);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(out);

			
			if(remain == 0){ //removed the else statement
				String message = "%s fainted!"; //added this
				message = String.format(message, p2.getCurrentPokemon().getName());
				p1.getPw().println(message);
				p2.getPw().println(message);
				p1.getPw().flush();
				p2.getPw().flush();
				System.out.println(message);
				if(checkForWinner(PLAYERTWO)){
					winnerDetermined = true;
					winner = "PLAYERONE";
					return true;
				}
				else
					doSwitch(PLAYERTWO);
				return true;
			}
		}
		else{
			int moveNameStartIndex = p2Input.indexOf("_") + 1;
			int moveNameEndIndex = p2Input.indexOf("|");
			int powerStartIndex = p2Input.indexOf("|") + 1;
			int powerEndIndex = p2Input.length();
			String moveName = p2Input.substring(moveNameStartIndex, moveNameEndIndex);
			int power = Integer.parseInt(p2Input.substring(powerStartIndex, powerEndIndex));
			int damage = calculateDamage(power);
			int remain = p1.getCurrentPokemon().getHealth() - damage;
			if(remain < 0)
				remain = 0;
			
			p1.getCurrentPokemon().setHealth(remain);
			
			String initialMessage = "hit_" + PLAYERONE + "_" + remain;
			
			p1.getPw().write(initialMessage);
			p2.getPw().write(initialMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(initialMessage);

			
			String out = "%s attacked with %s and did %d damage";
			out = String.format(out, p.getName(), moveName, damage);
			p1.getPw().write(out);
			p2.getPw().write(out);
			p1.getPw().flush();
			p2.getPw().flush();
			System.out.println(out);

			if(remain == 0){
				if(checkForWinner(PLAYERONE)){
					winnerDetermined = true;
					winner = "PLAYERTWO";
					return true;
				}
				else
					doSwitch(PLAYERONE);
				return true;
			}
			
		}
		return false;
		

	}

	private int calculateDamage(int power){
		return (2 * 10 + 10)/150 * (39/28) * power + 2;
	}
	
	private void interpretSurrender(){

		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		if(p1message.equals("Su") && p2message.equals("Su")){//if both player surrender, randomly select a player to lose

			Random r = new Random();
			int i = r.nextInt();
		
			
			if(i % 2 == 0){
				firstPlayerToQuit = 1;
			}
			else firstPlayerToQuit = 2;
			return;
		}
		
		else if(p1message.equals("Su")){
			System.out.println("P1QUIT");
			firstPlayerToQuit = 1;
			return;
		}
		
		else if(p2message.equals("Su")){
			System.out.println("P2QUIT");
			firstPlayerToQuit = 2;
			return;
		}
		
		
	}
	
	private int turnOrder(){//returns who goes first
		Pokemon p1Poke = p1.getCurrentPokemon();
		Pokemon p2Poke = p2.getCurrentPokemon();
	
		
		if(p1Poke.getSpeed() > p2Poke.getSpeed())
			return PLAYERONE;
	
		else if(p1Poke.getSpeed() == p2Poke.getSpeed()){
			
			if(p1Poke.getRarity() > p2Poke.getRarity())
				return PLAYERONE;
			
			else if(p1Poke.getRarity() == p2Poke.getRarity()){
				int num = (int)Math.random()%2;
				if(num == 0)
					return PLAYERTWO;
				
				return PLAYERONE;
			}
			
			return PLAYERTWO;
		}
				
		return PLAYERTWO;
	}
	
	private synchronized void interpretSwap(){//string will be number-pokemonname... eg: 1-Lickister  or 2-Beetwo	
		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		System.out.println(p1message + " P1 SWAP MESSAGE ");
		System.out.println(p2message + " P2 SWAP MESSAGE ");
		
		if(p1message.equals("Sw") && p2message.equals("Sw")){
			if(turnOrder() == 1){
				firstPlayerToSwitch = 1;
				player2Switch = true;
			}
			else{
				firstPlayerToSwitch = 2;
				player1Switch = true;
			}
		}
		else if(p1message.equals("Sw")){
			firstPlayerToSwitch = 1;
			player1Switch = true;
		}
		else if(p2message.equals("Sw")){
			firstPlayerToSwitch = 2;
			player2Switch = true;
		}
		System.out.println(firstPlayerToSwitch + " FIRST PLAYER TO SWITCH " + player1Switch + " P1SWITCH " + player2Switch + " P2SWITCH ");
	}
			
}
