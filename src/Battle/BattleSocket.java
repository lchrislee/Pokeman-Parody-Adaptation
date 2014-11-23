package Battle;

import java.net.Socket;

import dataStore.Player;

public class BattleSocket extends Socket{//contains a player
	private Player player;
	
	public BattleSocket(){
		this.player = new Player();
	}
	
	public Player getPlayer(){
		return this.player;
	}
}
