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
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//method to store players
	
	public Client(String ipAddress){
		clientGUI = new GUI();
		System.out.println("GUI CREATED");
		hostAddress = ipAddress;
		try {
			clientSocket = new Socket(hostAddress,Server.COMMUNICATIONPORT);
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
			
			//oos = new ObjectOutputStream(clientSocket.getOutputStream());
			//ois = new ObjectInputStream(clientSocket.getInputStream());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("BEFORE GETTING PLAYER");
		Player p = clientGUI.getPlayer();
		System.out.println("AFTER GETTING PLAYER" + p.getName());
		/*System.out.println(p.getName() + " PLAYER NAME ");
	
		System.out.println(p.getPokemonList().get(0).getName() + " FIRST POKEMON NAME ");
		try {
			oos.writeObject(p);//tries to send the player over to the server
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("DID IT WORK?");
	}
	
	public void run(){
		System.out.println("CLIENT MAKING GUI");
		clientGUI.createGUI(hostAddress, pw, br);
		//clientGUI.createGUI(hostAddress, oos, ois);
		
	
	}
	
	public static void main(String[] args) {
		String address = JOptionPane.showInputDialog(null, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		System.out.println(address + " ADDRESS ");
		Client client = new Client(address);
		client.run();

	}

}
