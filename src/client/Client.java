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

public class Client{
	private GUI clientGUI;
	private Socket clientSocket;
	private BufferedReader br;
	private PrintWriter pw;
	private String hostAddress;
	
	
	public Client(String ipAddress){
		clientGUI = new GUI();
		System.out.println("GUI CREATED");
		hostAddress = ipAddress;
		try {
			clientSocket = new Socket(hostAddress,Server.BATTLEPORT);
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			pw = new PrintWriter(clientSocket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
