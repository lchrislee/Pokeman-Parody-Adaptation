package sidebarGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PokemonStatusGUI extends JPanel {
	private static final long serialVersionUID = -2739616213660293286L;
	
	private JLabel lblImage;
	private JTextArea txeaInformation;
	private JTextArea txeaStats;
	private JTextArea txeaMovesList;
	private JButton btnRelease;
	private String imageName = "";
	private GridBagConstraints gbc;

	public PokemonStatusGUI(){
		setLayout(new BorderLayout());
		imageName = "res/Pokemon_sprites/Magikuna_left_tr.png";
		createGUI();
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
		txeaInformation.setText("Name: Magikuna\nLevel: 20\nHP: 50/50");
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		mainView.add(txeaInformation, gbc);
		
		txeaStats = new JTextArea();
		txeaStats.setEditable(false);
		txeaStats.setBackground(Color.RED);;
		txeaStats.setFont(new Font("Arial", Font.PLAIN, 24));
		txeaStats.setText("Attack: 38\nDefense: 20\nSpeed: 50");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = 2;
		gbc.gridwidth = 2;
		mainView.add(txeaStats, gbc);

		txeaMovesList = new JTextArea();
		txeaMovesList.setFont(new Font("Arial", Font.PLAIN, 20));
		txeaMovesList.setEditable(false);
		txeaMovesList.setText("Splash\nTackle\nHarden\nPound");
		gbc.gridy = 2;
		mainView.add(txeaMovesList, gbc);

		btnRelease = new JButton("Release");
		add(btnRelease, BorderLayout.SOUTH);
		add(mainView);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new PokemonStatusGUI());
		j.setSize(300, 350);
		j.setLocation(100, 200);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

}