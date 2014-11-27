package helper;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStore.Pokemon;

public class PokemonDisplay extends JPanel{
	private static final long serialVersionUID = 1009609997809971667L;
	//pokemon instance
	private GridBagConstraints gbc;
	private JLabel image;
	private JLabel name;
	private JLabel level;
	private JLabel hp;
	private Pokemon displayingPokemon;
	private int mouseLeftOff;
	
	public PokemonDisplay(){
		basicSetup();
	}
	
	public PokemonDisplay(Pokemon p){
		basicSetup();
		displayingPokemon = p;
	}
	
	private void basicSetup(){
		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		createLayout();
	}
	
	public void setPokemon(Pokemon p){
		displayingPokemon = p;
	}
	
	public Pokemon getPokemon(){
		return displayingPokemon;
	}
	
	public void setMouseLeftOff(int i){
		mouseLeftOff = i;
	}
	
	public int getMouseLeftOff(){
		return mouseLeftOff;
	}
	
	private void createLayout(){
		gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		image = new JLabel(new ImageIcon("res/Pokemon_sprites/dadizard_left_tr_small.png"));
		add(image, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		name = new JLabel("Dadizard");
		add(name, gbc);
		gbc.gridy = 1;
		level = new JLabel("Level " + "20");
		add(level, gbc);
		gbc.gridy = 2;
		hp = new JLabel("30/30");
		add(hp, gbc);
	}
	
	public static void main(String [] args){
		JFrame j = new JFrame();
		j.add(new PokemonDisplay());
		j.setSize(250, 600);
		j.setVisible(true);
	}
}//end PokemonDisplay class