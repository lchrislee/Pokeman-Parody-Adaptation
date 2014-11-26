package Battle;

//socket has player
//server class has a server socket for chat and another server socket for battle

import items.Item;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;

import dataStore.Player;
import dataStore.Pokemon;

public class BattleServer {
	private ServerSocket ss;
	
	private Vector<BattleThread> battleThreads;
	
	public BattleServer(){
		battleThreads = new Vector<BattleThread>();//should store two at a time
		
		try {
			ss = new ServerSocket(9001);
					
			BattleSocket[]socketArray = {null,null,null,null};//we'll end up with four sockets anyway
			Vector<Pokemon>pList = null;
			Vector<ImageIcon>spriteList = null;
			Vector<Item>itemList = null;
			//too lazy to make dummy data
					
			
			for(int i=0;i<4;++i){
			 socketArray[i] = (BattleSocket)ss.accept();//hopefully downcasting this doesn't give us problems
			 socketArray[i].getPlayer().setItemList(itemList);
			 socketArray[i].getPlayer().setPokemonList(pList);
			 socketArray[i].getPlayer().setSpriteList(spriteList);
			 new DataOutputStream(socketArray[i].getOutputStream()).writeInt((i%2)+1);//inform the players what player they are in the battle
			 //socketArray[i].getOutputStream()
			}
						
			/*
			for(int i=0;i<2;++i){
				battleThreads.add(new BattleThread(this,socketArray[i],socketArray[i+2]));
			}*/
			
			
			
			//let's try one battle first
			Thread battleone = new Thread(battleThreads.get(0));
			//Thread battletwo = new Thread(battleThreads.get(1));
			battleone.start();
			//battletwo.start();
			
			
			//generate random numbers later
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//OVAR 9000
		
	}
	
	public void dealDamage(int damage){
		
	}
	
	public void showDamage(){
		
	}
	
	public boolean p1goFirst(Pokemon p1Poke, Pokemon p2Poke){
		if(p1Poke.getSpeed() > p2Poke.getSpeed())
			return true;
	
		else if(p1Poke.getSpeed() == p2Poke.getSpeed()){
			
			if(p1Poke.getRarity() > p2Poke.getRarity())
				return true;
			
			else if(p1Poke.getRarity() == p2Poke.getRarity()){
				int num = (int)Math.random()%2;
				if(num == 0)
					return false;
				
				return true;
			}
			
			return false;
		}
				
		return false;
	}
	
	public static void main(String[]args){
		new BattleServer();
	}
	
	
}
