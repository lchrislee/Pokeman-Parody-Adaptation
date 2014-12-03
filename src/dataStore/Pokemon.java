package dataStore;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Pokemon implements Serializable{
	private static final long serialVersionUID = 1340972644597654868L;
	private Boolean conscious = true; //default
	private Integer attack;
	private Integer defense;
	private Integer speed;//stat that is used to decide which pokemon can attack first
	private Integer health;//the total amount of health points the pokemon currently has
	private Integer maxHealth;
	private Double rarity; /*the level of rarity (the higher level of rarity, the stronger the pokemon will be and less likely it will be to appear, more difficult to catch)*/
	private Integer level;
	
	private Vector<Move> moveList;
	//private Vector<ImageIcon> spriteList = new Vector<ImageIcon>();
	private ImageIcon leftSprite;
	private ImageIcon rightSprite;
	private ImageIcon smallLeftSprite;
	private ImageIcon smallRightSprite;
	
	private String name;
	private String[] FileNameArray;	

	public Pokemon (String name,String[] FileNameArray,int a, int d, int sp, int mh, double rarity, int lvl, Vector<Move> moves){//just assign the parameters to private vars
		this.name = name;
		this.FileNameArray = FileNameArray;
		this.attack = a;
		this.defense = d;
		this.speed = sp;
		this.maxHealth = mh;
		this.health = this.maxHealth;//only at the start
		this.rarity = rarity;
		this.level = lvl;
		this.moveList = moves;
		createSprites();//store left and right cuz we dunno which player we'll be.
	}

	public Pokemon(Pokemon p){
		this.name = p.name;
		if (p.FileNameArray == null){
			this.FileNameArray = new String[4];
			FileNameArray[0] = name + "_left_tr_small.png";
			FileNameArray[1] = name + "_left_tr.png";
			FileNameArray[2] = name + "_right_tr_small.png";
			FileNameArray[3] = name + "_right_tr.png";
		}else{
			this.FileNameArray = new String[p.FileNameArray.length];
			for (int i = 0; i < p.FileNameArray.length; ++i)
				this.FileNameArray[i] = p.FileNameArray[i];
		}
		this.attack = p.attack;
		this.defense = p.defense;
		this.speed = p.speed;
		this.maxHealth = p.maxHealth;
		this.health = maxHealth;
		this.rarity = p.rarity;
		this.level = p.level;
		this.moveList = new Vector<Move>(p.moveList);
		System.out.println("in pokemon constructor ");
		for(Move m : moveList){
			System.err.println(m);
		}
		System.out.println("after pokemon constructor loop");
		createSprites();
	}
	
	public Pokemon(){
		conscious = false;
		attack = 0;
		defense = 0;
		speed = 0;
		health = 0;
		maxHealth = 0;
		rarity = 0.0;
		level = 0;
		moveList = new Vector<Move>();
		name = "";
	}
	
	
	public void setRarity(double rarity) {
		this.rarity = rarity;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMoveList(Vector<Move> moveList) {
		this.moveList = moveList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFileNameArray(String[]array){
		this.FileNameArray = array;
	}
	
	private void createSprites(){
		for(int i=0;i<FileNameArray.length;++i){
			ImageIcon icon = new ImageIcon(FileNameArray[i]);
		//	spriteList.add(icon);
			if (FileNameArray[i].contains(name)) {
				if(FileNameArray[i].contains("left")){
					if(FileNameArray[i].contains("small"))
						smallLeftSprite = icon;
					else
						leftSprite = icon;
				}
				
				if(FileNameArray[i].contains("right")){
					if(FileNameArray[i].contains("small"))
						smallRightSprite = icon;
					else
						rightSprite = icon;
				}
			}
		}
	}
	
	public boolean isConscious(){
		return this.conscious;
	}
		
	public void faint(){//set conscious to false 	
		this.conscious = false;
	}
	
	public void setHealth(int h){// this method will be used when a pokemon health must be adjusted (potion is used the pokemon is attacked)
		if(h <= 0)
			this.health = 0;
		else
			this.health = h;
		
		if(this.health == 0)
			this.faint();
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getDefense(){
		return this.defense;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public double getRarity(){
		return this.rarity;
	}
	
	

	public int getMaxHealth() {
		return maxHealth;
	}

	
	public Vector<Move> getMoveList() {
		return moveList;
	}

	
	public ImageIcon getLeftSprite() {
		return leftSprite;
	}
	
	public ImageIcon getRightSprite(){
		return rightSprite;
	}

	public ImageIcon getsmallLeftSprite(){
		return this.smallLeftSprite;
	}
	
	public ImageIcon getsmallRightSprite(){
		return this.smallRightSprite;
	}
	
	public String getName(){
		return name;
	}

	public String[] getFileNameArray() {
		return this.FileNameArray;
	}
	
	private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException{
		System.out.println("Reading in name");
		name = (String) stream.readObject();
		if ((Boolean)stream.readBoolean()){
			int max = (Integer) stream.readInt();
			FileNameArray = new String[max];
			for (int i = 0; i < max; ++i)
				FileNameArray[i] = (String) stream.readObject();
		}
		System.out.println("Reading in moves");
		if (stream.readBoolean()){
			System.out.println("THERE ARE MOVES TO READ");
			for (int i = 0; i < 4; ++i)
				moveList.add((Move)stream.readObject());
		}
		System.out.println("READING IN STATS");
		level = (Integer) stream.readInt();
		rarity = (Double) stream.readDouble();
		maxHealth = (Integer) stream.readInt();
		health = (Integer) stream.readInt();
		speed = (Integer) stream.readInt();
		defense = (Integer) stream.readInt();
		attack = (Integer) stream.readInt();
		conscious = (Boolean) stream.readBoolean();
	}
	
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException{
		System.out.println("Writing out: " + name);
		stream.writeObject(name);
		if (FileNameArray != null){
			stream.writeBoolean(true);
			stream.writeInt(FileNameArray.length);
			for (int i = 0; i < FileNameArray.length; ++i)
				stream.writeObject(FileNameArray[i]);
		}else{
			stream.writeBoolean(false);
		}
		System.out.println("Writing out moves");
		if (moveList != null && moveList.size() > 0){
			System.out.println("Moves exist!");
			stream.writeBoolean(true);
			for (Move m : moveList)
				stream.writeObject(m);
		}else{
			System.out.println("No moves to write out");
			stream.writeBoolean(false);
		}
		System.out.println("Writing out stats");
		System.out.println("level: " + level);
		stream.writeInt(level);
		stream.writeDouble(rarity);
		stream.writeInt(maxHealth);
		System.out.println("health: " + health);
		stream.writeInt(health);
		stream.writeInt(speed);
		stream.writeInt(defense);
		stream.writeInt(attack);
		stream.writeBoolean(conscious);
	}
	
	public String toString(){
		String output = "";
		output += "Name: " + name + "\n\tlevel: " + level + "\n\tRarity: " + rarity + "\n\tCurrent HP: " + health + "\n\tMax HP: " + maxHealth + "\n\tConcious: " + conscious;
		output += "\n\tAttack: " + attack + "\n\tDefense: " + defense + "\n\tSpeed" + speed;
		for (Move m : moveList){
			output += "\n\t\t" + m.toString();
		}
		return output;
	}
}
