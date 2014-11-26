package client.sidebarGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataStore.Pokemon;

public class MenuUI extends JPanel {
	private static final long serialVersionUID = 7397593496707020802L;
	
	private ArrayList<PokemonDisplay> listPokemon;
	private CardLayout switcher;
	private SideBarMenuAdapter parent;
	private JButton items;
	private JButton trainer;
	//instance of player
	
	public MenuUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.cyan);
		listPokemon = new ArrayList<PokemonDisplay>();
		createGui();
	}
	
	public MenuUI(CardLayout c, SideBarMenuAdapter p){
		parent = p;
		switcher = c;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.cyan);
		listPokemon = new ArrayList<PokemonDisplay>();
		createGui();
		addListeners();
	}

	private void createGui(){
		add(new JLabel("Drag Pokemon to reorder party."));
		for (int i = 0; i < 3; ++i){
			add(createPokemonDisplay());
		}
		
		items = new JButton("Open Items Bag");
		items.setFocusable(false);
		items.setBorderPainted(false);
		items.setBackground(Color.cyan);
		add(items);
		
		trainer = new JButton("Trainer Info");
		trainer.setFocusable(false);
		trainer.setBorderPainted(false);
		trainer.setBackground(Color.cyan);
		add(trainer);
	}
	
	private void addListeners(){
		items.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.show(parent, parent.ITEMNAME);
			}
		});
		
		trainer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.show(parent, parent.USERINFO);
			}
		});
		
		for (PokemonDisplay d : listPokemon){
			d.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					//TODO make shrink?
					PokemonDisplay p = (PokemonDisplay) e.getSource();
					if (p.getMouseLeftOff() != p.getY()){
						int index = listPokemon.indexOf(p);
						int newY = p.getMouseLeftOff();
						int oldY = p.getY() + MenuUI.this.getY();
						
						if (newY <= (oldY - 80) && index == (listPokemon.size() - 1)){ //only the bottom one can go up 2
							System.out.println(oldY + " vs " + newY);
							System.out.println("Swapped up 2");
							swap(index, -2);
						}else if (newY < oldY && index != 0){ //the index != 0 prevents switching up leading to index out of bounds
							System.out.println(oldY + " vs " + newY);
							System.out.println("Swapped up 1");
							swap(index, -1);
						}else if (newY >= (oldY + 380) && index == 0){ //only the top one can go down 2
							swap(index, 2);
							System.out.println("Swapped down 2");
							System.out.println(oldY + " vs " + newY);
						}else if(newY >= (oldY + 130) && index != (listPokemon.size() - 1)){ //index check prevents index out of bounds
							System.out.println(oldY + " vs " + newY);
							System.out.println("Swapped down 1");
							swap(index, 1);
						}
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					//TODO make grow?
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					//do nothing
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					//do nothing
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					switcher.show(parent, parent.STATUSNAME);
				}
			});
			d.addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					//do nothing
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					PokemonDisplay p = (PokemonDisplay) e.getSource();
					p.setMouseLeftOff(e.getYOnScreen());
				}
			});
		}
	}
	
	private PokemonDisplay createPokemonDisplay(){
		PokemonDisplay p = new PokemonDisplay();
		listPokemon.add(p);
		return p;
	}
	
	private void swap(int index, int amount){
		PokemonDisplay moved = listPokemon.get(index);
		PokemonDisplay stationary = listPokemon.get(index + amount);
		Pokemon old = moved.getPokemon();
		moved.setPokemon(stationary.getPokemon());
		stationary.setPokemon(old);
	}

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
			updateDisplay();
		}
		
		private void basicSetup(){
			setLayout(new GridBagLayout());
			setBackground(Color.cyan);
			createLayout();
		}
		
		public void setPokemon(Pokemon p){
			displayingPokemon = p;
			updateDisplay();
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
		
		private void updateDisplay(){
			
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
	}//end PokemonDisplay class
}//end MenuUI class