package map;
import java.util.ArrayList;

import map.tiles.Terrain;
import dataStore.Player;

public class Map {
	private ArrayList<Screen> screenList;			// 2 x 2 square
	private int height;
	private int width;
	
	public void switchScreen(Player p, Screen current, Screen next){
		next.addPlayer(p);
		current.removePlayer(p);
	}
	
	public Screen getScreen(int index){
		if(index < this.screenList.size() && index > -1){
			return this.screenList.get(index);
		}
		return null;//other wise we are empty
	}
	
	public void movePlayer(Player p, Direction d){
		//change player's location
		Screen current = null;
		for (Screen s : screenList){
			if (s.isPlayerOnScreen(p)){
				current = s;
				/*store current player position*/
				s.movePlayer(p);
				if (!s.getPlayerTerrain(p).canWalkOn()){
					/*put player back to where they were*/
					s.movePlayer(p);
				}
				break;
			}
		}
		
		try{
			Terrain t = current.getPlayerTerrain(p);
			if (t.canBattleOn()){
				findPokemon(generateRandomNum());
			}
		}catch(IndexOutOfBoundsException ioobe){
			//do nothing
		}
	}
	
	public boolean isPlayerOnScreen(Player p, Screen index){
		return index.isPlayerOnScreen(p);
	}
	
	public int generateRandomNum(){
		return (int)(Math.random() * 255 + 1);
	}
	
	private void findPokemon(int chance){
		/*something here*/
	}
	
	private void createBattle(){
		/*something here*/
	}
}