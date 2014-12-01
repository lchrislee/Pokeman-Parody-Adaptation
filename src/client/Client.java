package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import server.Server;
import client.clientGUI.GUI;
import dataStore.Player;

public class Client{
	
	private GUI clientGUI;
	private Socket clientSocket;
	private BufferedReader br;
	private PrintWriter pw;
	private String hostAddress;
	private String playerName;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//method to store players
	
	public Client(String ipAddress){
		hostAddress = ipAddress;
		try {
			clientSocket = new Socket(hostAddress,Server.COMMUNICATIONPORT);
					
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
					
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientGUI = new GUI();
		System.out.println("GUI INITIALIZED");
		/*
		try {
			oos.writeObject(p);//tries to send the player over to the server
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DID IT WORK?");*/
	}
	
	public void run(){
		System.out.println("CLIENT MAKING GUI");
		Player p = clientGUI.getPlayer();
		System.out.println("GOT PLAYER");
		this.playerName = p.getName();
		System.out.println("STARTING TO SEND");
		clientGUI.createGUI(hostAddress, pw, br);
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(p);//trying to write player
			oos.flush();
			System.out.println("Trying to make outputstream");
			
//			ois = new ObjectInputStream(clientSocket.getInputStream());
//			System.out.println("Trying to make inputstream");//i think input streams are working ok
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE SENDING STUFF");
		///fjdfjdkjfkdjfkjfjfakjfk
//		clientGUI.switchToBattleScreen();
	
	}
	
	public static void main(String[] args) {
		String address = JOptionPane.showInputDialog(null, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		System.out.println(address + " ADDRESS ");
		if (address == null)
			return;
		Client client = new Client(address);
		client.run();
	}
	
	public String getName() {
		return this.playerName;
	}

}
