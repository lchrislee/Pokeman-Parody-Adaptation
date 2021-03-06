package helper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * 
 */




import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import client.clientGUI.Waiting.WaitingPanel;
import dataStore.Pokemon;

public class PokemonStatusHorizontalUI extends JPanel{ //make this panel 300 wide by 150 tall
	
	public PokemonStatusHorizontalUI() {
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(500,150));
		createHorizontalUI();
	}
	
	private void createHorizontalUI() {
		List<Pokemon> dummyPokemonList = new ArrayList<Pokemon>();
		for (int i = 0; i < 3; i++) {
			String dummyName = "dummy " + String.valueOf(i);
			JPanel selectionAreaPanel = new JPanel();
			selectionAreaPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
			selectionAreaPanel.setLayout(new BorderLayout());
			//selectionAreaPanel.setPreferredSize(new Dimension(166,150));
			
			//JPanel pokemonButtonPanel = new JPanel();
			Image pokemonImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/dadizard_left_tr.png");
			JButton dummyButton = new JButton(new ImageIcon("res/Pokemon_sprites/dadizard_left_tr.png"));
			dummyButton.setName(dummyName);
			dummyButton.setPreferredSize(new Dimension(166, 150));
			dummyButton.setLayout(new BorderLayout());
			JPanel panel = new JPanel();
			BoxLayout b = new BoxLayout(panel, BoxLayout.Y_AXIS);
			panel.setLayout(b);
			panel.setOpaque(false);
			JLabel nameLabel = new JLabel("Dummy " + i);
			JLabel levelLabel = new JLabel(" Level 69");
			JLabel healthLabel = new JLabel("HP 69/420");
			Color darkgreen = new Color(64,201,100);
			nameLabel.setFont(new Font("Arial",Font.BOLD, 18));
			nameLabel.setForeground(Color.BLUE);
			levelLabel.setFont(new Font("Arial",Font.BOLD, 18));
			levelLabel.setForeground(Color.BLUE);
			healthLabel.setFont(new Font("Arial",Font.BOLD, 18));
			healthLabel.setForeground(Color.BLUE);
			panel.add(nameLabel);
			panel.add(levelLabel);
			panel.add(healthLabel);
			dummyButton.add(panel, BorderLayout.SOUTH);
			dummyButton.setBackground(Color.WHITE);
//			dummyButton.setText();
//			dummyButton.setHorizontalTextPosition(SwingConstants.CENTER);
//			dummyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			dummyButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	((JButton)me.getSource()).setBackground(Color.WHITE);
	            }           
	            @Override
	            public void mouseEntered(MouseEvent me) {
	            	//((JButton)me.getSource()).setBackground(Color.BLUE);
	            }           
	            @Override
	            public void mouseClicked(MouseEvent me) {
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	System.out.println("Pokemon name: " + ((JButton)me.getSource()).getName());
	            }
	        });
			selectionAreaPanel.add(dummyButton);
			//selectionAreaPanel.add(nameLabel);
			//selectionAreaPanel.add(levelLabel);
			//selectionAreaPanel.add(healthLabel);
			
			add(selectionAreaPanel);
		}
	}
	public static void main(String args[]) {
		PokemonStatusHorizontalUI p = new PokemonStatusHorizontalUI();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		//testWindow.setSize(500,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(p);
		testWindow.pack();
		testWindow.setVisible(true);
	}
}
