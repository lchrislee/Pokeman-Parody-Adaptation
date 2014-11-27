package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import server.chatSystem.ChatServer;
import Battle.Battle;

public class Server implements Runnable{
	public static final int BATTLEPORT = 5555;
	public static final int CHATPORT = 4444;
	
	private ChatServer chatServer;
	private Battle first;
	private Battle second;
	
	private ServerSocket ssChat;
	private ServerSocket ssBattle;
	private ArrayList<Socket> chatSockets;
	private ArrayList<Socket> battleSockets;
	int battleOneP1 = 1;
	int battleOneP2 = -1;
	int battleTwoP1 = -1;
	int battleTwoP2 = -1;
	
	
	public Server(){
		chatServer = new ChatServer(CHATPORT);
		chatSockets = new ArrayList<Socket>();
		battleSockets = new ArrayList<Socket>();
		System.out.println("Getting input from other players");
//		try {
//			Thread.sleep(2000);//TODO replace this with actual server stuff
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("Waiting for clients...");
		try {
			ssChat = new ServerSocket(CHATPORT);
			ssBattle = new ServerSocket(BATTLEPORT);
			
			for (int i = 0; i < 4; ++i){
				Socket battleSocketInput = ssBattle.accept();
				System.out.println(battleSocketInput.toString() + " CONNECTED TO BATTLE");
				battleSockets.add(battleSocketInput);

				Socket chatSocketInput = ssChat.accept();
				System.out.println(chatSocketInput.toString() + " CONNECTED TO CHAT");
				chatServer.listen(chatSocketInput);
				chatSockets.add(chatSocketInput);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done getting input from other players");
		
		generateBattlePairs();
		createBattles();
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
	
}
