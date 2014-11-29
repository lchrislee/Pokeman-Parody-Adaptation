package dataStore;
import java.io.Serializable;
import java.util.Vector;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Pokemon implements Serializable{
	private boolean conscious = true; //default
	private int attack;
	private int defense;
	private int speed;//stat that is used to decide which pokemon can attack first
	private int health;//the total amount of health points the pokemon currently has
	private int maxHealth;
	private double rarity; /*the level of rarity (the higher level of rarity, the stronger the pokemon will be and less likely it will be to appear, more difficult to catch)*/
	private int level;
	
	private Vector<Move> moveList;
	//private Vector<ImageIcon> spriteList = new Vector<ImageIcon>();
	private ImageIcon leftSprite;
	private ImageIcon rightSprite;
	private String name;
	private String[] FileNameArray;	

	public Pokemon (String name,String[] FileNameArray,int a, int d, int sp, int mh, double rarity, int lvl, Vector<Move> moves){//just assign the parameters to private vars
		this.name = name;
		this.FileNameArray = FileNameArray;
		this.attack = a;
		this.defense = d;
		this.speed = sp;
		this.maxHealth = mh;
		this.health = this.maxHealth;//only at the start
		this.rarity = rarity;
		this.level = lvl;
		this.moveList = moves;
		createSprites();//store left and right cuz we dunno which player we'll be.
	}

	public Pokemon(){
		
	}
	
	
	private void createSprites(){
		for(int i=0;i<FileNameArray.length;++i){
			ImageIcon icon = new ImageIcon(FileNameArray[i]);
		//	spriteList.add(icon);
			if(FileNameArray[i].contains("left"))
				leftSprite = icon;
			
			if(FileNameArray[i].contains("right"))
				rightSprite = icon;
		}
	}
	
	public boolean isConscious(){
		return this.conscious;
	}
		
	public void faint(){//set conscious to false 	
		this.conscious = false;
	}
	
	public void setHealth(int h){// �C this method will be used when a pokemon��s health must be adjusted (potion is used the pokemon is attacked)
		if(health <= 0)
			this.health = 0;
		else
			this.health = h;
		
		if(this.health == 0)
			this.faint();
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
	
	

	public int getMaxHealth() {
		return maxHealth;
	}

	
	public Vector<Move> getMoveList() {
		return moveList;
	}

	
	public ImageIcon getLeftSprite() {
		return leftSprite;
	}
	
	public ImageIcon getRightSprite(){
		return rightSprite;
	}

	
	public String getName(){
		return name;
	}

	public String[] getFileNameArray() {
		return this.FileNameArray;
	}
	
}
