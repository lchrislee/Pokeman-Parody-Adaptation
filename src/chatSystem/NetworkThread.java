package chatSystem;

import java.io.BufferedReader;
import java.io.IOException;

import sidebarGUI.ChatGUI;


public class NetworkThread extends Thread {
	ChatGUI myGA;
	BufferedReader myBR;

	public NetworkThread(ChatGUI ga){
		myGA = ga;

	}
	public void run(){
		while(true){
			//String msg = myBR.readLine(); //waits here cant stick this in gui
			//myGA.readMessage(msg);
			myGA.readMessage("oo");
			
		}
	}
}
