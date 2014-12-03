package dataStore;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//player needs to know the index of the current pokemon that's out

public class Player implements Serializable{
	private static final long serialVersionUID = 2910716062574194936L;

	private Integer currentPokemonIndex = 0;//which pokemon in the list is currently out
	
	private Vector <Pokemon> pokemonList;// this will store the Pokemon the player has caught
//	private Vector <ImageIcon> spriteList;
	private ImageIcon currentSprite;
	private HashMap<String,Integer> statsMap; //([Capture]how many times players died, num pokemon caught, num pokemon released, avg pokemon level & rarity) ([Battle]wins, losses, avg pokemon used per battle)
	private Pokemon enemyPokemon;
	private Pokemon currentPokemon;//in battle at the moment
	private boolean quit = false;
	private String name;
	private String characterImageName;
	
//	private Vector<String>pokemonNamesList;
	//private Set<String> playerMoves;
	
	//name,3 pokemon names
		
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
//			String[] ss = {"Aerodon_left_tr.png", "Lickister_left_tr.png",
//					"Bee-two_left_tr.png", "Marozard_left_tr", "Meonx_left_tr.png",
//					"Geonx_left_tr.png", "Weepintoise_left_tr.png",
//					"Pikayu_left_tr.png", "sexypod_left_tr.png", "feelglet_left_tr.png" ,
//					
//					"Aerodon_right_tr.png", "Lickister_right_tr.png",
//					"Bee-two_right_tr.png", "Marozard_right_tr", "Meonx_right_tr.png",
//					"Geonx_right_tr.png", "Weepintoise_right_tr.png",
//					"Pikayu_right_tr.png", "sexypod_right_tr.png", "feelglet_right_tr.png",
//					
//					"Aerodon_right_tr_small.png", "Lickister_right_tr_small.png",
//					"Bee-two_right_tr_small.png", "Marozard_right_tr_small", "Meonx_right_tr_small.png",
//					"Geonx_right_tr_small.png", "Weepintoise_right_tr_small.png",
//					"Pikayu_right_tr_small.png", "sexypod_right_tr_small.png", "feelglet_right_tr_small.png"
//					
//					,"Aerodon_left_tr_small.png", "Lickister_left_tr_small.png",
//					"Bee-two_left_tr_small.png", "Marozard_left_tr_small", "Meonx_left_tr_small.png",
//					"Geonx_left_tr_small.png", "Weepintoise_left_tr_small.png",
//					"Pikayu_left_tr_small.png", "sexypod_left_tr_small.png", "feelglet_left_tr_small.png"
//					
//					};
			String[] s = {"Bee-two_left_tr.png"};
			Pokemon p = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);
			Pokemon p1 = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);
			Pokemon p2 = new Pokemon("Bee-Two", s, 40, 50, 55, 100, 2, 17, v);
			pokemonList.add(p);
			pokemonList.add(p1);
			pokemonList.add(p2);
			currentPokemon = pokemonList.get(0);
			currentPokemonIndex = 0;
		}
		
		
		public Player(String playerName,Vector<String>pokemonNames){//new constructor
			this.name = playerName;
//			this.pokemonNamesList = pokemonNames;
		}
	
		public Player(Player p/*, HashMap<String, ArrayList<Pokemon>> map*/){
			name = p.name;
			characterImageName = p.characterImageName;
			currentPokemonIndex = p.currentPokemonIndex;
			pokemonList = new Vector<Pokemon>();
			for (Pokemon poke : p.pokemonList){
				pokemonList.add(new Pokemon(poke));
			}
//			setPlayer(p, map);
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
		
		protected void setPlayer(HashMap<String, ArrayList<Pokemon>> map){
//			currentPokemonIndex = p.currentPokemonIndex;
//			currentPokemon = p.getCurrentPokemon();
			if(currentPokemon == null)
				System.out.println("PLAYER.JAVA CURRENT POKEMON NULL ");
//			System.out.println("PLAYER.JAVA CURRENT POKEMON INDEX " + currentPokemonIndex);

//			name = p.getName();
//			characterImageName = p.characterImageName;
			currentSprite = new ImageIcon("res/" + characterImageName);
//			pokemonList = new Vector<Pokemon>(p.pokemonList);

			Image playerImage = null;
			try {
//				System.out.println("Player/setPlayer - characterImageName: " + characterImageName);
				playerImage = ImageIO.read(getClass().getResource("/Character_sprites/" + characterImageName));
			} catch (IOException ioe) {
				System.out.println("fail reading playerimage in Player.java");
			}
			currentSprite = new ImageIcon(playerImage);
			
			if (map == null)
				return;
			
			Vector<Pokemon> v = new Vector<Pokemon>();
			
			for (Pokemon poke : pokemonList){
				//if (poke.getMoveList() == null){
//					System.out.println(poke.getName() + "\t\tNAME");
					ArrayList<Pokemon> possible = map.get(poke.getName());
//					System.out.println(possible.size());
					int position = (int)(Math.random() * possible.size());
					Pokemon REPLACEME = new Pokemon(possible.get(position));
					v.add(REPLACEME);
//					System.out.println("POKEMON COPY FOR PLAYER: " + poke);
//					System.out.println(poke.getMoveList().get(1).getName() + "\t\tMOVE");
				//}
			}
			setPokemonList(v);
			currentPokemon = this.pokemonList.get(0);
			System.out.println("HARD COPIED NEW LIST PRINT OUT");
			for (Pokemon poke : getPokemonList()){
				System.out.println(poke);
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

//		@SuppressWarnings("unchecked")
		protected void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
			characterImageName = (String) stream.readObject();
			System.out.println("READING IN NAME");
			name = (String) stream.readObject();
//			quit = (Boolean) stream.readBoolean();
//			currentPokemon = (Pokemon) stream.readObject();
//			enemyPokemon = (Pokemon) stream.readObject();
//			statsMap = (HashMap<String, Integer>) stream.readObject();
			pokemonList = new Vector<Pokemon>();
			System.out.println("READING IN POKEMAN");
			for (int i = 0; i < 3; ++i)
				pokemonList.add((Pokemon) stream.readObject());
			currentPokemonIndex = (Integer) stream.readObject();
			currentPokemon = pokemonList.get(currentPokemonIndex);
		}
		
		protected void readObject(java.io.BufferedReader br){
			try {
				String information = br.readLine();
				System.out.println(information);
				characterImageName = information.substring(0, information.indexOf("/"));
				name = information.substring(information.indexOf("/") + 1, information.indexOf("-"));
				pokemonList = new Vector<Pokemon>();
				Pokemon p = new Pokemon();
				String firstPName = information.substring(information.indexOf("-") + 1, information.indexOf("|"));
				p.setName(firstPName);
				pokemonList.add(p);
				Pokemon p2 = new Pokemon();
				String secondPName = information.substring(information.indexOf("|")+1, information.lastIndexOf("|"));
				p2.setName(secondPName);
				pokemonList.add(p2);
				Pokemon p3 = new Pokemon();
				String thirdPName = information.substring(information.lastIndexOf("|") +1, information.length());
				p3.setName(thirdPName);
				pokemonList.add(p3);
				currentPokemonIndex = 0;
				currentPokemon = pokemonList.get(currentPokemonIndex);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
					
		}
		
		private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
			System.out.println("Writing out: " + name);
			stream.writeObject(characterImageName);
			stream.writeObject(name);
//			stream.writeBoolean(quit);
//			stream.writeObject(currentPokemon);
//			stream.writeObject(enemyPokemon);
//			stream.writeObject(statsMap);
			System.out.println("WRITING OUT POKEMAN");
			for (Pokemon p : pokemonList)
				stream.writeObject(p);
			stream.writeObject(currentPokemonIndex);
		}
		
		public String getCharacterImageName() {
			return this.characterImageName;
		}

		public String toString(){
			String output = "Name: " + name + "\ncharacterImageName: " + characterImageName + "\ncurrentPokemonIndex: " + currentPokemonIndex;
			for (Pokemon p : pokemonList){
				output += "\n\t" + p.toString();
			}
			return output;
		}
}
