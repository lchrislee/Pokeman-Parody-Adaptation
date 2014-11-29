package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;

import javax.swing.JOptionPane;

import server.chatSystem.ChatServer;
import Battle.Battle;
import dataStore.NetworkPlayer;

public class Server implements Runnable{
	public static final int COMMUNICATIONPORT = 5555;
	public static final int CHATPORT = 4444;
	
	private ChatServer chatServer;
	private Battle first;
	private Battle second;
	
	private ServerSocket ssChat;
	private ServerSocket ssComm;
	private ArrayList<NetworkPlayer> players;
	int battleOneP1 = 1;
	int battleOneP2 = -1;
	int battleTwoP1 = -1;
	int battleTwoP2 = -1;
	
	
	public Server(){
		chatServer = new ChatServer(CHATPORT);
		players = new ArrayList<NetworkPlayer>();
		ArrayList<Socket> chatSockets = new ArrayList<Socket>();
		ArrayList<Socket> communicationSockets = new ArrayList<Socket>();
		System.out.println("Getting input from other players");
//		try {
//			Thread.sleep(2000);//TODO replace this with actual server stuff
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("Waiting for clients...");
		try {
			ssChat = new ServerSocket(CHATPORT);
			ssComm = new ServerSocket(COMMUNICATIONPORT);
			
			for (int i = 0; i < 4; ++i){
				Socket communicationSocketInput = ssComm.accept();
				System.out.println(communicationSocketInput.toString() + " CONNECTED TO SERVER");
				communicationSockets.add(communicationSocketInput);

				Socket chatSocketInput = ssChat.accept();
				System.out.println(chatSocketInput.toString() + " CONNECTED TO CHAT");
				chatSockets.add(chatSocketInput);
				players.add(new NetworkPlayer());
			}
			chatSockets.sort(new SocketSort());
			communicationSockets.sort(new SocketSort());
			for (int i = 0; i < 4; ++i){
				NetworkPlayer p = players.get(i);
				p.setChatSocket(chatSockets.get(i));
				p.setCommSocket(communicationSockets.get(i));
				p.setBr();
				p.setPw();
				chatServer.listen(p.getChatSocket());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done getting input from other players");
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
	
		Thread t = new Thread(new Server());
		t.run();
		
	}
	
	@Override
	public void run(){
		getPlayers();
		generateBattlePairs();
		createBattles();
		boolean result1 = first.join();
		boolean result2 = second.join();
		
		int b1winner = (result1 ? battleOneP1 : battleOneP2);
		int b1loser = (result1 ? battleOneP2 : battleOneP1);
		int b2winner = (result2 ? battleTwoP1 : battleTwoP2);
		int b2loser = (result2 ? battleTwoP2 : battleTwoP1);
	}
	
	private void getPlayers(){
		for (NetworkPlayer n : players){
			n.readPlayer();
		}
	}
	
	private void createBattles(){
		first = new Battle(players.get(battleOneP1 - 1), players.get(battleOneP2 - 1));
		second = new Battle(players.get(battleTwoP1 - 1), players.get(battleTwoP2 - 1));
		ForkJoinPool pool = new ForkJoinPool(2);
		pool.execute(first);
		pool.execute(second);
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
	private class SocketSort implements Comparator<Socket>{

		@Override
		public int compare(Socket o1, Socket o2) {
			return o1.getLocalAddress().toString().compareTo(o2.getLocalAddress().toString());
		}
		
	}
}