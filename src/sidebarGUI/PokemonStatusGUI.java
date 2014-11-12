package sidebarGUI;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PokemonStatusGUI extends JPanel {
	private static final long serialVersionUID = -2739616213660293286L;
	
	private JLabel lblImage;
	private JLabel lblSpeciesName;
	private JLabel lblLevel;
	private JLabel lblHP;
	private JLabel lblAttack;
	private JLabel lblDefense;
	private JLabel lblSpeed;
	private JLabel lblMovesList;
	private JButton btnRelease;

	public PokemonStatusGUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		createGUI();
	}
	
	private void createGUI(){
		lblImage = new JLabel(new ImageIcon("/res/Pokemon_sprites/Magikuna_left_tf.png"));
		lblSpeciesName = new JLabel("Name: Magikuna");
		
		lblLevel = new JLabel("Level: 20");
		lblHP = new JLabel("HP: 50/50");
		
		JPanel topRight = new JPanel();
		BoxLayout b = new BoxLayout(topRight, BoxLayout.Y_AXIS);
		topRight.setLayout(b);
		topRight.add(lblSpeciesName);
		topRight.add(lblLevel);
		topRight.add(lblHP);
		add(topRight);
//		
//		JPanel top = new JPanel(new BorderLayout());
//		top.add(lblImage);
//		top.add(topRight, BorderLayout.EAST);
		
		lblAttack = new JLabel("Attack: 38");
		lblDefense = new JLabel("Defense: 20");
		lblSpeed = new JLabel("Speed: 50");
		btnRelease = new JButton("");
		
		add(lblImage);
//		add(lblSpeciesName);
//		add(lblLevel);
//		add(lblHP);
		add(lblAttack);
		add(lblDefense);
		add(lblSpeed);
		add(btnRelease);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new PokemonStatusGUI());
		j.setSize(300, 400);
		j.setLocation(100, 200);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

}