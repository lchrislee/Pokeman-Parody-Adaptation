
package dataStore;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkPlayer extends Player {
	private static final long serialVersionUID = -6142557240391241069L;
	private Socket chatSocket;
	private Socket commSocket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	//testing stuff
	
	
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

	//trying to get objectinputstream;
	
	public ObjectInputStream getOIS(){
		return ois;
	}
	
	public BufferedReader getBr() {
		return br;
	}

	//testing
	public void setOIS(){
		
			try {
				this.ois = new ObjectInputStream(commSocket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	//testing
	public void setOIS(ObjectInputStream ois){
		this.ois = ois;
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

	public ObjectOutputStream getOOS(){
		return oos;
	}
	
	public void setOOS(){
		try {
			this.oos = new ObjectOutputStream(commSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setOOS(ObjectOutputStream oos){
		this.oos = oos;
	}
	
	public void readPlayer(){
		System.out.println("NETWORK PLAYER IS READING ");
		Player p = null;
		super.setPlayer(p);
		ObjectInputStream ois = this.ois;
		try {
			Player p2 = (Player) ois.readObject();
			System.out.println(p2.getName() + " WHOSE NAME IS IT ");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
