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
	
	//method to store players
	
	
	
	public Client(String ipAddress){
		clientGUI = new GUI();
		System.out.println("GUI CREATED");
		hostAddress = ipAddress;
		try {
			clientSocket = new Socket(hostAddress,Server.COMMUNICATIONPORT);
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player p = clientGUI.getPlayer();
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			os = clientSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(p.getPokemonList().get(0).getName() + " FIRST POKEMON NAME ");
		try {
			oos.writeObject(p);//tries to send the player over to the server
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DID IT WORK?");
	}
	
	public void run(){
		System.out.println("CLIENT MAKING GUI");
		clientGUI.createGUI(hostAddress, pw, br);
	
	}
	
	public void createGUI(String ip){
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String address = JOptionPane.showInputDialog(null, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		System.out.println(address + " ADDRESS ");
		Client client = new Client(address);
		client.run();

	}

}
