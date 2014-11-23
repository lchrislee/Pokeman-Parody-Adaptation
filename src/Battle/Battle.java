package Battle;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import dataStore.Move;
import dataStore.Player;

public class Battle implements Runnable {//needs a lot of work but i'll commit for now
	private Player p1;
	private Player p2;
	
	public Battle(Player p1,Player p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void run(){
		/*just some psuedocode for how it's going to run
		 * 
		 * 
		 * 
		 * 
		 */
	}
	
	public static void main(String[]args){//just adding a main function to test some stuff
		//Player p1 = 
		//Player p2 = 
		/*Pokemon lickister = new Pokemon("Lickister","res/Pokemon_String filename,int a, int d, int sp, int mh, double rarity, int lvl, ArrayList<Move> moves,ImageIcon image)
		*/
		
		Thread game = new Thread();
		game.start();
	}
}
