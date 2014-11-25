package chatSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;

import sidebarGUI.ChatGUI;

public class ChatClient implements Runnable {
	Socket sock;
	Scanner input;
	Scanner send = new Scanner(System.in);
	public static PrintWriter output;
	
	public ChatClient(Socket sock){
		
		this.sock = sock;
	}
	
	@Override
	public void run() {
		
		try {
			input = new Scanner(sock.getInputStream());
			output = new PrintWriter(sock.getOutputStream());
			output.flush();
			//maybe dont need output here?
			while(true){
				receive();  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				sock.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public void receive(){
		if(input.hasNext()){
			String message = input.nextLine();
			
		ChatGUI.txeaAllMessages.setText(ChatGUI.txeaAllMessages.getText() + "\n" + message);
			
		}
		
	}
	public void send(String X){
		//output.println();
	}
	public void checkStream(){
		
	}
}
