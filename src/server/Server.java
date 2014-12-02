package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

import javax.swing.JOptionPane;

import server.chatSystem.ChatServer;
import Battle.Battle;
import dataStore.MongoDB;
import dataStore.Move;
import dataStore.NetworkPlayer;
import dataStore.Pokemon;

public class Server implements Runnable{
	public static final int COMMUNICATIONPORT = 5555;
	public static final int CHATPORT = 4444;
	public static final int OBJECTPORT = 3456;
	private ChatServer chatServer;
	private Battle first;
	private Battle second;
	
	private ServerSocket ssChat;
	private ServerSocket ssComm;
	private ArrayList<NetworkPlayer> players;
	private static HashMap<String, ArrayList<Pokemon>> pokemonMap;
	int battleOneP1 = 1;
	int battleOneP2 = -1;
	int battleTwoP1 = -1;
	int battleTwoP2 = -1;
	private DataBaseAccess dba = null;
	
	public Server(){
		dba = new DataBaseAccess();
		System.out.println("TEST");
		dba.start();
		chatServer = new ChatServer(CHATPORT);
		players = new ArrayList<NetworkPlayer>();
		ArrayList<Socket> chatSockets = new ArrayList<Socket>();
		ArrayList<Socket> communicationSockets = new ArrayList<Socket>();
		System.out.println("Waiting for clients...");
		try {
			ssChat = new ServerSocket(CHATPORT);
			ssComm = new ServerSocket(COMMUNICATIONPORT);
			int numPlayers = 4;
			
			for (int i = 0; i < numPlayers; ++i){
				Socket communicationSocketInput = ssComm.accept();
				
				
				System.out.println(communicationSocketInput.toString() + " CONNECTED TO SERVER");
				communicationSockets.add(communicationSocketInput);

				Socket chatSocketInput = ssChat.accept();
				System.out.println(chatSocketInput.toString() + " CONNECTED TO CHAT");
				chatSockets.add(chatSocketInput);
				players.add(new NetworkPlayer());
			}
			System.out.println("AM I DONE WITH ACCEPTING ");
			
			chatSockets.sort(new SocketSort());
			communicationSockets.sort(new SocketSort());
			
			for (int i = 0; i < numPlayers; ++i){
				NetworkPlayer p = players.get(i);
				p.setChatSocket(chatSockets.get(i));
				p.setCommSocket(communicationSockets.get(i));
				p.setBr();
				p.setPw();
				chatServer.listen(p.getChatSocket());
				System.out.println("FINISHED LISTENING");
			}
			
			System.out.println("WAITING TO ACCEPT PLAYERS");
			/*
			ServerSocket ssObject = new ServerSocket(OBJECTPORT);
			for(int j=0;j<numPlayers;++j){
				NetworkPlayer p = players.get(j);
				System.out.println("SADHFILDSAJFJ");
//				p.getBr().close();
				Socket s = ssObject.accept();
				p.setOOS(new ObjectOutputStream(s.getOutputStream()));
				p.setOIS(new ObjectInputStream(s.getInputStream()));
				System.out.println("SET OOS AND OIS");
//				p.setBr();
				System.out.println(p.getBr().readLine()); //CHECK
				System.out.println("PRINTING TO TELL TO SEND DATA");
				p.getPw().println("asdf");
				p.getPw().flush();
				System.out.println("GETTING PLAYERS");
//				p.getBr().close();
				System.out.println("GOT: " + p.getBr().readLine());
				getPlayers();
//				p.getBr().readLine();
				p.getPw().println("CHECK AGAIN");
				p.getPw().flush();
				
				System.out.println("nEED TO GET INPUT NOW");
				if (p.getOIS() == null)
					System.out.println("OIS IS NULL");
				if (p.getOOS() == null)
					System.out.println("OOS IS NULL");
			}
			ssObject.close();*/
			giveMoves();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done getting input from other players");
	}
	
	@Override
	public void run(){
		try {
			dba.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pokemonMap = dba.getMap();
		generateBattlePairs();
		createBattles();
		boolean result1 = first.join();
		boolean result2 = second.join();
		
		int b1winner = (result1 ? battleOneP1 : battleOneP2);
		int b1loser = (result1 ? battleOneP2 : battleOneP1);
		int b2winner = (result2 ? battleTwoP1 : battleTwoP2);
		int b2loser = (result2 ? battleTwoP2 : battleTwoP1);
		
		signalResults(b1winner,b1loser, b2winner, b2loser);
		
		battleOneP1 = b1winner;
		battleOneP2 = b2winner;
		battleTwoP1 = b1loser;
		battleTwoP2 = b2loser;
		
		createBattles();
		boolean result3 = first.join();
		boolean result4 = first.join();
		
		int b3winner = (result3 ? battleOneP1 : battleOneP2);
		int b3loser = (result3 ? battleOneP2 : battleOneP1);
		int b4winner = (result4 ? battleTwoP1 : battleTwoP2);
		int b4loser = (result4 ? battleTwoP2 : battleTwoP1);
		
		signalResults(b3winner, b3loser, b4winner, b4loser);
		
		signalFinalResults(b3winner, b3loser, b4winner, b4loser);
	}
	
	private void getPlayers(){
		for (NetworkPlayer n : players){
//			n.readPlayer();
			n.readPlayer(pokemonMap);

		}
	}
	
	private void giveMoves(){
		for (NetworkPlayer p : players){
			String input = null;
			String output = "";
			try {
				input = p.getBr().readLine(); //read MOVES # from attackselection of client
			System.out.println("inputserver.java " + input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (input == null){
				System.out.println("BROKEDEDEDED");
				return;
			}
			if (input.contains("MOVES")){
				int position = Integer.parseInt(input.substring(input.length() - 1));
				int i = 0;
				for (Move m : p.getPokemonList().get(position).getMoveList()){
					output += m.toString();
					if (i != 3)
						output += "?";
					++i;
				}
			}
			System.out.println(output);
			p.getPw().println(output);

		}
	}
	
	private void createBattles(){
		System.out.println((battleOneP1 -1) + " BATTLE1 p1 " + (battleOneP2-1) + " BATTLE1p2 ");
		first = new Battle(players.get(battleOneP1 - 1), players.get(battleOneP2 - 1));
		second = new Battle(players.get(battleTwoP1 - 1), players.get(battleTwoP2 - 1));
		ForkJoinPool pool = new ForkJoinPool(2);
		pool.execute(first);
		pool.execute(second);
	}
	
	private void signalResults(int b1winner, int b1loser, int b2winner, int b2loser){
		
	}
	
	private void signalFinalResults(int first, int second, int third, int last){
		
	}
	
	private void generateBattlePairs(){
		battleOneP2 = (int)(Math.random() * 3 + 2);
		switch(battleOneP2){
			case 2:
				battleTwoP1 = 3;
				battleTwoP2 = 4;
				break;
			case 3:
				battleTwoP1 = 2;
				battleTwoP2 = 4;
				break;
			case 4:
				battleTwoP1 = 2;
				battleTwoP2 = 3;
				break;
		}
	}
	
	public static void main(String[] args) {
		String ipAddress = "";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (ipAddress == "")
			System.out.println("YOU HAVE AN ERROR!");
		
		JOptionPane.showMessageDialog(null, "Please tell your clients the following IP address: \n" + ipAddress, "Your IP Address", JOptionPane.INFORMATION_MESSAGE, null);
		
		Server s = new Server();
		
		Thread t = new Thread(s);
		t.run();
	}
	
	private class SocketSort implements Comparator<Socket>{
		@Override
		public int compare(Socket o1, Socket o2) {
			return o1.getLocalAddress().toString().compareTo(o2.getLocalAddress().toString());
		}
	}
	
	private class DataBaseAccess extends Thread{
		private HashMap<String, ArrayList<Pokemon>> map;
		public DataBaseAccess(){
			super();
		}
		
		@Override
		public void run(){
			try {
				System.out.println("TEST");//comment this out and get hte mongodb stuff back up
				MongoDB accessor = new MongoDB();
				map = accessor.getPokemon();
				accessor.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		public HashMap<String, ArrayList<Pokemon>> getMap(){
			return map;
		}
	}
}
