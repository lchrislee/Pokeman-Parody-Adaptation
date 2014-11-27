package client.clientGUI.LoginScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/* to be edited */
public class LoginScreen extends JPanel {
	private JTextField inputNameTextField;
	private JPanel characterSpriteListPanel;
	private List<JButton> characterSpriteList;
	private JPanel pokemonSpriteListPanel;
	private List<JButton> pokemonSpriteList;
	private JButton readyButton;
	private ImageIcon chosenCharacter = new ImageIcon();
	private JButton millerButton, crowleyButton, goobyButton, dolanButton, mistyButton, jungIllButton;
	private ImageIcon chosenPokemon = new ImageIcon();
	private Queue<ImageIcon> chosenPokemonQueue = new LinkedList<ImageIcon>();
	private boolean hasSelectedPokeman = false;
	public LoginScreen() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		createLoginGUI();
	}
	
	private void createLoginGUI() {
		chosenCharacter.setDescription("0");
		chosenPokemon.setDescription("0");
		/* name text input */
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.setLayout(new GridBagLayout());
		nameLabelPanel.setMaximumSize(new Dimension(800,100));
		add(nameLabelPanel);
		JLabel nameLabel = new JLabel("Enter your name");
		nameLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		nameLabelPanel.add(nameLabel);
		inputNameTextField = new JTextField("");
		inputNameTextField.setMaximumSize(new Dimension(500,50));
		inputNameTextField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
            	if ( (inputNameTextField.getText()).length() == 0 ) {
            		System.out.println("Enter your name");
            	}
            	else {
            		System.out.println("Selected Name: " + inputNameTextField.getText());
            	}
            }
        });
		
		add(inputNameTextField);
		
		
		
		/* panel with character sprites to choose from */
		JPanel characterSelectPanel = new JPanel();
		characterSelectPanel.setLayout(new GridBagLayout());
		characterSelectPanel.setMaximumSize(new Dimension(300,300));
		add(characterSelectPanel);
		JLabel characterSelectLabel = new JLabel("Choose your character");
		characterSelectLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		characterSelectPanel.add(characterSelectLabel);
		
		characterSpriteListPanel = new JPanel();
		characterSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		characterSpriteListPanel.setPreferredSize(new Dimension(800,120));
		characterSpriteList = new ArrayList<JButton>();
		
		
		final Image millerImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/miller_small.png");
		final ImageIcon millerImageIcon = new ImageIcon(millerImage, "Miller");
		millerButton = new JButton(millerImageIcon);
		millerButton.setBackground(Color.WHITE);
		millerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenCharacterDesc = chosenCharacter.getDescription();
	            	if (chosenCharacterDesc.equals(millerImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("Miller");
            	System.out.println("Selected Character: Miller");
            	chosenCharacter.setImage(millerImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	//millerButton.setBackground(Color.white);
            	crowleyButton.setBackground(Color.white);
            	dolanButton.setBackground(Color.white);
            	goobyButton.setBackground(Color.white);
            	mistyButton.setBackground(Color.white);
            	jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		final Image crowleyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/Crowley_small.png");
		final ImageIcon crowleyImageIcon = new ImageIcon(crowleyImage, "Crowley");
		crowleyButton = new JButton(crowleyImageIcon);
		crowleyButton.setBackground(Color.WHITE);
		crowleyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenCharacterDesc = chosenCharacter.getDescription();
	            	if (chosenCharacterDesc.equals(crowleyImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("Crowley");
            	System.out.println("Selected Character: Crowley");
            	chosenCharacter.setImage(crowleyImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	millerButton.setBackground(Color.white);
            	//crowleyButton.setBackground(Color.white);
            	dolanButton.setBackground(Color.white);
            	goobyButton.setBackground(Color.white);
            	mistyButton.setBackground(Color.white);
            	jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		
		final Image dolanImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/dolan_small.png");
		final ImageIcon dolanImageIcon = new ImageIcon (dolanImage, "Dolan");
		dolanButton = new JButton(dolanImageIcon);
		dolanButton.setBackground(Color.WHITE);
		dolanButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenCharacterDesc = chosenCharacter.getDescription();
	            	if (chosenCharacterDesc.equals(dolanImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("Dolan");
            	System.out.println("Selected Character: Dolan");
            	chosenCharacter.setImage(dolanImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	millerButton.setBackground(Color.white);
            	crowleyButton.setBackground(Color.white);
            	//dolanButton.setBackground(Color.white);
            	goobyButton.setBackground(Color.white);
            	mistyButton.setBackground(Color.white);
            	jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		final Image goobyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/gooby_small.png");
		final ImageIcon goobyImageIcon = new ImageIcon (goobyImage, "Gooby");
		goobyButton = new JButton(goobyImageIcon);
		goobyButton.setBackground(Color.WHITE);
		goobyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenCharacterDesc = chosenCharacter.getDescription();
	            	if (chosenCharacterDesc.equals(goobyImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("Gooby");
            	System.out.println("Selected Character: Gooby");
            	chosenCharacter.setImage(goobyImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	millerButton.setBackground(Color.white);
            	crowleyButton.setBackground(Color.white);
            	dolanButton.setBackground(Color.white);
            	//goobyButton.setBackground(Color.white);
            	mistyButton.setBackground(Color.white);
            	jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		final Image mistyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/misty_small.png");
		final ImageIcon mistyImageIcon = new ImageIcon (mistyImage, "Misty");
		mistyButton = new JButton(mistyImageIcon);
		mistyButton.setBackground(Color.WHITE);
		mistyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenCharacter.getDescription();
	            	if (chosenPokemonDesc.equals(mistyImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("Misty");
            	System.out.println("Selected Character: Misty");
            	chosenCharacter.setImage(mistyImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	millerButton.setBackground(Color.white);
            	crowleyButton.setBackground(Color.white);
            	dolanButton.setBackground(Color.white);
            	goobyButton.setBackground(Color.white);
            	//mistyButton.setBackground(Color.white);
            	jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		final Image jungIllImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/jungIll_small.png");
		final ImageIcon jungIllImageIcon = new ImageIcon (jungIllImage, "JungIll");
		jungIllButton = new JButton(jungIllImageIcon);
		jungIllButton.setBackground(Color.WHITE);
		jungIllButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenCharacter.getDescription().equals("0")) {
	            	String chosenCharacterDesc = chosenCharacter.getDescription();
	            	if (chosenCharacterDesc.equals(jungIllImageIcon.getDescription())) ((JButton)me.getSource()).setBackground(Color.RED);
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenCharacter.setDescription("JungIll");
            	System.out.println("Selected Character: JungIll");
            	chosenCharacter.setImage(jungIllImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	millerButton.setBackground(Color.white);
            	crowleyButton.setBackground(Color.white);
            	dolanButton.setBackground(Color.white);
            	goobyButton.setBackground(Color.white);
            	mistyButton.setBackground(Color.white);
            	//jungIllButton.setBackground(Color.white);
            	repaint();
            }
        });
		
		characterSpriteListPanel.add(millerButton);
		characterSpriteListPanel.add(crowleyButton);
		characterSpriteListPanel.add(dolanButton);
		characterSpriteListPanel.add(goobyButton);
		characterSpriteListPanel.add(mistyButton);
		characterSpriteListPanel.add(jungIllButton);
		
		add(characterSpriteListPanel);
		
		
		
		/* panel with default pokemans to choose from */
		JButton aerodonButton, lickisterButton, beetwoButton, marozardButton, meonxButton,
				geonxButton, weepintoiseButton, pikayuButton, sexypodButton, feelgletButton;
		JPanel pokemonSelectPanel = new JPanel();
		pokemonSelectPanel.setLayout(new GridBagLayout());
		pokemonSelectPanel.setMaximumSize(new Dimension(800,50));
		add(pokemonSelectPanel);
		JLabel pokemonSelectLabel = new JLabel("Select three Pokeman  ");
		pokemonSelectLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		JButton pokemonSelectButton = new JButton("Select");
		pokemonSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				System.out.println("Current chosen pokemans: ");
				for (int i = 0; i < chosenPokemonQueue.size(); i++) {
					
				}
			}
		});
		pokemonSelectPanel.add(pokemonSelectLabel);
		pokemonSelectPanel.add(pokemonSelectButton);
		
		pokemonSpriteListPanel = new JPanel();
		pokemonSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		pokemonSpriteList = new ArrayList<JButton>();
		
		Image aerodonImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Aerodon_left_tr_small.png");
		ImageIcon aerodonImageIcon = new ImageIcon(aerodonImage,"Aerodon");
		aerodonButton = new JButton(aerodonImageIcon);
		aerodonButton.setBackground(Color.WHITE);
		aerodonButton.setName("Aerodon");
		aerodonButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
	            	if (chosenPokemonDesc.equals(aerodonImageIcon.getDescription())) {
	            		((JButton)me.getSource()).setBackground(Color.RED);
	            	}
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (chosenPokemonQueue.contains(aerodonImageIcon)) {
	            			((JButton)me.getSource()).setBackground(Color.RED);
	            		}
	            	}
            	}
            	else if (chosenPokemonQueue.size() == 3) {
            		System.out.println("chosenqueue size: " + chosenPokemonQueue.size());
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenPokemon.setDescription("Aerodon");
            	System.out.println("Selected Character: Aerodon");
            	chosenPokemon.setImage(aerodonImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(aerodonImageIcon);
            	else {
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (!chosenPokemonQueue.contains(aerodonImageIcon)) {
	                    	chosenPokemonQueue.add(aerodonImageIcon);
	            		}
	            	}
            	}
            	if (chosenPokemonQueue.size() > 3) {
            		ImageIcon toRemove = chosenPokemonQueue.remove();
            		String toRemoveDesc = toRemove.getDescription();
            		for (int i = 0; i < pokemonSpriteList.size(); i++) {
            			String toCompareDesc = pokemonSpriteList.get(i).getName();
            			if (toCompareDesc.equals(toRemoveDesc)) {
            				pokemonSpriteList.get(i).setBackground(Color.WHITE);
            			}
            		}
            	}
            	System.out.println("Size of chosen queue: " + chosenPokemonQueue.size());
            	repaint();
            }
        });
		
		pokemonSpriteList.add(aerodonButton);
		
		Image lickisterImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Lickister_left_tr_small.png");
		ImageIcon lickisterImageIcon = new ImageIcon(lickisterImage,"Lickister");
		lickisterButton = new JButton(lickisterImageIcon);
		lickisterButton.setBackground(Color.WHITE);
		lickisterButton.setName("Lickister");
		lickisterButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenPokemon.getDescription().equals("0")) {
            		String chosenPokemonDesc = chosenPokemon.getDescription();
	            	if (chosenPokemonDesc.equals(lickisterImageIcon.getDescription())) {
	            		((JButton)me.getSource()).setBackground(Color.RED);
	            	}
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (chosenPokemonQueue.contains(lickisterImageIcon)) {
	            			((JButton)me.getSource()).setBackground(Color.RED);
	            		}
	            	}
            	}
            	else if (chosenPokemonQueue.size() == 3) {
            		System.out.println("chosenqueue size: " + chosenPokemonQueue.size());
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenPokemon.setDescription("Lickister");
            	System.out.println("Selected Character: Lickister");
            	chosenPokemon.setImage(lickisterImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(lickisterImageIcon);
            	else {
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (!chosenPokemonQueue.contains(lickisterImageIcon)) {
	                    	chosenPokemonQueue.add(lickisterImageIcon);
	            		}
	            	}
            	}
            	if (chosenPokemonQueue.size() > 3) {
            		ImageIcon toRemove = chosenPokemonQueue.remove();
            		String toRemoveDesc = toRemove.getDescription();
            		for (int i = 0; i < pokemonSpriteList.size(); i++) {
            			String toCompareDesc = pokemonSpriteList.get(i).getName();
            			if (toCompareDesc.equals(toRemoveDesc)) {
            				pokemonSpriteList.get(i).setBackground(Color.WHITE);
            			}
            		}
            	}
            	System.out.println("Size of chosen queue: " + chosenPokemonQueue.size());
            	repaint();
            }
        });
		
		pokemonSpriteList.add(lickisterButton);
		
		Image beetwoImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Bee-two_left_tr_small.png");
		ImageIcon beetwoImageIcon = new ImageIcon(beetwoImage,"Bee-Two");
		beetwoButton = new JButton(beetwoImageIcon);
		beetwoButton.setBackground(Color.WHITE);
		beetwoButton.setName("Bee-Two");
		beetwoButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenPokemon.getDescription().equals("0")) {
            		String chosenPokemonDesc = chosenPokemon.getDescription();
	            	if (chosenPokemonDesc.equals(beetwoImageIcon.getDescription())) {
	            		((JButton)me.getSource()).setBackground(Color.RED);
	            	}
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (chosenPokemonQueue.contains(beetwoImageIcon)) {
	            			((JButton)me.getSource()).setBackground(Color.RED);
	            		}
	            	}
            	}
            	else if (chosenPokemonQueue.size() == 3) {
            		System.out.println("chosenqueue size: " + chosenPokemonQueue.size());
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenPokemon.setDescription("Bee-Two");
            	System.out.println("Selected Character: Bee-Two");
            	chosenPokemon.setImage(beetwoImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(beetwoImageIcon);
            	else {
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (!chosenPokemonQueue.contains(beetwoImageIcon)) {
	                    	chosenPokemonQueue.add(beetwoImageIcon);
	            		}
	            	}
            	}
            	if (chosenPokemonQueue.size() > 3) {
            		ImageIcon toRemove = chosenPokemonQueue.remove();
            		String toRemoveDesc = toRemove.getDescription();
            		for (int i = 0; i < pokemonSpriteList.size(); i++) {
            			String toCompareDesc = pokemonSpriteList.get(i).getName();
            			if (toCompareDesc.equals(toRemoveDesc)) {
            				pokemonSpriteList.get(i).setBackground(Color.WHITE);
            			}
            		}
            	}
            	System.out.println("Size of chosen queue: " + chosenPokemonQueue.size());
            	repaint();
            }
        });
		
		pokemonSpriteList.add(beetwoButton);
		
		Image marozardImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Marozard_left_tr_small.png");
		ImageIcon marozardImageIcon = new ImageIcon(marozardImage,"Marozard");
		marozardButton = new JButton(marozardImageIcon);
		marozardButton.setBackground(Color.WHITE);
		marozardButton.setName("Marozard");
		marozardButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent me) { 
            	if (!chosenPokemon.getDescription().equals("0")) {
            		String chosenPokemonDesc = chosenPokemon.getDescription();
	            	if (chosenPokemonDesc.equals(marozardImageIcon.getDescription())) {
	            		((JButton)me.getSource()).setBackground(Color.RED);
	            	}
	            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (chosenPokemonQueue.contains(marozardImageIcon)) {
	            			((JButton)me.getSource()).setBackground(Color.RED);
	            		}
	            	}
            	}
            	else if (chosenPokemonQueue.size() == 3) {
            		System.out.println("chosenqueue size: " + chosenPokemonQueue.size());
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            	else {
            		((JButton)me.getSource()).setBackground(Color.WHITE);
            	}
            }           
            @Override
            public void mouseEntered(MouseEvent me) {
            	((JButton)me.getSource()).setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent me) {
            	chosenPokemon.setDescription("Marozard");
            	System.out.println("Selected Character: Marozard");
            	chosenPokemon.setImage(marozardImage);
            	((JButton)me.getSource()).setBackground(Color.RED);
            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(marozardImageIcon);
            	else {
	            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
	            		if (!chosenPokemonQueue.contains(marozardImageIcon)) {
	                    	chosenPokemonQueue.add(marozardImageIcon);
	            		}
	            	}
            	}
            	if (chosenPokemonQueue.size() > 3) {
            		ImageIcon toRemove = chosenPokemonQueue.remove();
            		String toRemoveDesc = toRemove.getDescription();
            		for (int i = 0; i < pokemonSpriteList.size(); i++) {
            			String toCompareDesc = pokemonSpriteList.get(i).getName();
            			if (toCompareDesc.equals(toRemoveDesc)) {
            				pokemonSpriteList.get(i).setBackground(Color.WHITE);
            			}
            		}
            	}
            	System.out.println("Size of chosen queue: " + chosenPokemonQueue.size());
            	repaint();
            }
        });
		pokemonSpriteList.add(marozardButton);
		
		Image meonxImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Meonx_left_tr_small.png");
		ImageIcon meonxImageIcon = new ImageIcon(meonxImage,"Meonx");
		meonxButton = new JButton(meonxImageIcon);
		meonxButton.setBackground(Color.WHITE);
		meonxButton.setName("Meonx");
		
		pokemonSpriteList.add(meonxButton);
		
		Image geonxImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Geonx_left_tr_small.png");
		ImageIcon geonxImageIcon = new ImageIcon(geonxImage,"Geonx");
		geonxButton = new JButton(geonxImageIcon);
		geonxButton.setBackground(Color.WHITE);
		geonxButton.setName("Geonx");
		
		pokemonSpriteList.add(geonxButton);
		
		Image weepintoiseImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Weepintoise_left_tr_small.png");
		ImageIcon weepintoiseImageIcon = new ImageIcon(weepintoiseImage,"Weepintoise");
		weepintoiseButton = new JButton(weepintoiseImageIcon);
		weepintoiseButton.setBackground(Color.WHITE);
		weepintoiseButton.setName("Weepintoise");
		
		pokemonSpriteList.add(weepintoiseButton);
		
		Image pikayuImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Pikayu_left_tr_small.png");
		ImageIcon pikayuImageIcon = new ImageIcon(pikayuImage,"Pikayu");
		pikayuButton = new JButton(pikayuImageIcon);
		pikayuButton.setBackground(Color.WHITE);
		pikayuButton.setName("Pikayu");
		
		pokemonSpriteList.add(pikayuButton);
		
		Image sexypodImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/sexypod_left_tr_small.png");
		ImageIcon sexypodImageIcon = new ImageIcon(sexypodImage,"Sexypod");
		sexypodButton = new JButton(sexypodImageIcon);
		sexypodButton.setBackground(Color.WHITE);
		sexypodButton.setName("Sexypod");
		
		pokemonSpriteList.add(sexypodButton);
		
		Image feelgletImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/feelglet_left_tr_small.png");
		ImageIcon feelgletImageIcon = new ImageIcon(feelgletImage,"Feelglet");
		feelgletButton = new JButton(feelgletImageIcon);
		feelgletButton.setBackground(Color.WHITE);
		feelgletButton.setName("Feelglet");
		
		pokemonSpriteList.add(feelgletButton);
		
		System.out.println("Size of pokemanlist: " + pokemonSpriteList.size());
		
		pokemonSpriteListPanel.add(aerodonButton);
		pokemonSpriteListPanel.add(lickisterButton);
		pokemonSpriteListPanel.add(beetwoButton);
		pokemonSpriteListPanel.add(marozardButton);
		pokemonSpriteListPanel.add(meonxButton);
		pokemonSpriteListPanel.add(geonxButton);
		pokemonSpriteListPanel.add(weepintoiseButton);
		pokemonSpriteListPanel.add(pikayuButton);
		pokemonSpriteListPanel.add(sexypodButton);
		pokemonSpriteListPanel.add(feelgletButton);
		add(pokemonSpriteListPanel);
		/* ready Button */
		JPanel readyPanel = new JPanel();
		readyPanel.setLayout(new GridBagLayout());
		readyPanel.setMaximumSize(new Dimension(300,50));
		readyButton = new JButton("READY");
		readyButton.setFont(new Font("Arial",Font.BOLD, 25));
		readyPanel.add(readyButton);
		add(readyPanel);
	}
	
	public static void main(String[] args) {
		LoginScreen ls = new LoginScreen();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		testWindow.setSize(800,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(ls);
		testWindow.setVisible(true);
	}
}

