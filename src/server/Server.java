package server;

import java.io.IOException;
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
import server.helper.MoveSender;
import server.helper.ServerSocketAccepter;
import Battle.Battle;
import dataStore.MongoDB;
import dataStore.Move;
import dataStore.NetworkPlayer;
import dataStore.Pokemon;

public class Server implements Runnable{
	public static final int COMMUNICATIONPORT = 5555;
	public static final int CHATPORT = 4444;
//	public static final int OBJECTPORT = 3456;
	private ChatServer chatServer;
	private Battle first;
	private Battle second;
	
	private ServerSocket ssChat;
	private ServerSocket ssComm;
	private ArrayList<NetworkPlayer> players;
	private static HashMap<String, ArrayList<Pokemon>> pokemonMap;
	private int numPlayers = 4;
	private int battleOneP1 = 1;
	private int battleOneP2 = -1;
	private int battleTwoP1 = -1;
	private int battleTwoP2 = -1;
	private DataBaseAccess dba = null;
	
	public Server(){
		dba = new DataBaseAccess();
		System.out.println("TEST");
		dba.start();
//		chatServer = new ChatServer(CHATPORT);	//TODO
		players = new ArrayList<NetworkPlayer>();
//		ArrayList<Socket> chatSockets = new ArrayList<Socket>();TODO
		ArrayList<Socket> communicationSockets = new ArrayList<Socket>();
		System.out.println("Waiting for clients...");
		try {
//			ssChat = new ServerSocket(CHATPORT);TODO
			ssComm = new ServerSocket(COMMUNICATIONPORT);
			
			ServerSocketAccepter comm = new ServerSocketAccepter(ssComm, numPlayers);
//			ServerSocketAccepter chat = new ServerSocketAccepter(ssChat, numPlayers);TODO
			
			ForkJoinPool pool = new ForkJoinPool(2);
			pool.execute(comm);
//			pool.execute(chat);TODO
			
			pool.shutdown();
			while(!pool.isTerminated())
				Thread.yield();
			
			for (int i = 0; i < numPlayers; ++i)
				players.add(new NetworkPlayer());
			System.out.println("AM I DONE WITH ACCEPTING ");
			
//			chatSockets.sort(new SocketSort());TODO
			communicationSockets.sort(new SocketSort());
			System.out.println("DONE SORTING");
			for (int i = 0; i < numPlayers; ++i){
				NetworkPlayer p = players.get(i);
				//p.setChatSocket(chatSockets.get(i));TODO
				p.setCommSocket(communicationSockets.get(i));
				p.setBr();
				p.setPw();
				System.out.println("HERE?");
				//chatServer.listen(p.getChatSocket());TODO
				System.out.println("FINISHED LISTENING");
			}
			
			try {
				dba.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pokemonMap = dba.getMap();
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
			System.out.println("giving moves");
			getPlayersAndGiveMovesAndSendPokemonForSwitch();
			System.out.println("sending pokemon");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done getting input from other players");
	}
	
	@Override
	public void run(){
		System.out.println("at the beginning of run");
		generateBattlePairs();
		createBattles();
		boolean result1 = first.join();
		boolean result2 = second.join();
		System.out.println("after joins");

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
	
	private void getPlayersAndGiveMovesAndSendPokemonForSwitch(){
		ArrayList<MoveSender> senders = new ArrayList<MoveSender>();
		for (NetworkPlayer p : players){
			senders.add(new MoveSender(p, pokemonMap));
			System.out.println("giving move to player: " + p.getCommSocket().getInetAddress());
//			String input = null;
			
//			try {
//				input = p.getBr().readLine(); //read MOVES # from attackselection of client
//			System.out.println("inputserver.java " + input);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			if (input == null){
//				System.out.println("BROKEDEDEDED");
//				return;
//			}
//			if (input.contains("MOVES")){
//				int position = Integer.parseInt(input.substring(input.length() - 1));
			
			//System.out.println(p.getPokemonList().get(0).getMoveList().size() + "EMPTY MOVES?");
//			System.out.println(p.getPokemonList().get(0).getName());
//			System.out.println(p.getPokemonList().get(1).getName());
//			System.out.println(p.getPokemonList().get(2).getName());
			
			//System.out.println(p.getPokemonList().get(0).getName());
		}
		ForkJoinPool pool = new ForkJoinPool(numPlayers);
		for (int i = 0; i < numPlayers; ++i){
			pool.execute(senders.get(i));
		}
		pool.shutdown();
		while(!pool.isTerminated())
			Thread.yield();
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
			System.out.println("YOU HAVE AN outOR!");
		
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
				map = new HashMap<String, ArrayList<Pokemon>>(accessor.getPokemon());
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
