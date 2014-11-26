package Battle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import dataStore.Move;
import dataStore.Player;
import dataStore.Pokemon;




public class BattleThread implements Runnable {//needs a lot of work but i'll commit for now
	private BattleSocket battleSocket;
	private BattleSocket battleSocketTwo;
	private BattleServer bs;
	private Player p1;
	private Player p2;
	
	private DataInputStream P1In;
	private DataOutputStream P1Out;
	private DataInputStream P2In;
	private DataOutputStream P2Out;
	//@Override
	/*public void run() {
		// TODO Auto-generated method stub
		
	}*/
	
	
	
	public BattleThread(BattleServer bs,BattleSocket battleSocket,BattleSocket battleSocketTwo){
		this.bs = bs;
		this.battleSocket = battleSocket;
		this.battleSocketTwo = battleSocketTwo;
		this.p1 = battleSocket.getPlayer();
		this.p2 = battleSocketTwo.getPlayer();
		try {
			P1In = new DataInputStream(battleSocket.getInputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			P1Out = new DataOutputStream(battleSocket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			P2In = new DataInputStream(battleSocketTwo.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			P2Out = new DataOutputStream(battleSocketTwo.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		//make both players select their pokemon
		/*Pokemon P1poke = p1.getCurrentPokemon();
		Pokemon P2poke = p2.getCurrentPokemon();
		
		while(p1.hasActivePokemon() && p2.hasActivePokemon() && !p1.hasQuit() && !p2.hasQuit()){
			//damage calculations...who goes first is all done by the server
			if(bs.p1goFirst(P1poke, P2poke)){
				
			}
		}
	}
	*/
	
}
