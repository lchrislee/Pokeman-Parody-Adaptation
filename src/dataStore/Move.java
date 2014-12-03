package dataStore;

import java.io.IOException;
import java.io.Serializable;

public class Move implements Serializable {
	private static final long serialVersionUID = 6103104890327381735L;
	//	private int id;//so we can take name and damage value from database
	private Integer damage;//maybe store this..dunno yet
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
		damage = 0;
		name = "";
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
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		System.out.println("Reading in name");
		this.name = (String) stream.readObject();
		System.out.println("Reading in damage");
		this.damage = stream.readInt();
	}
	
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
		System.out.println("Writing out: " + name);
		stream.writeObject(name);
		System.out.println("Writing out: " + damage);
		stream.writeInt(damage);
	}
	
	public String toString(){
		return name + "+" + damage;
	}
}
