package server;

import java.io.IOException;
import java.io.ObjectInputStream;
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
import dataStore.NetworkPlayer;
import dataStore.Pokemon;

public class Server implements Runnable{
	public static final int COMMUNICATIONPORT = 5555;
	public static final int CHATPORT = 4444;
	
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
	
	
	public Server(){
		chatServer = new ChatServer(CHATPORT);
		players = new ArrayList<NetworkPlayer>();
		ArrayList<Socket> chatSockets = new ArrayList<Socket>();
		ArrayList<Socket> communicationSockets = new ArrayList<Socket>();
		System.out.println("Waiting for clients...");
		try {
			ssChat = new ServerSocket(CHATPORT);
			ssComm = new ServerSocket(COMMUNICATIONPORT);
			
			for (int i = 0; i < 1; ++i){
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
			
			for (int i = 0; i < 1; ++i){
				NetworkPlayer p = players.get(i);
				p.setChatSocket(chatSockets.get(i));
				p.setCommSocket(communicationSockets.get(i));
				p.setBr();
				p.setPw();
				
				//p.setOIS();
				p.setOOS();
				
				chatServer.listen(p.getChatSocket());
				System.out.println("FINISHED LISTENING");
			}
			
			for(int j=0;j<1;++j){
				NetworkPlayer p = players.get(j);
				p.setOIS();
				System.out.println("nEED TO GET INPUT NOW");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done getting input from other players");
	}
	
	@Override
	public void run(){
	
		getPlayers();/*
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
		
		signalFinalResults(b3winner, b3loser, b4winner, b4loser);*/
	}
	
	private void getPlayers(){
		for (NetworkPlayer n : players){
//			n.readPlayer();
			n.readPlayer(pokemonMap);
		}
	}
	
	private void createBattles(){
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
		DataBaseAccess dba = s.new DataBaseAccess();
		dba.start();
		
		Thread t = new Thread(s);
		try {
			dba.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pokemonMap = dba.getMap();
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
			//try {
				System.out.println("TEST");//comment this out and get hte mongodb stuff back up
				//MongoDB accessor = new MongoDB();
				//map = accessor.getPokemon();
			//} catch (UnknownHostException e) {
				//e.printStackTrace();
			//}
		}
		public HashMap<String, ArrayList<Pokemon>> getMap(){
			return map;
		}
	}
}
