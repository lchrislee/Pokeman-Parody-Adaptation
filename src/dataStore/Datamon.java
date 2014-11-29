package dataStore;


import java.util.ArrayList;

public class Datamon {

	//private DatamonStats datamonStats;
	private int Defense;
	private int Attack;
	private int Speed;
	private int HP;
	private int Level;
	private String Name;
	private ArrayList<Move> moves;
	
	public Datamon() {

	}

	public int getDefense() {
		return Defense;
	}


	public void setDefense(int defense) {
		Defense = defense;
	}


	public int getAttack() {
		return Attack;
	}


	public void setAttack(int attack) {
		Attack = attack;
	}


	public int getSpeed() {
		return Speed;
	}


	public void setSpeed(int speed) {
		Speed = speed;
	}


	public int getHP() {
		return HP;
	}


	public void setHP(int hP) {
		HP = hP;
	}


	public int getLevel() {
		return Level;
	}


	public void setLevel(int level) {
		Level = level;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	public ArrayList<Move> getMoves() {
		return moves;
	}


	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	
}