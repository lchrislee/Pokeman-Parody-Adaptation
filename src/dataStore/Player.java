package dataStore;

import items.Item;
import java.util.Vector;
import java.util.HashMap;

import javax.swing.ImageIcon;

//player needs to know the index of the current pokemon that's out

public class Player {
	//private int x; //position
	//private int y;//position

	private int currentPokemonIndex = 0;//which pokemon in the list is currently out
	
	private Vector <Pokemon> pokemonList;// this will store the Pokemon the player has caught
	private Vector <ImageIcon> spriteList;
	private Vector <Item> itemList;// �Cstores all the items the player owns
	private ImageIcon currentSprite;
	private HashMap<String,Integer> statsMap; //([Capture]how many times player��s died, num pokemon caught, num pokemon released, avg pokemon level & rarity) ([Battle]wins, losses, avg pokemon used per battle)
	private Pokemon enemyPokemon;
	private Pokemon currentPokemon;//in battle at the moment
	//private boolean isBattling = false;//players start off capturing
	private boolean quit = false;
	
	/*
	private int numSteps = 500;
	private static int NORTH = 1;
	private static int EAST = 2;
	private static int SOUTH = 3;
	private static int WEST = 4;
	public int direction = SOUTH;//set it to this first so we can see the players faces
	*/
		public Player(Vector<Pokemon>pList, Vector <ImageIcon> imageList, Vector<Item> itemList){
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
		
		public boolean hasQuit(){
			return this.quit;
		}
		
		public int getCurrentPokemonIndex(){
			return this.currentPokemonIndex;
		}
		/*
		public boolean isBattling(){//in the battle phase or capture phase
			return this.isBattling;
		}*/
		
		/*
		public void setBattling(boolean isBattling){//switch from capture to battle, maybe change access modifier later
			this.isBattling = true;			
		}*/	
		
		/*
		public void walk(int direction){ //changes the x and y position of the player and keep track of which direction i'm currently in
			//change sprites and update x and y
			//decrease steps until 0
			switch(direction){//figure out how to display animations too -.- and also switch current displayed sprite
				case 1:
					this.y-=1;//go up one
					//this.currentSprite = this.spriteList.get(0);
					break;
				case 2:
					this.x+=1;
					//this.currentSprite = this.spriteList.get(1);
					break;
				case 3:
					this.y+=1;
					//this.currentSprite = this.spriteList.get(2);
					break;
				case 4:
					this.x-=1;
					//this.currentSprite = this.spriteList.get(3);
					break;
			}
		}*/
		
		public void setEnemyPokemon(Pokemon enemy){
			if(enemy!=null){
			this.enemyPokemon = enemy;
			}
		}
		
			
		public void choosePokemon(){//gets called only when we start the battle
			this.currentPokemon = this.pokemonList.get(0);
			
		}
				
		public void choosePokemon(Pokemon p){ //�C the player will access his/her Vector of pokemon and select one to battle (when the current pokemon faints)
			if(this.currentPokemon!=p){//no switching with yourself
				if(this.pokemonList.contains(p)){
					this.currentPokemon = p;
					currentPokemonIndex = this.pokemonList.indexOf(p);				
					
				}
			}
				
		}	
			
		
		public void flee(){//set quit to true
			this.quit = true;
		}
		/*
		public void releasePokemon(Pokemon p){
			if(this.pokemonList.contains(p))
				this.pokemonList.remove(p);
		}*/
		
		public void useItem(Item i){
			if(this.itemList.contains(i)){
				i.use(this.currentPokemon);
				this.itemList.remove(i);//now we don't have the item
			}
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
		
		public Vector<Item> getItemList(){
			return this.itemList;
		}
		
		public Vector<ImageIcon>getSpriteList(){
			return this.spriteList;
		}
		
		public HashMap<String,Integer>getStats(){
			return this.statsMap;
		}


/*
		public int getX() {
			return x;
		}



		public void setX(int x) {
			this.x = x;
		}



		public int getY() {
			return y;
		}



		public void setY(int y) {
			this.y = y;
		}


*/
		public ImageIcon getCurrentSprite() {
			return currentSprite;
		}

		/*
		public void setCurrentSprite(ImageIcon currentSprite) {
			this.currentSprite = currentSprite;
		}*/
		public HashMap<String, Integer> getStatsMap() {
			return statsMap;
		}
		
		

/*
		public int getNumSteps() {
			return numSteps;
		}



		public void setNumSteps(int numSteps) {
			this.numSteps = numSteps;
		}
*/

/*
		public static int getNORTH() {
			return NORTH;
		}



		public static void setNORTH(int nORTH) {
			NORTH = nORTH;
		}



		public static int getEAST() {
			return EAST;
		}



		public static void setEAST(int eAST) {
			EAST = eAST;
		}



		public static int getSOUTH() {
			return SOUTH;
		}



		public static void setSOUTH(int sOUTH) {
			SOUTH = sOUTH;
		}



		public static int getWEST() {
			return WEST;
		}



		public static void setWEST(int wEST) {
			WEST = wEST;
		}



		public int getDirection() {
			return direction;
		}
*/

		
		
}
