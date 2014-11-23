package chatSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import sidebarGUI.ChatGUI;


public class MiniServer implements Runnable{
	Socket socket = null;
	private Scanner input;
	private PrintWriter output;
	String message = "";
	//Socket socket = null;
	//ChatGUI myGA;
	//BufferedReader myBR;

	public MiniServer(Socket socket){
		this.socket = socket;

	}
	public void CheckConnection() throws IOException{
		if(!socket.isConnected()){
			for(int i = 0; i < ChatServer.connectionArray.size(); i++){
				if(ChatServer.connectionArray.get(i) == socket){
					ChatServer.connectionArray.remove(i);
				}
			}
			
			for(int i = 0; i < ChatServer.connectionArray.size(); i++){
				Socket tempSock = (Socket) ChatServer.connectionArray.get(i-1);
				PrintWriter output = new PrintWriter(tempSock.getOutputStream());
				output.println(tempSock.getLocalAddress().getHostName() + "disconnected");
				output.flush();
				System.out.println(tempSock.getLocalAddress().getHostName() + "disconnected");
			}
		}
	}
	public void run(){
		try {
			input = new Scanner(socket.getInputStream());
			output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(true){			
			try {
				
				CheckConnection();
				
				if(!input.hasNext()){
				
				continue;
				}
				
				message = input.nextLine();
				
				System.out.println("Client message: " + message);
				System.out.println("ConnectionList size:");
				System.out.println(ChatServer.connectionArray.size());
				/*for(int i  = 0; i < ChatServer.connectionArray.size()-1; i++){
						
						Socket tempSock = (Socket) ChatServer.connectionArray.get(i);
						PrintWriter output = new PrintWriter(tempSock.getOutputStream());
						output.println(message);
						output.flush();
						System.out.println("Sent to: " + tempSock.getLocalAddress().getHostName());
				}*/
				
				for (Socket tempSock : ChatServer.connectionArray) {
					//tempSock = (Socket) ChatServer.connectionArray.get(i);
					PrintWriter output = new PrintWriter(tempSock.getOutputStream());
					output.println(message);
					output.flush();
					System.out.println("Sent to: " + tempSock.getLocalAddress().getHostName());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*finally{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			//read input and process here
			//read message from the chatgui
			//String msg = myBR.readLine(); //waits here cant stick this in gui
			//myGA.readMessage(msg);
		//	myGA.readMessage("oo");
			
		}
		//implement methods here
	}
}
