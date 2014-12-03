package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import server.Server;
import client.clientGUI.GUI;
import dataStore.Player;
//import dataStore.Pokemon;

public class Client{
	
	private GUI clientGUI;
	private Socket clientSocket;
	private BufferedReader br;
	private PrintWriter pw;
	private String hostAddress;
	private String playerName;
	
//	private ObjectOutputStream oos;
	
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
		
		clientGUI = new GUI(pw,br);
		System.out.println("GUI CREATED");
	}
	
	public void run(){
		System.out.println("CLIENT MAKING GUI");
		Player p = clientGUI.getPlayer();
		this.playerName = p.getName();
		//clientGUI.createChat(hostAddress);TODO
		System.out.println("DONE MAKING CHAT");
//		try {
			//Socket s = new Socket(hostAddress, Server.OBJECTPORT);
			//System.out.println("Trying to make outputstream");
			//oos = new ObjectOutputStream(s.getOutputStream());
//			oos.flush();
			
		
			String pokeNameOne = p.getPokemonList().get(0).getName();
			String pokeNameTwo = p.getPokemonList().get(1).getName();
			String pokeNameThree = p.getPokemonList().get(2).getName();
			
			String message = p.getCharacterImageName()+"/"+p.getName()+"-"+pokeNameOne+"|"+pokeNameTwo+"|"+pokeNameThree;
			System.out.println(message + " WORK DAMMIT ");
			pw.println(message);
			pw.flush();
			System.err.println("AFTER FLUSHING STATEMENT");
			
			//br.readLine();

			//client needs to send the message...show waiting screen then readline(wait until all players are good)...then be done -.-
			
			//System.out.println("GOT MESSAGE TO SEND DATA");

			//oos.writeObject(p);//trying to write player
			//oos.flush();
			//oos.close();
			//s.close();
			//System.out.println("DONE WRITING");
			
			//pw.println("DONE WRITING");
			//pw.flush();
			//this.clientGUI
//			System.out.println(br.readLine());//might need to comment this out.
			System.out.println("THIS WORKS");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		clientGUI.createGUI(hostAddress);
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
