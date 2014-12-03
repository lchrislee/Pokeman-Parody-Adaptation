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
	
	public MoveSender(NetworkPlayer np, PrintWriter p, Vector<Move> m, HashMap<String, ArrayList<Pokemon>> map){
		pw = p;
		moves = m;
		n = np;
		pokemonMap = map;
	}
	
	@Override
	public Void compute() {
		n.readPlayer(pokemonMap); //GET PLAYER
		System.out.println("START PRINTING STUFF HERE");
		for (Pokemon poke : n.getPokemonList()){
			System.out.println(poke);
		}
		
		//NEW SECTION BELOW
		String output = "";
		int i = 0;
		for (Move m : moves){
			output += m.toString();
//			System.out.println(m.toString() + " MOVE TO STRING ");
			if (i != 3)
				output += "=";
			++i;
		}
//		}
		System.out.println(output);
		pw.println(output); //OUTPUT MOVE OF FIRST POKEMON
		pw.flush();
		
		//NEW SECTION BELOW
		output = "";
		int counter = 1;
		for (Pokemon pokes : n.getPokemonList()){
			output += pokes.getName() + "=" + pokes.getLevel() + "+" + pokes.getHealth() + "_" + pokes.getMaxHealth();
			if (counter++ < 3)
				output += ":";
		}
		pw.println(output);
		pw.flush(); //OUTPUT POKEMON STATS FOR SWITCHING
		return null;
	}

}
