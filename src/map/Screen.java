package map;
import java.util.ArrayList;

import dataStore.Player;
import map.tiles.Terrain;


public class Screen {
	
	private Terrain[][] terrainArray;
	private ArrayList<Player> playersOnScreen;
	private int width;
	private int height;
	
	public Screen(){
		/*allocate terrainArray*/
		playersOnScreen = new ArrayList<Player>();
	}
	
	public void movePlayer(Player p){
		
	}
	
	public boolean isPlayerOnScreen(Player p){
		for (Player pl : playersOnScreen){
			if (p == pl)
				return true;
		}
		return false;
	}
	
	public void removePlayer(Player p){
		playersOnScreen.remove(p);
	}
	
	public void addPlayer(Player p){
		playersOnScreen.add(p);
	}

	public Terrain getPlayerTerrain(Player p){
		if (isPlayerOnScreen(p)){
			/*get player position*/
			return terrainArray[0][0]; //replace these with actual position
		}else
			throw new IndexOutOfBoundsException("No player present");
	}
}