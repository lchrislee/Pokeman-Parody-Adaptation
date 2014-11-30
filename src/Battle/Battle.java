package Battle;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

import dataStore.NetworkPlayer;
import dataStore.Player;
import dataStore.Pokemon;

public class Battle extends RecursiveTask<Boolean> {
	String input;
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
		
	
		
		//make a dummy player and pokemon
		//Pokemon metapod = new Pokemon("Met")
		//public Pokemon (String name,String[] FileNameArray,int a, int d, int sp, int mh, double rarity, int lvl, Vector<Move> moves)
		
		//Pokemon p = new Pokemon("Lickister",null,attack,defense,speed,mh,rarity, level,moveList);
		//Pokemon poke = new Pokemon("Magikuna",imageNamesTwo,attack,defense,speed,mh,rarity, level,moveList);
		
		Battle b = new Battle();
		b.input = "At_Tackle|50~P1 P2";
		System.out.println(b.compute());
		b.input = "At_Tackle|50~P2 P1";
		System.out.println(b.compute());
	
		System.out.println("<<<<<<<");
		b.input = "Sw_1-Swagasaur";
		System.out.println(b.compute());
		b.input = "Sw_2-Pikachu";
		System.out.println(b.compute());
		System.out.println("<<<<<<<");
		
		b.input = "Su_P1";
		System.out.println(b.compute());
		b.input = "Su_P2";
		System.out.println(b.compute());
	
		System.out.println(">>>>>>");
		b.input = "Sw_1-Swagasaur";
		System.out.println(b.compute());
		b.input = "Sw_2-Pikachu";
		System.out.println(b.compute());
		System.out.println(">>>>>>");
	
	}

	@Override
	protected Boolean compute() {
		Boolean output = null;
		while (!winnerDetermined){
			//while (!player1Selected && !player2Selected)
			//Thread.yield();
			
			p1Input = "";
			p2Input = "";
			
			try {
				p1Input = p1.getBr().readLine();
				p2Input = p2.getBr().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			
			System.out.println("Player 1 quit: " + player1Quit);
			System.out.println("Player 2 quit: " + player2Quit);
			System.out.println("Who Quit first: " + firstPlayerToQuit);
			System.out.println("Player 1 selected: " + player1Selected);
			System.out.println("Player 2 selected: " + player2Selected);
			setTurnVariables();
		}
		return output;
	}
	
	private void parse() {
		String p1message = p1Input.substring(0, 2);
		String p2message = p2Input.substring(0, 2);
		
		
		if(p1message.equals("Su") || p2message.equals("Su")){
			interpretSurrender();
			return;
		}
		
		if(p1message.equals("Sw") || p2message.equals("Sw")){
			interpretSwap();	
			if(p1message.equals(p2message))//if both players swap
				return;
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
				doDamage(p2);
			}
			else{
				doDamage(p2);
				doDamage(p1);
			}
		}
		
		else if(p1message.equals("At"))
			doDamage(p1);
		
		else doDamage(p2);
		
		System.out.println(input);

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
			String out = "%s attacked with %s and did %d damage";
			String.format(out, p.getName(), moveName, damage);
			p1.getPw().write(out);
			p2.getPw().write(out);
			p1.getPw().flush();
			p2.getPw().flush();
		}
		else{
			int moveNameStartIndex = p2Input.indexOf("_") + 1;
			int moveNameEndIndex = p2Input.indexOf("|");
			int powerStartIndex = p2Input.indexOf("|") + 1;
			int powerEndIndex = p2Input.length();
			String moveName = p2Input.substring(moveNameStartIndex, moveNameEndIndex);
			int power = Integer.parseInt(p2Input.substring(powerStartIndex, powerEndIndex));
			int damage = calculateDamage(power);
			String out = "%s attacked with %s and did %d damage";
			String.format(out, p.getName(), moveName, damage);
			p1.getPw().write(out);
			p2.getPw().write(out);
			p1.getPw().flush();
			p2.getPw().flush();
			
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
