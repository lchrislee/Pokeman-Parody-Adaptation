package BattleGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStore.Pokemon;

public class BattleScreen extends JPanel {
	
	JPanel enemyInfoPanel, enemyPokemonPanel, yourPokemonPanel, yourInfoPanel;
	JLabel enemyPokemonHealth, yourPokemonHealth;
	Pokemon enemyPokemon = null;
	Pokemon yourPokemon = null;
	private String yourName;
	private PrintWriter pw;
	private BufferedReader br;
//	public BattleScreen(Pokemon enemy, Pokemon yours, String yn) {
	public BattleScreen(PrintWriter p, BufferedReader b, String yn){
		pw = p;
		br = b;
		this.yourName = yn;
		setPreferredSize(new Dimension(500,350));
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		
		try {
			parse(br.readLine(), yourPokemon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		changeYourPokemon();
		
		try {
			parse(br.readLine(), enemyPokemon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		changeEnemyPokemon();
	}
	
//	public void ChangeYourPokemon (Pokemon yours) {
	public void changeYourPokemon (){
//		this.yourPokemon = yours;
		this.removeAll();
		createGUI();
		repaint();
		revalidate();
	}
	
	private void parse(String input, Pokemon changeMe){
		/* parse messages for info */
		String m = input;
		Pokemon p = new Pokemon();
		if (m.contains("swap")) {
			m = m.substring(5);
			int nameBound = m.indexOf("?");
			int pokemonBound = m.indexOf("|");
			int levelBound = m.indexOf("!");
			int currentHealthBound = m.indexOf(":");
			String nameCheck = m.substring(0, nameBound);
			//System.out.println("Name checking in swap: " + nameCheck);
			String pokemonCheck = m.substring(nameBound+1,pokemonBound);
			p.setName(pokemonCheck);
			//System.out.println("Pokemon checking in swap: " + pokemonCheck);
			String levelCheck = m.substring(pokemonBound+1,levelBound);
			int level = Integer.parseInt(levelCheck);
			p.setLevel(level);
			//System.out.println("Level checking in swap: " + levelCheck);
			String currentHealthCheck = m.substring(levelBound+1,currentHealthBound);
			int currentHealth = Integer.parseInt(currentHealthCheck);
			p.setHealth(currentHealth);
			//System.out.println("Current health checking in swap: " + currentHealth);
			String maxHealthCheck = m.substring(currentHealthBound+1,m.length());
			int maxHealth = Integer.parseInt(maxHealthCheck);
			p.setMaxHealth(maxHealth);
			//System.out.println("Max health checking in swap: " + maxHealth);
			changeMe = p;
		}
		
	}
	
	public void changeYourPokemonHealth (int remainder) {
		/* damage will be changed by receiving the damage or the new current health */
		yourPokemonHealth.setText("HP " + String.valueOf(remainder) + "/" + yourPokemon.getMaxHealth());
		repaint();
		revalidate();
	}
	
	public void changeEnemyPokemonHealth (int remainder) {
		/* damage will be changed by receiving the damage or the new current health */
		enemyPokemonHealth.setText("HP " + String.valueOf(remainder) + "/" + enemyPokemon.getMaxHealth());
		repaint();
		revalidate();
	}
	
	public void changeEnemyPokemon () {
		this.removeAll();
		createGUI();
		repaint();
		revalidate();
	}
	
	public BattleScreen() {
		
		setPreferredSize(new Dimension(500,350));
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		createGUI();
	}
	
	private void createGUI() {
		
		Color darkgreen = new Color(64,201,100);
		
		if (enemyPokemon == null) {
			enemyInfoPanel = new JPanel();
			enemyInfoPanel.setBackground(Color.white);
			JLabel enemyPokemonLabel = new JLabel("Dummy lvl 420");
			enemyPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
			enemyPokemonHealth = new JLabel("5/10");
			enemyPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
			enemyInfoPanel.add(enemyPokemonLabel);
			enemyInfoPanel.add(enemyPokemonHealth);
			
			enemyPokemonPanel = new JPanel();
			enemyPokemonPanel.setBackground(darkgreen);
			JLabel enemyPokemonImage = new JLabel(new ImageIcon("res/Pokemon_sprites/pikayu_left_tr.png"));
			enemyPokemonPanel.add(enemyPokemonImage);
		}
		else {
			enemyInfoPanel = new JPanel();
			enemyInfoPanel.setBackground(Color.white);
			JLabel enemyPokemonLabel = new JLabel(enemyPokemon.getName());
			JLabel enemyPokemonLevel = new JLabel( String.valueOf(enemyPokemon.getLevel()));
			enemyPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
			enemyPokemonLevel.setFont(new Font("Arial",Font.PLAIN, 35));
			String enemyHealth = String.valueOf(enemyPokemon.getHealth()) + "/" + 
								 String.valueOf(enemyPokemon.getMaxHealth());
			enemyPokemonHealth = new JLabel(enemyHealth);
			enemyPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
			enemyInfoPanel.add(enemyPokemonLabel);
			enemyInfoPanel.add(enemyPokemonLevel);
			enemyInfoPanel.add(enemyPokemonHealth);
			
			enemyPokemonPanel = new JPanel();
			enemyPokemonPanel.setBackground(Color.white);
			JLabel enemyPokemonImage = new JLabel(enemyPokemon.getLeftSprite());
			enemyPokemonPanel.add(enemyPokemonImage);
		}
		
		if (yourPokemon == null) {
			yourPokemonPanel = new JPanel();
			yourPokemonPanel.setBackground(darkgreen);
			JLabel yourPokemonImage = new JLabel(new ImageIcon("res/Pokemon_sprites/feelglet_right_tr.png"));
			yourPokemonPanel.add(yourPokemonImage);
			
			yourInfoPanel = new JPanel();
			yourInfoPanel.setBackground(Color.white);
			JLabel yourPokemonLabel = new JLabel("Dummy lvl 69");
			yourPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
			yourPokemonHealth = new JLabel("69/420");
			yourPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
			yourInfoPanel.add(yourPokemonLabel);
			yourInfoPanel.add(yourPokemonHealth);
		}
		else {
			yourPokemonPanel = new JPanel();
			yourPokemonPanel.setBackground(Color.white);
			JLabel yourPokemonImage = new JLabel(yourPokemon.getRightSprite());
			yourPokemonPanel.add(yourPokemonImage);
			
			yourInfoPanel = new JPanel();
			yourInfoPanel.setBackground(Color.white);
			JLabel yourPokemonLabel = new JLabel(yourPokemon.getName());
			yourPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
			JLabel yourPokemonLevel = new JLabel( String.valueOf(yourPokemon.getLevel()) );
			yourPokemonLevel.setFont(new Font("Arial",Font.PLAIN, 35));
			String yourHealth = String.valueOf(yourPokemon.getHealth()) + "/" + 
					 String.valueOf(yourPokemon.getMaxHealth());
			yourPokemonHealth = new JLabel(yourHealth);
			yourPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
			yourInfoPanel.add(yourPokemonLabel);
			yourInfoPanel.add(yourPokemonLevel);
			yourInfoPanel.add(yourPokemonHealth);
		}
		
		add(enemyInfoPanel);
		add(enemyPokemonPanel);
		add(yourPokemonPanel);
		add(yourInfoPanel);
	}
	
	public void update(String m){
		String temp = m;
		if (temp.contains("hit")) {
			temp = temp.substring(4);
			int nameBound = temp.indexOf("_");
			String nameCheck = temp.substring(0, nameBound);
			//System.out.println("Name checking in hit: " + nameCheck);
			String remainingHealthString = temp.substring(nameBound+1, temp.length());
			int remainingHealth = Integer.parseInt(remainingHealthString);
			//System.out.println("Remaining health in hit: " + remainingHealth);
			if (yourName.equals(nameCheck)) {
				changeYourPokemonHealth(remainingHealth);
			}
			else {
				changeEnemyPokemonHealth(remainingHealth);
			}
		}
		else if (temp.contains("swap")){
			swap(m);
		}
	}
	
	private void swap(String m){
		String temp = m;
		if (temp.contains("swap")) {
			temp = temp.substring(5);
			int nameBound = temp.indexOf("?");
			String nameCheck = temp.substring(0, nameBound);
			if (yourName.equals(nameCheck)) { 
				parse(m,yourPokemon);
				changeYourPokemon();
			}
			else {
				parse(m,enemyPokemon);
				changeEnemyPokemon();
			}
		}
	}
	
	public static void main(String args[]) {
		BattleScreen w = new BattleScreen();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		//testWindow.setSize(500,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(w);
		testWindow.pack();
		testWindow.setVisible(true);
		
		
		/* string parsing for pokemon swap and hits */
		String tempName = "jiwoo";
		
		String hitTest = "hit_jiwoo_69";
		String swapTest = "swap_obama?Pikayu|42!64:81";
		String m = hitTest;
		if (m.contains("hit")) {
			System.out.println("Your name: " + tempName);
			m = m.substring(4);
			int nameBound = m.indexOf("_");
			String nameCheck = m.substring(0, nameBound);
			System.out.println("Name checking in hit: " + nameCheck);
			String remainingHealthString = m.substring(nameBound+1, m.length());
			int remainingHealth = Integer.parseInt(remainingHealthString);
			System.out.println("Remaining health in hit: " + remainingHealth);
			if (!tempName.equals(nameCheck)) {
				System.out.println("Hitting enemy pokemon");
			}
			else System.out.println("Your pokemon getting hit \n \n");
		}
		m = swapTest;
		if (m.contains("swap")) {
			System.out.println("Your name: " + tempName);
			m = m.substring(5);
			int nameBound = m.indexOf("?");
			int pokemonBound = m.indexOf("|");
			int levelBound = m.indexOf("!");
			int currentHealthBound = m.indexOf(":");
			String nameCheck = m.substring(0, nameBound);
			System.out.println("Name checking in swap: " + nameCheck);
			String pokemonCheck = m.substring(nameBound+1,pokemonBound);
			System.out.println("Pokemon checking in swap: " + pokemonCheck);
			String levelCheck = m.substring(pokemonBound+1,levelBound);
			System.out.println("Level checking in swap: " + levelCheck);
			String currentHealthCheck = m.substring(levelBound+1,currentHealthBound);
			int currentHealth = Integer.parseInt(currentHealthCheck);
			System.out.println("Current health checking in swap: " + currentHealth);
			String maxHealthCheck = m.substring(currentHealthBound+1,m.length());
			int maxHealth = Integer.parseInt(maxHealthCheck);
			System.out.println("Max health checking in swap: " + maxHealth);
		}
		/*
		w.ChangeEnemyPokemon(new Pokemon());
		w.ChangeYourPokemon(new Pokemon());
		w.ChangeEnemyPokemon(new Pokemon());
		w.ChangeYourPokemon(new Pokemon());
		*/
	}
}

