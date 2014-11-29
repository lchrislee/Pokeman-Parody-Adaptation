package dataStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkPlayer extends Player {
	private static final long serialVersionUID = -6142557240391241069L;
	private Socket chatSocket;
	private Socket commSocket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public NetworkPlayer(){
		super();
		chatSocket = null;
		commSocket = null;
	}
	
	public NetworkPlayer(Player p){
		super(p);
		chatSocket = null;
		commSocket = null;
	}
	
	public void setCommSocket(Socket c){
		this.commSocket = c;
	}
	
	public Socket getCommSocket(){
		return commSocket;
	}
	
	public void setChatSocket(Socket c){
		this.chatSocket = c;
	}
	
	public Socket getChatSocket(){
		return this.chatSocket;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr() {
		try {
			this.br = new BufferedReader(new InputStreamReader(commSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw() {
		try {
			this.pw = new PrintWriter(commSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}	

	public void readPlayer(){
		Player p = null;
		super.setPlayer(p);
	}
}