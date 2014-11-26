package server.chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ChatServer {
	// holds all the connections
	//public static List<Socket> connectionArray = Collections.synchronizedList(new ArrayList<Socket>());
	// holds current users
	public static List<String> currentUsers = Collections.synchronizedList(new ArrayList<String>());
	public static Vector<Socket> connectionArray = new Vector<Socket>();
	//used for accepting new connections
	private ServerSocket serverSocket = null;

	// Constructor and while-accept loop
	public ChatServer(int port) throws IOException {
		listen(port);
	}

	private void listen(int port) throws IOException{
		try {
			//create the server socket
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Waiting for clients...");
			while (true) {
				
				Socket socket = ss.accept();
				connectionArray.add(socket); //?
				System.out.println("client connected from: " + socket.getLocalAddress().getHostName());
				AddUserName(socket); //?
				//new thread for connection
				ChatThread chat = new ChatThread(this, socket);
				Thread x = new Thread(chat);
				x.start();
				System.out.println("Started Thread");
			}
		} catch (IOException ioe) {
			System.out.println("IOException in server constructor:"
					+ ioe.getMessage());
			ioe.printStackTrace();
		}

	}

	// Start server first and take connections
	public static void main(String[] args) throws Exception {
		final int port = 4444;
		new ChatServer(port);

	}

	// Server echos to all clients users who has joined-- adds username to list
	// that are online
	public static void AddUserName(Socket X) throws IOException {
		Scanner INPUT = new Scanner(X.getInputStream());
		String Username = INPUT.nextLine();
		currentUsers.add(Username);

		for (int i = 1; i <= ChatServer.connectionArray.size(); i++) {
			Socket tempSock = (Socket) ChatServer.connectionArray.get(i - 1);
			PrintWriter output = new PrintWriter(tempSock.getOutputStream());
			output.println("#?:" + currentUsers);
			output.flush();
		}
	}
	//sends message to all clients
	void sendToAll(String message) throws IOException{
		for (Socket tempSock : ChatServer.connectionArray) {
			PrintWriter output = new PrintWriter(
					tempSock.getOutputStream());
			output.println(message);
			output.flush();
			System.out.println("Sent to: "
					+ tempSock.getLocalAddress().getHostName());
		}
	}
	//remove a socket
	void removeConnection(Socket s) throws IOException {
	/*	for (int i = 1; i <= ChatServer.connectionArray.size(); i++) {
			Socket tempSock = (Socket) ChatServer.connectionArray.get(i - 1);
			PrintWriter output = new PrintWriter(tempSock.getOutputStream());
			output.println("#?:" + currentUsers);
			output.flush();
		}*/
	/*	for (Socket tempSock : ChatServer.connectionArray) {
			PrintWriter output = new PrintWriter(tempSock.getOutputStream());
			System.out.println("Removing connection to " + s);
			if (tempSock == s) { //or ==
					ChatServer.connectionArray.remove(s); //remove tempsock or s?
			}
				
		}*/
		/*
		 * for (int i = 0; i < ChatServer.connectionArray.size(); i++) { if
		 * (ChatServer.connectionArray.get(i) == socket) {
		 * ChatServer.connectionArray.remove(i); } }
		 */
		for (int i = 0; i < ChatServer.connectionArray.size(); i++) {
			Socket tempSock = (Socket) ChatServer.connectionArray
					.get(i);
			PrintWriter output = new PrintWriter(tempSock.getOutputStream());
			System.out.println("Removing connection to " + s);
			if (tempSock == s) { //or ==
					ChatServer.connectionArray.remove(tempSock); //remove tempsock or s?
			}
		}
		//make sure its closed
		try{
			s.close();
		}catch(IOException ie){
			System.out.println("Error closing" + s);
			ie.printStackTrace();
		}
	
	}

}
