package sidebarGUI;

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
					int index = listPokemon.indexOf(p);
					int newY = e.getYOnScreen();
					int oldY = p.getY();
					if ( newY < oldY && index != 0){
						p.canSwap = true;
						p.goUp = true;
						System.out.println("SWAP UP");
					}else if(newY > (oldY + 150) && index != (listPokemon.size() - 1)){
						p.canSwap = true;
						p.goUp = false;
						System.out.println("SWAP DOWN");
					}
				}
			});
		}
	}
	
	private PokemonDisplay createPokemonDisplay(){
		PokemonDisplay p = new PokemonDisplay();
		listPokemon.add(p);
		return p;
	}
	
//	public static void main(String[] args) {
//		JFrame j = new JFrame();
//		MenuUI m = new MenuUI();
//		j.add(new JScrollPane(m));
//		j.setSize(300, 350);
//		j.setLocation(100, 200);
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setVisible(true);
//	}

	public class PokemonDisplay extends JPanel{
		private static final long serialVersionUID = 1009609997809971667L;
		//pokemon instance
		private GridBagConstraints gbc;
		public boolean canSwap;
		public boolean goUp;
		
		public PokemonDisplay(){
			canSwap = false;
			goUp = false;
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