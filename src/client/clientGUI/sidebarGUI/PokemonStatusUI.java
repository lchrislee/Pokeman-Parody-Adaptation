package client.clientGUI.sidebarGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PokemonStatusUI extends JPanel {
	private static final long serialVersionUID = -2739616213660293286L;
	private JLabel lblImage;
	private JTextArea txeaInformation;
	private JTextArea txeaStats;
	private JTextArea txeaMovesList;
	private JButton btnRelease;
	private JButton btnBack;
	private String imageName = "";
	private String name;
	private int level;
	private int currentHP;
	private int maxHP;
	private int attack;
	private int defense;
	private int speed;
	private String[] attacks;
	private boolean canRelease;
	private GridBagConstraints gbc;
	private CardLayout switcher;
	private SideBarMenuAdapter parent;

	public PokemonStatusUI(){
		setLayout(new BorderLayout());
		name = "Magikuna";
		imageName = "res/Pokemon_sprites/Magikuna_left_tr.png";
		level = 20;
		currentHP = 43;
		maxHP = 50;
		attack = 23;
		defense = 12;
		speed = 50;
		attacks = new String[4];
		attacks[0] = "Pound";
		attacks[1] = "Splash";
		attacks[2] = "Harden";
		attacks[3] = "Tackle";
		canRelease = true;
		createGUI();
	}
	
	public PokemonStatusUI(CardLayout c, SideBarMenuAdapter p){
		switcher = c;
		parent = p;
		setLayout(new BorderLayout());
		name = "Magikuna";
		imageName = "res/Pokemon_sprites/Magikuna_left_tr.png";
		level = 20;
		currentHP = 43;
		maxHP = 50;
		attack = 23;
		defense = 12;
		speed = 50;
		attacks = new String[4];
		attacks[0] = "Pound";
		attacks[1] = "Splash";
		attacks[2] = "Harden";
		attacks[3] = "Tackle";
		canRelease = true;
		createGUI();
		addListeners();
	}
	//the constructor be low is an example of what would happen when a pokemon is passed in
	public PokemonStatusUI(String n, String im, int l, int c, int m, int a, int d, int s, String[] moves, boolean r){
		name = n;
		imageName = im;
		level = l;
		currentHP = c;
		maxHP = m;
		attack = a;
		defense = d;
		speed = s;
		attacks = new String[moves.length];
		for (int i = 0; i < moves.length; ++i)
			attacks[i] = moves[i];
		canRelease =r;
	}
	
	private void createGUI(){
		JPanel mainView = new JPanel(new GridBagLayout());
		lblImage = new JLabel();
		ImageIcon i = new ImageIcon(imageName);
		lblImage.setIcon(i);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 30;
		gbc.anchor = GridBagConstraints.EAST;
		mainView.add(lblImage, gbc);
		
		txeaInformation = new JTextArea();
		txeaInformation.setEditable(false);
		txeaInformation.setBackground(Color.green);
		txeaInformation.setFont(new Font("Arial", Font.PLAIN, 20));
		txeaInformation.setText("Name: " + name + "\nLevel: " + level + "\nHP: " + currentHP + "/" + maxHP);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		mainView.add(txeaInformation, gbc);
		
		txeaStats = new JTextArea();
		txeaStats.setEditable(false);
		txeaStats.setBackground(Color.RED);;
		txeaStats.setFont(new Font("Arial", Font.PLAIN, 24));
		txeaStats.setText("Attack: " + attack + "\nDefense: " + defense + "\nSpeed: " + speed);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = 2;
		gbc.gridwidth = 2;
		mainView.add(txeaStats, gbc);

		txeaMovesList = new JTextArea();
		txeaMovesList.setFont(new Font("Arial", Font.PLAIN, 20));
		txeaMovesList.setEditable(false);
		String movesList = "";
		for (int index = 0; index < attacks.length; ++index){
			movesList += attacks[index];
			if (index != attacks.length - 1)
				movesList += "\n";
		}
		txeaMovesList.setText(movesList);
		gbc.gridy = 2;
		mainView.add(txeaMovesList, gbc);

		JPanel bottom = new JPanel();
		btnBack = new JButton("Back");
		bottom.add(btnBack);
		
		if (canRelease){
			btnRelease = new JButton("Release");
			bottom.add(btnRelease);
		}
		
		add(mainView);
		add(bottom, BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.show(parent, parent.MENUNAME);
			}
		});
	}
}