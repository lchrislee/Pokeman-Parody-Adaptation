package server.helper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class ServerSocketAccepter extends RecursiveTask<ArrayList<Socket>> {
	private static final long serialVersionUID = 7258935807647797804L;
	private ServerSocket ss;
	private int numPlayers;
	
	public ServerSocketAccepter(ServerSocket s, int n){
		ss = s;
		numPlayers = n;
	}
	
	@Override
	public ArrayList<Socket> compute() {
		ArrayList<Socket> sockets = new ArrayList<Socket>();
		for (int i = 0; i < numPlayers; ++i){
			Socket communicationSocketInput;
			try {
				communicationSocketInput = ss.accept();
				System.out.println(communicationSocketInput.toString() + " CONNECTED");
				sockets.add(communicationSocketInput);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return sockets;
	}

}