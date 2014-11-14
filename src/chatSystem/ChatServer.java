package chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
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
		
		ChatServer server = new ChatServer(8888);
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

}