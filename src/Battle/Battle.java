package Battle;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

import dataStore.NetworkPlayer;
import dataStore.Player;
import dataStore.Pokemon;

public class Battle extends RecursiveTask<Boolean> {
	//String input;
	String p1Input;
	String p2Input;
	private boolean player1Quit;
	private boolean player2Quit;
	private boolean player1Selected;
	private boolean player2Selected;
	private boolean player1Switch;
	private boolean player2Switch;
	private boolean winnerDetermined;
	private int firstPlayerToQuit;
	private int firstPlayerToSwitch;
	private final int PLAYERONE = 1;
	private final int PLAYERTWO = 2;
	
	private NetworkPlayer p1;
	private NetworkPlayer p2;
	
	
	public Battle(){
		winnerDetermined = false;
		setTurnVariables();
	}
	
	public Battle(NetworkPlayer p1, NetworkPlayer p2){
		this.p1 = p1;
		this.p2 = p2;
		winnerDetermined = false;
		setTurnVariables();
	}
	
	public static void main(String[] args) {
		Player p1 = new Player();
		Player p2 = new Player();
		
		Battle b = new Battle();
	}

	@Override
	protected Boolean compute() {
		Boolean output = null;
		while (!winnerDetermined){

			p1Input = "";
			p2Input = "";
			
			try {
				p1Input = p1.getBr().readLine();
				p2Input = p2.getBr().readLine();
			} catch (IOException e) {
				e.printStackTrace();
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

		
		return output;
	}
	
	private boolean checkForWinner(int player) {
		if(player == PLAYERONE){
			int count = 0;
			for(int i = 0; i < p1.getPokemonList().size(); i++){
				if(p1.getPokemonList().get(i).getHealth() == 0)
					count++;
			}
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
			interpretSurrender();
			return;
		}
		
		if(p1message.equals("Sw") || p2message.equals("Sw")){
			interpretSwap();	
			//if(p1message.equals(p2message))//if both players swap
				//return;
		}
	}

	private void setTurnVariables(){
		player1Quit = false;
		player2Quit = false;
		firstPlayerToQuit = 0;
		player1Selected = false;
		player2Selected = false;
	}
	
	private void doSwitch(int player){
		if(player == 2){
			String fromClient = "";
			try {
				fromClient = p2.getBr().readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int index = Integer.parseInt(fromClient.substring(fromClient.length()));
			String swapMessage = "swap_%s?%s|%d!%d:%d";
			String.format(swapMessage, p2.getName(), p2.getPokemonList().get(index),
			p2.getPokemonList().get(index).getLevel(), 
			p2.getPokemonList().get(index).getHealth(), 
			p2.getPokemonList().get(index).getMaxHealth());
	
			p2.setCurrentPokemonIndex(index);
			
			p1.getPw().write(swapMessage);
			p2.getPw().write(swapMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			
			String swapDisplayMessage = "%s sent out %s to battle";
			String.format(swapDisplayMessage, p2.getName(), p2.getPokemonList().get(index));
			
			p1.getPw().write(swapDisplayMessage);
			p2.getPw().write(swapDisplayMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			
			try {
				p1.getBr().readLine();
				p2.getBr().readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else{
			String fromClient = "";
			try {
				fromClient = p1.getBr().readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int index = Integer.parseInt(fromClient.substring(fromClient.length()));
			String swapMessage = "swap_%s?%s|%d!%d:%d";
			String.format(swapMessage, p1.getName(), p1.getPokemonList().get(index),
			p1.getPokemonList().get(index).getLevel(), 
			p1.getPokemonList().get(index).getHealth(), 
			p1.getPokemonList().get(index).getMaxHealth());
	
			p1.setCurrentPokemonIndex(index);
			
			p1.getPw().write(swapMessage);
			p2.getPw().write(swapMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			
			String swapDisplayMessage = "%s sent out %s to battle";
			String.format(swapDisplayMessage, p1.getName(), p1.getPokemonList().get(index));
			
			p1.getPw().write(swapDisplayMessage);
			p2.getPw().write(swapDisplayMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			
			try {
				p1.getBr().readLine();
				p2.getBr().readLine();
			} catch (IOException e) {
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
		
		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		if(p1message.equals("At") && p2message.equals("At")){
			if(turnOrder() == 1){
				doDamage(p1);
				//checkForFaint(PLAYERTWO);
				doDamage(p2);
				//checkForFaint(PLAYERONE);
			}
			else{
				doDamage(p2);
				//checkForFaint(PLAYERONE);
				doDamage(p1);
				//checkForFaint(PLAYERTWO);
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
		
	private void doDamage(NetworkPlayer p) {
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
			
			String initialMessage = "hit_" + p2.getName() + "_" + remain;
			p1.getPw().write(initialMessage);
			p2.getPw().write(initialMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			//checkForFaint(PLAYERTWO);
			if(remain == 0){
				if(checkForWinner(PLAYERTWO)){
					winnerDetermined = true;
					return;
				}
				else
					doSwitch(PLAYERTWO);
			}
			else{
			
				String out = "%s attacked with %s and did %d damage";
				String.format(out, p.getName(), moveName, damage);
				p1.getPw().write(out);
				p2.getPw().write(out);
				p1.getPw().flush();
				p2.getPw().flush();
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
			
			String initialMessage = "hit_" + p1.getName() + "_" + remain;
			p1.getPw().write(initialMessage);
			p2.getPw().write(initialMessage);
			p1.getPw().flush();
			p2.getPw().flush();
			if(remain == 0){
				if(checkForWinner(PLAYERONE)){
					winnerDetermined = true;
					return;
				}
				else
					doSwitch(PLAYERONE);
			
			}else{
				String out = "%s attacked with %s and did %d damage";
				String.format(out, p.getName(), moveName, damage);
				p1.getPw().write(out);
				p2.getPw().write(out);
				p1.getPw().flush();
				p2.getPw().flush();
			}
			
		}
		

	}

	private int calculateDamage(int power){
		return (2 * 10 + 10)/150 * (39/28) * power + 2;
	}
	
	private void interpretSurrender(){

		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		if(p1message.equals("Su") && p2message.equals("Su")){

			Random r = new Random();
			int i = r.nextInt();
		
			if(i % 2 == 0){
				firstPlayerToQuit = 1;
			}
			else firstPlayerToQuit = 2;
			return;
		}
		
		else if(p1message.equals("Su")){
			firstPlayerToQuit = 1;
			return;
		}
		else if(p2message.equals("Su")){
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
		
	}
			
}
