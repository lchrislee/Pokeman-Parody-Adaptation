package sidebarGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuUI extends JPanel {
	private static final long serialVersionUID = 7397593496707020802L;
	
	private ArrayList<PokemonDisplay> listPokemon;
	//instance of player
	
	public MenuUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.cyan);
		listPokemon = new ArrayList<PokemonDisplay>();
		createGui();
	}

	private void createGui(){
		for (int i = 0; i < 3; ++i){
			add(createPokemonDisplay());
		}
		
		JButton items = new JButton("Open Items Bag");
		items.setFocusable(false);
		items.setBorderPainted(false);
		items.setBackground(Color.cyan);
		add(items);
		
		JButton trainer = new JButton("Trainer Info");
		trainer.setFocusable(false);
		trainer.setBorderPainted(false);
		trainer.setBackground(Color.cyan);
		add(trainer);
	}
	
	private PokemonDisplay createPokemonDisplay(){
		PokemonDisplay p = new PokemonDisplay();
		listPokemon.add(p);
		return p;
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		MenuUI m = new MenuUI();
		j.add(new JScrollPane(m));
		j.setSize(300, 350);
		j.setLocation(100, 200);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

	public class PokemonDisplay extends JPanel{
		private static final long serialVersionUID = 1009609997809971667L;
		//pokemon instance
		private GridBagConstraints gbc;
		
		public PokemonDisplay(){
			setLayout(new GridBagLayout());
			setBackground(Color.cyan);
			createLayout();
		}
		
		private void createLayout(){
			gbc = new GridBagConstraints();
			gbc.gridwidth = 1;
			gbc.gridheight = 3;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.VERTICAL;
			JLabel image = new JLabel(new ImageIcon("res/Pokemon_sprites/dadizard_left_tr_small.png"));
			add(image, gbc);
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridheight = 1;
			gbc.gridx = 1;
			JLabel name = new JLabel("Dadizard");
			add(name, gbc);
			gbc.gridy = 1;
			JLabel level = new JLabel("Level " + "20");
			add(level, gbc);
			gbc.gridy = 2;
			JLabel hp = new JLabel("30/30");
			add(hp, gbc);
		}
	}//end PokemonDisplay class
}//end MenuUI class