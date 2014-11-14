import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Player {
	private int x; //position
	private int y;//position
	private ArrayList <Pokemon> pokemonList;// this will store the Pokemon the player has caught
	private ArrayList <ImageIcon> spriteList;
	private ArrayList <Item> itemList;// ¨Cstores all the items the player owns
	private ImageIcon currentSprite;
	protected HashMap<String,Integer> statsMap; //([Capture]how many times player¡¯s died, num pokemon caught, num pokemon released, avg pokemon level & rarity) ([Battle]wins, losses, avg pokemon used per battle)
	private Pokemon enemyPokemon;
	private Pokemon currentPokemon;//in battle at the moment
	private boolean isBattling = false;//players start off capturing
	private boolean quit = false;
	
	
		public Player(ArrayList<Pokemon>pList, ArrayList <ImageIcon> imageList, ArrayList<Item> itemList){
			this.pokemonList = pList;
			this.spriteList = imageList;
			this.itemList = itemList;
		}
 	
	
		public boolean hasActivePokemon(){//return false if all pokemon are unconscious
			for(int i=0;i<pokemonList.size();++i){
				if(pokemonList.get(i).isConscious())
					return true;
			}
			return false;
		}
		
		public boolean isBattling(){//in the battle phase or capture phase
			return this.isBattling;
		}
		
		public void setBattling(boolean isBattling){//switch from capture to battle
		
			
		}	
		
		public void walk(){ //changes the x and y position of the player
			//change sprites and update x and y
		}
		
		public void setEnemyPokemon(Pokemon enemy){
			this.enemyPokemon = enemy;
		}
		
		public void choosePokemon(){//gets called only when we start fighting
			this.currentPokemon = this.pokemonList.get(0);		
		}
				
		public void choosePokemon(Pokemon p){ //¨C the player will access his/her arraylist of pokemon and select one to battle (when the current pokemon faints)
			this.currentPokemon = p;
		}	
			
		public void chooseItem(){
			
		}
		
		public void throwRocks(){
			
		}
		
		public void flee(){//set quit to true
			this.quit = true;
		}
		
		public void releasePokemon(Pokemon p){
			if(this.pokemonList.contains(p))
				this.pokemonList.remove(p);
		}
		
		public void useItem(Item i){
			i.use(this.currentPokemon);
		}
				
		public Pokemon getCurrentPokemon(){
			return this.currentPokemon;
		}
}
