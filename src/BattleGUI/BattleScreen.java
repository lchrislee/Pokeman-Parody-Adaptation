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
	Pokemon enemyPokemon;
	Pokemon yourPokemon;
	public BattleScreen() {
		setPreferredSize(new Dimension(500,350));
		setLayout(new GridLayout(2,2));
		setBackground(Color.white);
		createGUI();
	}
	private void createGUI() {
		JPanel enemyInfoPanel = new JPanel();
		enemyInfoPanel.setBackground(Color.white);
		/* dummy enemy info */
		JLabel enemyPokemonLabel = new JLabel("Pikayu lvl 42");
		enemyPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
		JLabel enemyPokemonHealth = new JLabel("5/10");
		enemyPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
		enemyInfoPanel.add(enemyPokemonLabel);
		enemyInfoPanel.add(enemyPokemonHealth);
		
		JPanel enemyPokemonPanel = new JPanel();
		enemyPokemonPanel.setBackground(Color.white);
		JLabel enemyPokemonImage = new JLabel(new ImageIcon("res/Pokemon_sprites/pikayu_left_tr.png"));
		enemyPokemonPanel.add(enemyPokemonImage);
		
		JPanel yourPokemonPanel = new JPanel();
		yourPokemonPanel.setBackground(Color.white);
		JLabel yourPokemonImage = new JLabel(new ImageIcon("res/Pokemon_sprites/feelglet_right_tr.png"));
		yourPokemonPanel.add(yourPokemonImage);
		
		JPanel yourInfoPanel = new JPanel();
		yourInfoPanel.setBackground(Color.white);
		JLabel yourPokemonLabel = new JLabel("Feelglet lvl 69");
		yourPokemonLabel.setFont(new Font("Arial",Font.PLAIN, 35));
		JLabel yourPokemonHealth = new JLabel("69/420");
		yourPokemonHealth.setFont(new Font("Arial",Font.PLAIN, 35));
		yourInfoPanel.add(yourPokemonLabel);
		yourInfoPanel.add(yourPokemonHealth);
		
		add(enemyInfoPanel);
		add(enemyPokemonPanel);
		add(yourPokemonPanel);
		add(yourInfoPanel);
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
	}
}

