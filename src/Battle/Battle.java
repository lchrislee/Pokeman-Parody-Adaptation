package Battle;

import java.util.concurrent.RecursiveTask;

import dataStore.NetworkPlayer;
import dataStore.Player;
import dataStore.Pokemon;

public class Battle extends RecursiveTask<Boolean> {
	String input;
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
		b.input = "Attack_Tackle|50~P1 P2";
		System.out.println(b.compute());
		b.input = "Attack_Tackle|50~P2 P1";
		System.out.println(b.compute());
	
		System.out.println("<<<<<<<");
		b.input = "Sw_1-Swagasaur";
		System.out.println(b.compute());
		b.input = "Sw_2-Pikachu";
		System.out.println(b.compute());
		System.out.println("<<<<<<<");
		
		b.input = "Surrender_P1";
		System.out.println(b.compute());
		b.input = "Surrender_P2";
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
			while (!player1Selected && !player2Selected)
				Thread.yield();
			
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
			
			System.out.println("Player 1 quit: " + player1Quit);
			System.out.println("Player 2 quit: " + player2Quit);
			System.out.println("Who Quit first: " + firstPlayerToQuit);
			System.out.println("Player 1 selected: " + player1Selected);
			System.out.println("Player 2 selected: " + player2Selected);
			setTurnVariables();
		}
		return output;
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
	
	private void interpretAttack(String input){
		System.out.println(input);
		int moveNameStartIndex = input.indexOf("_") + 1;
		int moveNameEndIndex = input.indexOf("|");
		int powerStartIndex = input.indexOf("|") + 1;
		int powerEndIndex = input.indexOf("~");
		int sourcePlayerIndex = input.indexOf("~") + 2;
		int receivePlayerIndex = input.indexOf(" ") + 2;
		
		String moveName = input.substring(moveNameStartIndex, moveNameEndIndex);
		int power = Integer.parseInt(input.substring(powerStartIndex, powerEndIndex));
		int damage = calculateDamage(power);
		System.out.println("Player " + input.charAt(sourcePlayerIndex) + " did " + damage + " with " + moveName + " to Player " + input.charAt(receivePlayerIndex)); 
		if (input.charAt(sourcePlayerIndex) == '1')
			player1Selected = true;
		else
			player2Selected = true;
	}
	
	private int calculateDamage(int power){
		return (2 * 10 + 10)/150 * (39/28) * power + 2;
	}
	
	private void interpretSurrender(String input){
		System.out.println(input);
		int playerIndex = input.indexOf("_P") + 2;
		System.out.println("Player " + input.charAt(playerIndex) + " wants to quit");
		if (input.charAt(playerIndex) == '1')
			player1Quit = true;
		else
			player2Quit = true;
		if (player1Quit && !player2Quit){
			firstPlayerToQuit = 1;
			player1Selected = true;
		}else if (player2Quit && !player1Quit){
			firstPlayerToQuit = 2;
			player2Selected = true;
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
	
	private synchronized void interpretSwap(String input){//string will be number-pokemonname... eg: 1-Lickister  or 2-Beetwo
		
		
			//will run only if nobody's quit yet
				int digitIndex = input.indexOf("_")+1;
				int playerIndex = Integer.parseInt(input.substring(digitIndex,digitIndex+1));
				
				String pokemonName = input.substring(input.indexOf("-")+1,input.length());
				
				if (playerIndex == 1){
					player1Switch = true;
					player2Switch = false;
					firstPlayerToSwitch = PLAYERONE;
					player1Selected = true;
					player2Selected = false;
				}
				
				else{
					player2Switch = true;
					player1Switch = false;
					player2Selected = true;
					player1Selected = false;
					firstPlayerToSwitch = PLAYERTWO;
				}
				
				System.out.println("Player " + firstPlayerToSwitch + " wants to switch to " + pokemonName);
				//can reset turn order later
			}
			
}
