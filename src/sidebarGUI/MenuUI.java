package sidebarGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuUI extends JPanel {
	private static final long serialVersionUID = 7397593496707020802L;
	
	private ArrayList<PokemonDisplay> listPokemon;
	//instance of player
	
	public MenuUI(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		listPokemon = new ArrayList<PokemonDisplay>();
		createGui();
	}

	private void createGui(){
		for (int i = 0; i < 3; ++i){
			add(createPokemonDisplay());
		}
	}
	
	private PokemonDisplay createPokemonDisplay(){
		PokemonDisplay p = new PokemonDisplay();
		add(p);
		return p;
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		MenuUI m = new MenuUI();
		j.add(m);
		j.setSize(300, 350);
		j.setLocation(100, 200);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

	public class PokemonDisplay extends JPanel{
		//pokemon instance
		private GridBagConstraints gbc;
		
		public PokemonDisplay(){
			setLayout(new GridBagLayout());
			createLayout();
		}
		
		private void createLayout(){
			gbc = new GridBagConstraints();
			gbc.gridwidth = 1;
			gbc.gridheight = 2;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.VERTICAL;
			JLabel image = new JLabel(new ImageIcon("res/Pokemon_sprites/dadizard_left_tr.png"));
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