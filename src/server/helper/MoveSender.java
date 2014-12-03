package server.helper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

import dataStore.Move;
import dataStore.NetworkPlayer;
import dataStore.Pokemon;

public class MoveSender extends RecursiveTask<Void> {
	private static final long serialVersionUID = 6973748963957940788L;

	private NetworkPlayer n;
	private PrintWriter pw;
	private Vector<Move> moves;
	private HashMap<String, ArrayList<Pokemon>> pokemonMap;
	
	public MoveSender(NetworkPlayer np, HashMap<String, ArrayList<Pokemon>> map){
		n = np;
		pw = n.getPw();
		moves = n.getPokemonList().get(0).getMoveList();
		pokemonMap = map;
	}
	
	@Override
	public Void compute() {
		System.out.println("WAITING TO ACCEPT PLAYERS");
		n.readPlayer(pokemonMap); //GET PLAYER
//		System.out.println("START PRINTING STUFF HERE");
		for (Pokemon poke : n.getPokemonList()){
			System.out.println(poke);
		}
		
		//NEW SECTION BELOW
		System.out.println("giving moves");
		String output = "";
		int i = 0;
		for (Move m : moves){
			output += m.toString();
//			System.out.println(m.toString() + " MOVE TO STRING ");
			if (i != 3)
				output += "=";
			++i;
		}
//		System.out.println("MOVES IN MOVESENDER" + output);
		pw.println(output); //OUTPUT MOVE OF FIRST POKEMON
		pw.flush();
		
		//NEW SECTION BELOW
		System.out.println("sending pokemon");
		output = "";
		int counter = 1;
		for (Pokemon pokes : n.getPokemonList()){
			output += pokes.getName() + "=" + pokes.getLevel() + "+" + pokes.getHealth() + "_" + pokes.getMaxHealth();
			if (counter++ < 3)
				output += ":";
		}
		pw.println(output);
		System.out.println(output + "OUTPUT OF MOVESENDER FOR STATS");
		pw.flush(); //OUTPUT POKEMON STATS FOR SWITCHING
		return null;
	}

}
