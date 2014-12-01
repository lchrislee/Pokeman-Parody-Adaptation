package dataStore;

import java.io.Serializable;

public class Move implements Serializable {
	private static final long serialVersionUID = 8303834611226519467L;
//	private int id;//so we can take name and damage value from database
	private int damage;//maybe store this..dunno yet
	private String name;
	
	public Move(int damage,/*int id, */String n){	//4 moves x 20 pokemon = 80 moves -.-
		if(damage < 0)
			this.damage = 0;
		else
			this.damage = damage;
		
//		this.id = id;
		this.name = n;
	}
	//doing a move just 
	
	public Move(){
		
	}
	
	public void setDamage(int i){
		damage = i;
	}
	
	public void setName(String n){
		name = n;
	}
	
//	public int getId(){
//		return this.id;
//	}
	

	public int getDamage(){
		return this.damage;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return name + " " + damage;
	}
}
