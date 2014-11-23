package chatSystem;

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


/*public class ChatServer {
	public ChatServer(int port){
		
			//create a serversocket that listens on port 9000
		try{
			ServerSocket ss = new ServerSocket(port);
			while(true){
				Socket s = ss.accept();
				ServerThread st = new ServerThread(s);
				st.start();
			}
		}catch(IOException ioe){
			System.out.println("IOException in server constructor:" + ioe.getMessage());
		}
		
	}
	public static void main(String[] args){
		
		ChatServer server = new ChatServer(2345);
	}
	class ServerThread extends Thread{
		private Socket s;
		public ServerThread(Socket s){
			this.s = s;
		}
		public void run(){
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw = new PrintWriter(s.getOutputStream());
				String line = br.readLine();
				System.out.println("Line received from client " + s.getInetAddress() + ": " + line);
				pw.println("Line going to client");
				pw.flush();
			}catch(IOException ioe){
				System.out.println("IOException in ServerThread constructor:" + ioe.getMessage());
			}
		}
		
	}

}***
public class ChatServer{
	public ChatServer() throws IOException{
	    ServerSocket serverSocket = null;

        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(2343);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 2343");
        }

        while(listeningSocket){
            Socket clientSocket = serverSocket.accept();
            MiniServer mini = new MiniServer(clientSocket);
            mini.start();
        }
        serverSocket.close(); 
	}
	
}*/

public class ChatServer{
	//holds all the connections
	
	public static List<Socket> connectionArray = Collections.synchronizedList(new ArrayList<Socket>());
	public static List<String> currentUsers = Collections.synchronizedList(new ArrayList<String>());
	//holds current users
	//public static ArrayList<String> currentUsers = new ArrayList<String>();
    ServerSocket serverSocket = null;
    
    //Start server first and take connections
    public static void main(String[] args){
    	try{
				final int PORT = 4444;
				ServerSocket ss = new ServerSocket(PORT);
				System.out.println("Waiting for clients...");
				while(true){
					Socket socket = ss.accept();
					connectionArray.add(socket);
					System.out.println("client connected from: " + socket.getLocalAddress().getHostName());
					AddUserName(socket); //maybe
					MiniServer chat = new MiniServer(socket);
					Thread x = new Thread(chat); //pass in connected users
					x.start();
					System.out.println("Started Thread");
			}
		}catch(IOException ioe){
			System.out.println("IOException in server constructor:" + ioe.getMessage());
			ioe.printStackTrace();
		}
    }
    
    //add username to list that are online
	public static void AddUserName(Socket X) throws IOException{
		Scanner INPUT = new Scanner(X.getInputStream());
		String Username = INPUT.nextLine();
		currentUsers.add(Username);
		//tells all users who joined, server echos this to clients
		for(int i = 1; i <= ChatServer.connectionArray.size(); i++){
			Socket tempSock = (Socket) ChatServer.connectionArray.get(i-1);
			PrintWriter output = new PrintWriter(tempSock.getOutputStream());
			output.println("#?:" + currentUsers);
			output.flush();
		}
	}
	
}
