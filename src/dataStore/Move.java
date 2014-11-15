package dataStore;

public class Move {
	
	private int id;//so we can take name and damage value from database
	private int damage;//maybe store this..dunno yet

	
	public Move(int damage,int id){	//4 moves x 20 pokemon = 80 moves -.-
		this.damage = damage;
		this.id = id;
	}
	//doing a move just 
	
	public int getId(){
		return this.id;
	}

	public int getDamage(){
		return this.damage;
	}
}
