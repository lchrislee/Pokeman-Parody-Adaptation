
package dataStore;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
		System.out.println("STARTING TO MAKE OIS");
		try {
			this.ois = new ObjectInputStream(commSocket.getInputStream());
			if(ois==null){
				System.out.println("ois IS NULL");
			}
		} catch (IOException e) {
			System.out.println("BAD OIS");
			System.exit(0);
			e.printStackTrace();
		}
		System.out.println("DONE SETTING OIS");
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
			this.oos.flush();
			if(this.oos==null){
				System.out.println("oos IS NULL");
			}
		} catch (IOException e) {
			System.out.println("BAD OOS");
			System.exit(0);
			e.printStackTrace();
		}
		System.out.println("DONE SETTING OOS");
	}
	
	public void setOOS(ObjectOutputStream oos){
		this.oos = oos;
	}
	
	public void readPlayer(HashMap<String, ArrayList<Pokemon>> mapping){
		System.out.println("NETWORK PLAYER IS READING ");
		if(br == null){
			System.out.println("NETWORKPLAYER.JAVA  br is null");
		}
		readObject(br);
	/*	try {
			String playerInfo = this.br.readLine();
			System.out.println(playerInfo + " PLAYER INFO ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		ObjectInputStream ois = this.ois;
		/*if(ois == null)
			System.out.println("ois cannot be assigned ");
		//try {
			p.readObject(br);
			System.out.println(p);
//			System.out.println(p.getName() + " WHOSE NAME IS IT ");
		/*} catch(java.io.StreamCorruptedException e){
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e){
			System.out.println("END OF FILE");
			e.printStackTrace();
			System.exit(1);
		} catch (OptionalDataException e){
			e.printStackTrace();
			System.out.println("END OF FILE? " + e.eof);
			System.out.println("LEFT? " + e.length);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
//		if(p.getCurrentSprite()==null)
//			System.out.println("CURRENT SPRITE IS NULL");
		
//		System.out.println("PLAYER IS " + p);
		super.setPlayer(mapping);
		System.err.println("AFTER MAPPING PRINT");
		for (Pokemon poke : getPokemonList()){
			for (Move m : poke.getMoveList()){
				System.out.println(poke.getName());
				System.err.println(m);
			}
		}
		System.out.println("DONE PRINTING AFTER MAPPING");
	}

}
