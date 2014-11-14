import java.util.ArrayList;
import java.util.Random;


public class Pokemon {
	private boolean conscious = true; //default
	private int attack;
	private int defense;
	private int speed;//stat that is used to decide which pokemon can attack first
	private int health;//the total amount of health points the pokemon currently has
	private int maxHealth;
	private double rarity; /*the level of rarity (the higher level of rarity, the stronger the pokemon will be and less likely it will be to appear, more difficult to catch)*/
	private int level;
	private ArrayList<Move>moveList;
	
	private static Random rand = new Random();//to generate either 0 or 1 for determining order and stuff
	

	public Pokemon (int a, int d, int sp, int mh, double rarity, int lvl, ArrayList<Move> moves){//just assign the parameters to private vars
		this.attack = a;
		this.defense = d;
		this.speed = sp;
		this.maxHealth = mh;
		this.rarity = rarity;
		this.level = lvl;
		this.moveList = moves;
	}
	
	public boolean isConscious(){
		return this.conscious;
	}
		
	public boolean goFirst(Pokemon p){//-comparing this with another pokemon¡¯s speed, rarity, and if the first two are equal a random number to see who attacks first
		if(this.getSpeed() > p.getSpeed())
			return true;
		
		else if(this.getSpeed() == p.getSpeed()){
			if(this.getRarity() > p.getRarity()){
				return true;
			}
			
			else if(this.getRarity() == p.getRarity()){
				int x = rand.nextInt(1);
				if(x == 1)//if we get one, we go first, otherwise good luck bruh
					return true;
				
				return false;
			}
			
			return false;
			
		}
		
		return false;
	}

	public void faint(){//set conscious to false 	
		this.conscious = false;
	}
	
	public void Attack(Pokemon p, Move m){//- this method takes in another pokemon. When attack is called, it will adjust the other pokemon¡¯s health through an algorithm that takes in this pokemon¡¯s attack and the other pokemon¡¯s defense
	}
	
	public void setHealth(int h){// ¨C this method will be used when a pokemon¡¯s health must be adjusted (potion is used the pokemon is attacked)
		this.health = h;
	}

	public int getAttack(){
		return this.attack;
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

}
