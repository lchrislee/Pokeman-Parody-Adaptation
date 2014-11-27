package dataStore;


import java.util.ArrayList;

public class Datamon {

	private DatamonStats datamonStats;
	private String Name;
	private ArrayList<Move> moves;
	
	public Datamon() {

	}
	

	public DatamonStats getDs() {
		return datamonStats;
	}

	public void setDs(DatamonStats ds) {
		this.datamonStats = ds;
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