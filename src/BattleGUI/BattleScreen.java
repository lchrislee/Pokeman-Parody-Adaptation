package BattleGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.clientGUI.Waiting.WaitingPanel;
import dataStore.Pokemon;

public class BattleScreen extends JPanel {
	
	JPanel enemyInfoPanel, enemyPokemonPanel, yourPokemonPanel, yourInfoPanel;
	JLabel enemyPokemonHealth, yourPokemonHealth;
	Pokemon enemyPokemon = null;
	Pokemon yourPokemon = null;
	
	public BattleScreen(Pokemon enemy, Pokemon yours) {
		this.enemyPokemon = enemy;
		this.yourPokemon = yours;
		setPreferredSize(new Dimension(500,350));
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		createGUI();
	}
	
	public void ChangeYourPokemon (Pokemon yours) {
		this.yourPokemon = yours;
		this.removeAll();
		createGUI();
		repaint();
		revalidate();
	}
	
	public void ChangeYourPokemonHealth () {
		/* damage will be changed by receiving the damage or the new current health */
		yourPokemonHealth.setText("");
		repaint();
		revalidate();
	}
	
	public void ChangeEnemyPokemonHealth () {
		/* damage will be changed by receiving the damage or the new current health */
		enemyPokemonHealth.setText("");
		repaint();
		revalidate();
	}
	
	public void ChangeEnemyPokemon (Pokemon enemy) {
		this.enemyPokemon = enemy;
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
		/* dummy enemy info */
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
			enemyPokemonPanel.setBackground(Color.white);
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
			yourPokemonPanel.setBackground(Color.white);
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
		/*
		w.ChangeEnemyPokemon(new Pokemon());
		w.ChangeYourPokemon(new Pokemon());
		w.ChangeEnemyPokemon(new Pokemon());
		w.ChangeYourPokemon(new Pokemon());
		*/
	}
}

