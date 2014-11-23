package dataStore;
import java.util.Vector;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
//get current pokemon

public class Pokemon {
	private boolean conscious = true; //default
	private int attack;
	private int defense;
	private int speed;//stat that is used to decide which pokemon can attack first
	private int health;//the total amount of health points the pokemon currently has
	private int maxHealth;
	private double rarity; /*the level of rarity (the higher level of rarity, the stronger the pokemon will be and less likely it will be to appear, more difficult to catch)*/
	private int level;
	private Vector<Move> moveList;
	private ImageIcon sprite;
	private String name;
	private String filename;	
	


	public Pokemon (String name,String filename,int a, int d, int sp, int mh, double rarity, int lvl, Vector<Move> moves,ImageIcon image){//just assign the parameters to private vars
		this.name = name;
		this.filename = filename;
		this.attack = a;
		this.defense = d;
		this.speed = sp;
		this.maxHealth = mh;
		this.rarity = rarity;
		this.level = lvl;
		this.moveList = moves;
		this.sprite = image;
	}
	
	public boolean isConscious(){
		return this.conscious;
	}
		
	public void faint(){//set conscious to false 	
		this.conscious = false;
	}
	
	public void setHealth(int h){// ¨C this method will be used when a pokemon¡¯s health must be adjusted (potion is used the pokemon is attacked)
		this.health = h;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getDefense(){
		return this.defense;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public double getRarity(){
		return this.rarity;
	}
	
	public Vector<Move> getMoves(){
		return this.moveList;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	
	public Vector<Move> getMoveList() {
		return moveList;
	}

	
	public ImageIcon getSprite() {
		return sprite;
	}

	
	public String getName() {
		return name;
	}

	public String getFilename() {
		return filename;
	}
	
}
