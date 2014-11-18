package dataStore;

import items.Item;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class AIPlayer extends Player {

	Pokemon enemyPoke;
	private int baseChanceStayIn;
	private int baseChanceUseItem;
	private int baseChanceSwitchPokemon;
	private int baseChanceAttack;
	
	private int damageAttacks[];
	private int percentageHealthAfterItem[];
	private int pickedItem;
	
	public AIPlayer(ArrayList<Pokemon> pList, ArrayList<ImageIcon> imageList,
			ArrayList<Item> itemList) {
		super(pList, imageList, itemList);
	}
	
	public void makeMove(){
		
	}
	
	private int checkDamage(){
		
	}
	
	private int checkSurvival(){
		
	}
	
	private int checkItemUsage(){
		
	}
	
	private void useItem(){
		
	}
	
	private void attack(){
		
	}
	
	private int calculateDamageDone(Move m){
		
	}
	
	private int calculateDamageToMe(){
		
	}
	
	private int calculateHealthRegain(Item i){
		
	}
	
}