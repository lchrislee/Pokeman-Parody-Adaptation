package BattleGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * 
 */
import javax.swing.border.LineBorder;

import dataStore.Pokemon;

public class SwitchSelection extends JPanel{ //make this panel 300 wide by 150 tall
	private static final long serialVersionUID = 3499666410519520395L;
	private CardLayout selecter;
	private PrintWriter pw;
	private BufferedReader br;
	private CommandCenterGUI central;
	private ArrayList<Pokemon> pokemon;
	
	public SwitchSelection(CardLayout s, CommandCenterGUI c) {
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(500,150));
		selecter = s;
		central = c;
		createGUI();
	}
	
	public SwitchSelection(CardLayout s, CommandCenterGUI c, PrintWriter p, BufferedReader b) {
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(500,150));
		selecter = s;
		pw = p;
		br = b;
		central = c;
		
		try {
			pokemon = parse(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		createGUI();
	}
	
	private ArrayList<Pokemon> parse(String s){
		ArrayList<Pokemon> p = new ArrayList<Pokemon>();
		
		//pokemonName=levelNumber+currentHP_maxHP:pokemonName=levelNumber+currentHP_maxHP:pokemonName=levelNumber+currentHP_maxHP
		//create pokemon and put in p
		//name of pokemon, level, max hp, current hp, 
		int numPokes = 3;
		for(int i=0;i<numPokes;++i){
			String name;
			String level;
			String hp;
			String max;
			System.out.println(s +  "  SWITCH STRING ");
			if(s.contains(":")){
				name = s.substring(0,s.indexOf("="));
				level = s.substring(s.indexOf("=")+1,s.indexOf("+"));
				hp = s.substring(s.indexOf("+")+1,s.indexOf("_"));
				max = s.substring(s.indexOf("_")+1,s.indexOf(":"));
				s = s.substring(s.indexOf(":")+1,s.length());
			}
			
			else{
				name = s.substring(0,s.indexOf("="));
				level = s.substring(s.indexOf("=")+1,s.indexOf("+"));
				hp = s.substring(s.indexOf("+")+1,s.indexOf("_"));
				max = s.substring(s.indexOf("_")+1,s.length());				
			}		
			
			System.out.println(name + " NAME " + level + " LEVEL " + hp + " HP " + max + " MAX ");
			Pokemon poke = new Pokemon();
			poke.setName(name);
			String[] f = new String[4];
			f[0] = "/Pokemon_sprites/" + poke.getName() + "_left_tr_small.png";
			f[1] = "/Pokemon_sprites/" + poke.getName() + "_left_tr.png";
			f[2] = "/Pokemon_sprites/" + poke.getName() + "_right_tr_small.png";
			f[3] = "/Pokemon_sprites/" + poke.getName() + "_right_tr.png";
			poke.setFileNameArray(f);
			poke.setLevel(Integer.parseInt(level));
			poke.setHealth(Integer.parseInt(hp));
			poke.setMaxHealth(Integer.parseInt(max));
			p.add(poke);
			
		}
		return p;
	}
	
	private void createGUI() {
		for (int i = 0; i < 3; i++) {
			Pokemon p = null;
			if (pokemon != null)
				p = pokemon.get(i);
			String dummyName = "dummy " + String.valueOf(i);
			JPanel selectionAreaPanel = new JPanel();
			selectionAreaPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
			selectionAreaPanel.setLayout(new BorderLayout());

			JButton pokemonButton = null;
			
			if (pokemon == null){
				pokemonButton = new JButton(new ImageIcon("res/Pokemon_sprites/dadizard_left_tr.png"));
			}else{
				pokemonButton = new JButton(new ImageIcon(p.getFileNameArray()[0]));
			}
			if (pokemon == null)
				pokemonButton.setName(dummyName);
			else
				pokemonButton.setName(p.getName());
			
			pokemonButton.setPreferredSize(new Dimension(166, 150));
			pokemonButton.setLayout(new BorderLayout());
			JPanel panel = new JPanel();
			BoxLayout b = new BoxLayout(panel, BoxLayout.Y_AXIS);
			panel.setLayout(b);
			panel.setOpaque(false);
				JLabel nameLabel = null;
				JLabel levelLabel = null;
				JLabel healthLabel = null;
				
			if (pokemon == null){
				nameLabel = new JLabel("Dummy " + i);
				levelLabel = new JLabel(" Level 69");
				healthLabel = new JLabel("HP 69/420");
			}else{
				nameLabel = new JLabel(p.getName());
				levelLabel = new JLabel(String.valueOf(p.getLevel()));
				healthLabel = new JLabel(String.format("HP: %d/%d", p.getHealth(), p.getMaxHealth()));
			}
			
//			Color darkgreen = new Color(64,201,100);
			nameLabel.setFont(new Font("Arial",Font.BOLD, 18));
			nameLabel.setForeground(Color.BLUE);
			levelLabel.setFont(new Font("Arial",Font.BOLD, 18));
			levelLabel.setForeground(Color.BLUE);
			healthLabel.setFont(new Font("Arial",Font.BOLD, 18));
			healthLabel.setForeground(Color.BLUE);
			panel.add(nameLabel);
			panel.add(levelLabel);
			panel.add(healthLabel);
			pokemonButton.add(panel, BorderLayout.SOUTH);
			pokemonButton.setBackground(Color.WHITE);
			if (pokemon != null)
				pokemonButton.setName(p.getName());
			else
				pokemonButton.setName(String.valueOf(i));
			pokemonButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	((JButton)me.getSource()).setBackground(Color.WHITE);
	            }           
	            @Override
	            public void mouseEntered(MouseEvent me) {}    
	            @Override
	            public void mouseClicked(MouseEvent me) {
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	System.out.println("Pokemon name: " + ((JButton)me.getSource()).getName());
					SwitchSelection.this.central.text.setText("You switched to " + ((JButton)me.getSource()).getName() + "!");
					if (pw != null){
						pw.println("Sw_" + ((JButton)me.getSource()).getName());
						pw.flush();
					}
					
					String input = "";
					try {
						input = br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					SwitchSelection.this.central.text.setText(input);
					SwitchSelection.this.selecter.show(central, central.TEXT);
	            }
	        });
			selectionAreaPanel.add(pokemonButton);
			//selectionAreaPanel.add(nameLabel);
			//selectionAreaPanel.add(levelLabel);
			//selectionAreaPanel.add(healthLabel);
			
			add(selectionAreaPanel);
		}
	}
}