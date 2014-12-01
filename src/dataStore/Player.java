package dataStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

//player needs to know the index of the current pokemon that's out

public class Player implements Serializable{
	private static final long serialVersionUID = 5504055184113660229L;

	private int currentPokemonIndex = 0;//which pokemon in the list is currently out
	
	private Vector <Pokemon> pokemonList;// this will store the Pokemon the player has caught
//	private Vector <ImageIcon> spriteList;
	private ImageIcon currentSprite;
	private HashMap<String,Integer> statsMap; //([Capture]how many times players died, num pokemon caught, num pokemon released, avg pokemon level & rarity) ([Battle]wins, losses, avg pokemon used per battle)
	private Pokemon enemyPokemon;
	private Pokemon currentPokemon;//in battle at the moment
	private boolean quit = false;
	private String name;
	private String characterImageName;
	//private Set<String> playerMoves;
		
		public Player(){/*
			playerMoves = new HashSet<String>();
			playerMoves.add("Fight");
			playerMoves.add("Switch");
			playerMoves.add("Quit");*/
			pokemonList = new Vector<Pokemon>();
			Vector<Move> v = new Vector<Move>();
			v.add(new Move(50,/* 1, */"Tackle"));
			v.add(new Move(90,/* 2, */"Psychic"));
			v.add(new Move(100,/* 3, */"Hyper Beam"));
			v.add(new Move(10,/* 4, */"Jump"));
			String[] s = {"Bee-two_left_tr.png"};
			Pokemon p = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);
			Pokemon p1 = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);
			Pokemon p2 = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);

			pokemonList.add(p);
			pokemonList.add(p1);
			pokemonList.add(p2);
		}
	
		public Player(Player p, HashMap<String, ArrayList<Pokemon>> map){
			setPlayer(p, map);
		}
		
		public Player(Vector<Pokemon>pList,/* Vector <ImageIcon> imageList,*/String chosenCharacterName, String playerName){
			this.pokemonList = pList;
//			this.spriteList = imageList;
//			this.currentSprite = new ImageIcon(this.);
//			if(currentSprite == null)
//				System.out.println("NULL SPRITE");
			//this.itemList = itemList;
			this.name = playerName;
			characterImageName = chosenCharacterName + "_normal.png";
//			System.out.println("NAME IS: " + name + "_normal.png");
//			currentSprite = new ImageIcon(name + "_normal.png");
		}	
		
		public boolean hasActivePokemon(){//return false if all pokemon are unconscious
			for(int i=0;i<pokemonList.size();++i){
				if(pokemonList.get(i).isConscious())
					return true;
			}
			return false;
		}
		
		public boolean hasQuit(){
			return this.quit;
		}
		
		public int getCurrentPokemonIndex(){
			return this.currentPokemonIndex;
		}
		public void setCurrentPokemonIndex(int i){
			this.currentPokemonIndex = i;
			this.currentPokemon = pokemonList.get(i);
		}
		
		
		public void setPokemonList(Vector<Pokemon>pList){
			this.pokemonList = pList;
			/*if(this.pokemonList!=null && !this.pokemonList.isEmpty()){
				this.currentPokemon = pokemonList.get(0);
				
			}*/
		}
//		
//		public void setSpriteList(Vector<ImageIcon>spriteList){
//			this.spriteList = spriteList;
//			if(spriteList!=null && !spriteList.isEmpty())
//				currentSprite = spriteList.get(0);
//		}
				
		public void setEnemyPokemon(Pokemon enemy){
			if(enemy!=null){
			this.enemyPokemon = enemy;
			}
		}
		
		protected void setPlayer(Player p, HashMap<String, ArrayList<Pokemon>> map){
			currentPokemonIndex = p.currentPokemonIndex;
			characterImageName = p.characterImageName;
			currentSprite = new ImageIcon("res/" + characterImageName);
			pokemonList = new Vector<Pokemon>(p.pokemonList);
			if (map == null)
				return;
			
			for (Pokemon poke : pokemonList){
				if (poke.getMoveList() == null){
					ArrayList<Pokemon> possible = map.get(poke.getName());
					int position = (int)(Math.random() * possible.size());
					poke = new Pokemon(possible.get(position));
				}
			}
//			spriteList = new Vector<ImageIcon> (p.spriteList);			
//			statsMap = new HashMap<String,Integer>(p.statsMap);
		}
			
		public void choosePokemon(){//gets called only when we start the battle
			this.currentPokemon = this.pokemonList.get(0);
			
		}
		
		//doubles up as switching
		public void choosePokemon(Pokemon p){ // the player will access his/her Vector of pokemon and select one to battle (when the current pokemon faints)
			//if(this.currentPokemon!=p){//no switching with yourself
				if(this.pokemonList.contains(p)){
					this.currentPokemon = p;
					currentPokemonIndex = this.pokemonList.indexOf(p);				
					
				}
			//}
				
		}	
			
		
		public void quit(){//set quit to true
			this.quit = true;
		}
		
		public String getName(){
			return this.name;
		}
						
		public Pokemon getCurrentPokemon(){
			return this.currentPokemon;
		}
		
		public Pokemon getEnemyPokemon(){
			return this.enemyPokemon;
		}
		
		public Vector<Pokemon> getPokemonList(){
			return this.pokemonList;
		}
		
//				
//		public Vector<ImageIcon>getSpriteList(){
//			return this.spriteList;
//		}
		
		public HashMap<String,Integer>getStats(){
			return this.statsMap;
		}

		public ImageIcon getCurrentSprite() {
			return currentSprite;
		}
		
		public String getCharacterImageName() {
			return this.characterImageName;
		}
	
		public String toString(){
			return name + "\n" + currentPokemonIndex;
		}
}
