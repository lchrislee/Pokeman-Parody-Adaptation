package helper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			String dummyName = "dummy";
			JPanel selectionAreaPanel = new JPanel();
			selectionAreaPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
			//selectionAreaPanel.setLayout(new BoxLayout(selectionAreaPanel, BoxLayout.X_AXIS));
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
			panel.add(new JLabel("Dummy " + i));
			panel.add(new JLabel(" Level 69"));
			panel.add(new JLabel(" HP 69/420"));
			dummyButton.add(panel, BorderLayout.SOUTH);
//			dummyButton.setText();
//			dummyButton.setHorizontalTextPosition(SwingConstants.CENTER);
//			dummyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
//			JLabel nameLabel = new JLabel("Dummy " + i);
//			JLabel levelLabel = new JLabel(" Level 69");
//			JLabel healthLabel = new JLabel("HP 69/420");
			dummyButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	//String name = 
	            	System.out.println("");
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
