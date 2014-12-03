package server.helper;

import java.io.PrintWriter;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

import dataStore.Move;

public class MoveSender extends RecursiveTask<Void> {
	private static final long serialVersionUID = 6973748963957940788L;

	private PrintWriter pw;
	private Vector<Move> moves;
	
	public MoveSender(PrintWriter p, Vector<Move> m){
		pw = p;
		moves = m;
	}
	
	@Override
	public Void compute() {
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
		pw.println(output);
		pw.flush();
		return null;
	}

}
