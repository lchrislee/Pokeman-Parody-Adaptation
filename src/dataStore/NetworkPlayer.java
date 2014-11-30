
package dataStore;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
				if(ois==null){
					System.out.println("ois IS NULL");
				}
			} catch (IOException e) {
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
			if(br == null)
				System.out.println("br is null");
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
			if(pw == null)
				System.out.println("pw is null");
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
			if(oos==null){
				System.out.println("oos IS NULL");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setOOS(ObjectOutputStream oos){
		this.oos = oos;
	}
	
	public void readPlayer(){
		System.out.println("NETWORK PLAYER IS READING ");
		Player p = null;
		ObjectInputStream ois = this.ois;
		if(ois == null)
			System.out.println("ois cannot be assigned ");
		try {
			p = (Player) ois.readObject();
			if(p == null){
				System.out.println("PLAYER SENT IS NULL ");
			}
			System.out.println(p.getName() + " WHOSE NAME IS IT ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if(p.getCurrentSprite()==null)
//			System.out.println("CURRENT SPRITE IS NULL");
		
		System.out.println(p);
		super.setPlayer(p);
		
		
	}

}
