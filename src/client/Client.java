package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player p = clientGUI.getPlayer();
	}
	
	public void run(){
		System.out.println("CLIENT MAKING GUI");
		clientGUI.createGUI(hostAddress, pw, br);
	
	}
	
	public static void main(String[] args) {
		String address = JOptionPane.showInputDialog(null, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		System.out.println(address + " ADDRESS ");
		Client client = new Client(address);
		client.run();

	}

}
