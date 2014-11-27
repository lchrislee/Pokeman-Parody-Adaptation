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
	public static final int CHATPORT = 4444;
	public static final int BATTLEPORT = 5555;
	private ChatServer chatServer;
	private ServerSocket ss;
	
	int battleOneP1 = 1;
	int battleOneP2 = -1;
	int battleTwoP1 = -1;
	int battleTwoP2 = -1;
	private Battle first;
	private Battle second;
	
	public Server(){
		chatServer = new ChatServer(CHATPORT);
		System.out.println("Getting input from other players");
//		try {
//			Thread.sleep(2000);//TODO replace this with actual server stuff
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("Waiting for clients...");
		ArrayList<Socket> connectedSockets = new ArrayList<Socket>();
		ArrayList<Socket> incomingSockets = new ArrayList<Socket>();
		
		try {
			ss = new ServerSocket(CHATPORT);
			for (int i = 0; i < 8; ++i) {
				Socket socket = ss.accept();
				System.out.println("Local Address: " + socket.getLocalAddress());
				System.out.println(socket.toString() + " TO STRING ");
				if (connectedSockets.size() == 0)
					connectedSockets.add(socket);
				else{
					boolean putIn = false;
					for (Socket s : connectedSockets){
						if (s.getLocalAddress().equals(socket.getLocalAddress())){
							connectedSockets.add(socket);
							putIn = true;
						}
					}
					if (!putIn){
						for (Socket s : incomingSockets){
							if (s.getLocalAddress().equals(socket)){
								connectedSockets.add(0, socket);
								connectedSockets.add(0, s);
								incomingSockets.remove(s);
							}
						}
						putIn = true;
					}
					if (!putIn)
						incomingSockets.add(socket);
				}
			}
			for (int i = 1; i < connectedSockets.size(); i+= 2)
				chatServer.listen(connectedSockets.get(i));
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
