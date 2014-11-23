package dataStore;

import items.Item;

import java.util.ArrayList;

import javax.swing.ImageIcon;

enum Result{
	zero, low, mediumLow, medium, mediumHigh, high;
}

public class AIPlayer extends Player {

	Pokemon enemyPoke;
	private int baseChanceStayIn;
	private int baseChanceUseItem;
	private int baseChanceSwitchPokemon;
	private int baseChanceAttack;
	
	private int damageAttacks[];
	private int percentageHealthAfterItem[];
	private Item pickedItem;
	
	public AIPlayer(ArrayList<Pokemon> pList, ArrayList<ImageIcon> imageList,
			ArrayList<Item> itemList) {
		super(pList, imageList, itemList);
	}
	
	public void makeMove(){
		Result d = checkDamage(), s = checkSurvival(), u = checkItemUsage();
		
		boolean temp = (s.compareTo(Result.low) == 0 && d.compareTo(Result.medium) >= 0);

		if (d == Result.high)
			attack();//equiv
		else if (d.compareTo(Result.medium) >= 0 && s.compareTo(Result.medium) >= 0)
			attack();//equiv
		else if (/*pokemon list size*/ temp && u.compareTo(Result.low) <= 0)
			switchPoke(/*method to pick pokemon*/generateOtherPokemon());
		else if (s.compareTo(Result.low) <= 0 && u.compareTo(Result.high) == 0)
			useItem(pickedItem);
		else
			attack();
	}
	
	private Result checkDamage(){
		Pokemon p = getCurrentPokemon();
	}
	
	private Result checkSurvival(){
		
	}
	
	private Result checkItemUsage(){
		
	}
	
	private void useItem(){
		
	}
	
	private void attack(){
		
	}
	
	private void switchPoke(int r){
		
	}
	
	private int calculateDamageDone(Move m){
		
	}
	
	private int calculateDamageToMe(){
		
	}
	
	private int calculateHealthRegain(Item i){
		
	}
	
	private int generateOtherPokemon(){
		
	}
}