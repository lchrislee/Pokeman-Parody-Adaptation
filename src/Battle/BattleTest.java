package Battle;
//work on player and making test cases

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStore.Move;
import dataStore.Player;
import dataStore.Pokemon;

public class BattleTest {//test cases for the battle system
	
	
	public static void main(String[]args){
		
		int attack = 50;
		int defense = 20;
		int speed = 30;
		int mh = 100;
		double rarity = 1.2;
		int level = 5;
		
		Vector<Move>moveList = new Vector<Move>();
		moveList.add(new Move(50,1, "Tackle"));//does 50 damage, dunno what move it is yet
		moveList.add(new Move(-1,1, "Fail"));
		
		String[]imageNames = {"res/Pokemon_sprites/Lickister_left_tr.png","res/Pokemon_sprites/Lickister_right_tr.png"};
		String[]imageNamesTwo = {"res/Pokemon_sprites/Magikuna_left_tr.png","res/Pokemon_sprites/Magikuna_right_tr.png"};
		
		Pokemon p = new Pokemon("Lickister",imageNames,attack,defense,speed,mh,rarity, level,moveList);
		Pokemon poke = new Pokemon("Magikuna",imageNamesTwo,attack,defense,speed,mh,rarity, level,moveList);
		
		for(int i=0;i<p.getMoveList().size();++i){
			System.out.println(p.getMoveList().get(i).getDamage());
		}
		
		Vector<Pokemon>pokemonList = new Vector<Pokemon>();
		pokemonList.add(p);
		System.out.println(pokemonList.size() + " POKEMON LIST SIZE ");
		System.out.println(p.getAttack() + " ATK " + p.getDefense() + " DEF " + p.getFileNameArray().length + " FNAME " + p.getLevel() + " LVL ");
		System.out.println(p.getHealth()+ " H " + p.getMaxHealth() + " MH " + p.getRarity() + " RARITY " + p.getSpeed() + " SPEED ");
			
		
		JFrame frame = new JFrame("TEST");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		//panel.add(new JLabel("HI"));
		panel.add(new JLabel(p.getRightSprite()));
		panel.add(new JLabel(poke.getLeftSprite()));//does my sprite exist?
		
		//make a pokemon first and test its methods
		System.out.println(p.isConscious() + " ALIVE? " + p.getHealth() + " MY HEALTH ");//should be alive
		p.setHealth(0);
		System.out.println(p.isConscious() + " ALIVE? " + p.getHealth() + " MY HEALTH " + p.getMaxHealth() + " MAX HEALTH ");//should be dead
	
		
		//testing some player functions
		Player p1 = new Player();
		Vector<ImageIcon> playerIconList = new Vector<ImageIcon>();
		playerIconList.add(new ImageIcon("res/Character_sprites/Dolan_normal.png"));
		p1.setSpriteList(playerIconList);
		p1.setPokemonList(pokemonList);//player 1 now has lickister
		
		if(p1.getCurrentPokemon()== null){
			System.out.println("NOT YET");
		}
		
		p1.choosePokemon();
		System.out.println(p1.getCurrentPokemon().getName() + "FIRST POKE ");
		
		
		p1.setEnemyPokemon(poke);//player 1's enemy pokemon is magikuna
		System.out.println(p1.getEnemyPokemon().getName() + " ENEMY NAME ");
		panel.add(new JLabel(p1.getCurrentSprite()));
	
		System.out.println(p1.hasQuit() + "STILL IN IT ?");
		p1.quit();
		System.out.println(p1.hasQuit() + "STILL IN IT ?");
		
	
	
	}
}
