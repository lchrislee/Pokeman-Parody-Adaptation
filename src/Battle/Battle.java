package Battle;

import java.util.concurrent.RecursiveTask;

public class Battle extends RecursiveTask<Boolean> {
	String input;
	private boolean player1Quit;
	private boolean player2Quit;
	private int firstPlayerToQuit;
	private boolean player1Selected;
	private boolean player2Selected;
	
	public Battle(){
		setTurnVariables();
	}
	
	public static void main(String[] args) {
		Battle b = new Battle();
		b.input = "Attack_Tackle|50~P1 P2";
		System.out.println(b.compute());
		b.input = "Attack_Tackle|50~P2 P1";
		System.out.println(b.compute());
		b.input = "Surrender_P1";
		System.out.println(b.compute());
		b.input = "Surrender_P2";
		System.out.println(b.compute());
	}

	@Override
	protected Boolean compute() {
		Boolean output = null;
		if (input.charAt(0) == 'A'){
			attack(input);
			output = true;
		}else if (input.indexOf("Su") == 0){
			surrender(input);
			output = false;
		}
		System.out.println("Player 1 quit: " + player1Quit);
		System.out.println("Player 2 quit: " + player2Quit);
		System.out.println("Who Quit first: " + firstPlayerToQuit);
		System.out.println("Player 1 selected: " + player1Selected);
		System.out.println("Player 2 selected: " + player2Selected);
		setTurnVariables();
		return output;
	}
	
	private void setTurnVariables(){
		player1Quit = false;
		player2Quit = false;
		firstPlayerToQuit = 0;
		player1Selected = false;
		player2Selected = false;
	}
	
	private void attack(String input){
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
	
	private synchronized void surrender(String input){
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
}
