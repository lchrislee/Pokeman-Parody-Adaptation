package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

import dataStore.NetworkPlayer;
import server.chatSystem.ChatServer;
import Battle.Battle;

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
				chatServer.listen(chatSocketInput);
				chatSockets.add(chatSocketInput);
				players.add(new NetworkPlayer());
			}
			chatSockets.sort(new SocketSort());
			communicationSockets.sort(new SocketSort());
			for (NetworkPlayer p : players){
				
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
		generateBattlePairs();
		createBattles();
		boolean result1 = first.join();
		boolean result2 = second.join();
	}
	
	private void createBattles(){
		
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