package client.clientGUI.LoginScreen;

import helper.MusicPlayer;

import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStore.Player;
import dataStore.Pokemon;

/* to be edited */
public class LoginScreen extends JPanel {
	private static final long serialVersionUID = -1434607477632566952L;
	private JTextField inputNameTextField;
	private JPanel characterSpriteListPanel;
	private JPanel pokemonSpriteListPanel;
	private List<JButton> pokemonSpriteList;
	private JButton readyButton;
	private ImageIcon chosenCharacter = new ImageIcon();
	private String chosenCharacterName;
	private ImageIcon chosenPokemon = new ImageIcon();
	
	private Queue<ImageIcon> chosenPokemonQueue = new LinkedList<ImageIcon>();
	private Vector<String> pokemonNamesList = new Vector<String>();
	
	private Player player;
	
	private boolean hasSelectedPokeman = false;
	public boolean done = false;
	public Clip clip = null;
	
	public LoginScreen() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		createLoginGUI();
		if (clip == null)
			clip = MusicPlayer.run("Menu.wav");
	}
	
	public void stop(){
		clip.stop();
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
		createCharacters();
	
		/* panel with default pokemans to choose from */
		JButton aerodonButton, lickisterButton, beetwoButton, marozardButton, meonxButton,
		geonxButton, weepintoiseButton, pikayuButton, sexypodButton, feelgletButton;
		JPanel pokemonSelectPanel = new JPanel();
		pokemonSelectPanel.setLayout(new GridBagLayout());
		pokemonSelectPanel.setMaximumSize(new Dimension(800,50));
		add(pokemonSelectPanel);
		JLabel pokemonSelectLabel = new JLabel("Select three Pokeman  ");
		pokemonSelectLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		final JButton pokemonSelectButton = new JButton("Select");
		pokemonSelectButton.setFont(new Font("Arial",Font.PLAIN, 15));
		pokemonSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				if (chosenPokemonQueue.size() == 3) {
					pokemonSelectButton.setEnabled(false);
					System.out.println("Current chosen pokemans: ");
					int numInQueue = chosenPokemonQueue.size();
					String [] descs = new String[3];
					for (int i = 0; i < numInQueue; i++) {
						ImageIcon temp = chosenPokemonQueue.remove();
						System.out.println("Pokemon " + i + ": " + temp);
						String tempDesc = temp.getDescription();
						descs[i] = tempDesc;
					}
					Component[] components = pokemonSpriteListPanel.getComponents();
					for (int k = 0; k < components.length; k++){
						boolean DONOTREMOVE = false;
						for (int j = 0; j < descs.length; ++j)
							if (((JButton)components[k]).getName().equalsIgnoreCase(descs[j])) {
								DONOTREMOVE = true;
								((JButton)components[k]).setBackground(Color.WHITE);
							}
						if (!DONOTREMOVE)
							pokemonSpriteListPanel.remove(pokemonSpriteList.get(k));
					}
			//	for (int j = 0; j < pokemonSpriteList.size(); j++) {
			//	if ( !tempDesc.equals(pokemonSpriteList.get(j).getName()) )  {
			//	pokemonSpriteListPanel.remove(pokemonSpriteList.get(j));
			//	
			//	}
			//	}
					hasSelectedPokeman = true;
					repaint();
				}
			}
		});
		pokemonSelectPanel.add(pokemonSelectLabel);
		pokemonSelectPanel.add(pokemonSelectButton);
		
		pokemonSpriteListPanel = new JPanel();
		pokemonSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		pokemonSpriteListPanel.setMaximumSize(new Dimension(600,30000));
		pokemonSpriteList = new ArrayList<JButton>();
		
		final Image aerodonImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Aerodon_left_tr_small.png");
		final ImageIcon aerodonImageIcon = new ImageIcon(aerodonImage,"Aerodon");
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
		            	//pokemonNamesList.add("Aerodon");
		            	}
		            	}
	            	}
	            	else if (chosenPokemonQueue.size() == 3) {
	            	System.out.println("chosenqueue size: " + chosenPokemonQueue.size());
	            	//pokemonNamesList.remove(0);//just remove the first name
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
	            	if(!pokemonNamesList.contains("Aerodon"))
	            		pokemonNamesList.add("Aerodon");
	            	
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
	            	pokemonNamesList.remove(0);//get rid of the first pokemon name
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
		
		final Image lickisterImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Lickister_left_tr_small.png");
		final ImageIcon lickisterImageIcon = new ImageIcon(lickisterImage,"Lickister");
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
	            	if(!pokemonNamesList.contains("Lickister"))
	            	pokemonNamesList.add("Lickister");
	            	
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
	            		pokemonNamesList.remove(0);
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
		
		final Image beetwoImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Bee-two_left_tr_small.png");
		final ImageIcon beetwoImageIcon = new ImageIcon(beetwoImage,"Bee-Two");
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
	            	if(!pokemonNamesList.contains("Bee-Two"))
	            	pokemonNamesList.add("Bee-Two");
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
	            		pokemonNamesList.remove(0);
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
		
		final Image marozardImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Marozard_left_tr_small.png");
		final ImageIcon marozardImageIcon = new ImageIcon(marozardImage,"Marozard");
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
	            	if(!pokemonNamesList.contains("Marozard"))
	            	pokemonNamesList.add("Marozard");
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
	            		pokemonNamesList.remove(0);
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
		
		final Image meonxImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Meonx_left_tr_small.png");
		final ImageIcon meonxImageIcon = new ImageIcon(meonxImage,"Meonx");
		meonxButton = new JButton(meonxImageIcon);
		meonxButton.setBackground(Color.WHITE);
		meonxButton.setName("Meonx");
		meonxButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(meonxImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(meonxImageIcon)) {
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
	            	chosenPokemon.setDescription("Meonx");
	            	if(!pokemonNamesList.contains("Meonx"))
	            	pokemonNamesList.add("Meonx");
	            	System.out.println("Selected Character: Meonx");
	            	chosenPokemon.setImage(meonxImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(meonxImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(meonxImageIcon)) {
		                    	chosenPokemonQueue.add(meonxImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            	pokemonNamesList.remove(0);
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
		
		pokemonSpriteList.add(meonxButton);
		
		final Image geonxImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Geonx_left_tr_small.png");
		final ImageIcon geonxImageIcon = new ImageIcon(geonxImage,"Geonx");
		geonxButton = new JButton(geonxImageIcon);
		geonxButton.setBackground(Color.WHITE);
		geonxButton.setName("Geonx");
		geonxButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(geonxImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(geonxImageIcon)) {
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
	            	chosenPokemon.setDescription("Geonx");
	            	if(!pokemonNamesList.contains("Geonx"))
	            	pokemonNamesList.add("Geonx");
	            	System.out.println("Selected Character: Geonx");
	            	chosenPokemon.setImage(geonxImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(geonxImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(geonxImageIcon)) {
		                    	chosenPokemonQueue.add(geonxImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            	pokemonNamesList.remove(0);
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
		
		pokemonSpriteList.add(geonxButton);
		
		final Image weepintoiseImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Weepintoise_left_tr_small.png");
		final ImageIcon weepintoiseImageIcon = new ImageIcon(weepintoiseImage,"Weepintoise");
		weepintoiseButton = new JButton(weepintoiseImageIcon);
		weepintoiseButton.setBackground(Color.WHITE);
		weepintoiseButton.setName("Weepintoise");
		weepintoiseButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(weepintoiseImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(weepintoiseImageIcon)) {
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
	            	chosenPokemon.setDescription("Weepintoise");
	            	if(!pokemonNamesList.contains("Weepintoise"))
	            	pokemonNamesList.add("Weepintoise");
	            	System.out.println("Selected Character: Weepintoise");
	            	chosenPokemon.setImage(weepintoiseImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(meonxImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(weepintoiseImageIcon)) {
		                    	chosenPokemonQueue.add(weepintoiseImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            	pokemonNamesList.remove(0);
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
		
		pokemonSpriteList.add(weepintoiseButton);
		
		final Image pikayuImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Pikayu_left_tr_small.png");
		final ImageIcon pikayuImageIcon = new ImageIcon(pikayuImage,"Pikayu");
		pikayuButton = new JButton(pikayuImageIcon);
		pikayuButton.setBackground(Color.WHITE);
		pikayuButton.setName("Pikayu");
		pikayuButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(pikayuImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(pikayuImageIcon)) {
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
	            	chosenPokemon.setDescription("Pikayu");
	            	if(!pokemonNamesList.contains("Pikayu"))
	            	pokemonNamesList.add("Pikayu");
	            	System.out.println("Selected Character: Pikayu");
	            	chosenPokemon.setImage(pikayuImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(pikayuImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(pikayuImageIcon)) {
		                    	chosenPokemonQueue.add(pikayuImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            	pokemonNamesList.remove(0);
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
		
		pokemonSpriteList.add(pikayuButton);
		
		final Image sexypodImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/sexypod_left_tr_small.png");
		final ImageIcon sexypodImageIcon = new ImageIcon(sexypodImage,"Sexypod");
		sexypodButton = new JButton(sexypodImageIcon);
		sexypodButton.setBackground(Color.WHITE);
		sexypodButton.setName("Sexypod");
		sexypodButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(sexypodImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(sexypodImageIcon)) {
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
	            	chosenPokemon.setDescription("Sexypod");
	            	if(!pokemonNamesList.contains("Sexypod"))
	            		pokemonNamesList.add("Sexypod");
	            	System.out.println("Selected Character: Sexypod");
	            	chosenPokemon.setImage(sexypodImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(sexypodImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(sexypodImageIcon)) {
		                    	chosenPokemonQueue.add(sexypodImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            		pokemonNamesList.remove(0);
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
		
		pokemonSpriteList.add(sexypodButton);
		
		final Image feelgletImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/feelglet_left_tr_small.png");
		final ImageIcon feelgletImageIcon = new ImageIcon(feelgletImage,"Feelglet");
		feelgletButton = new JButton(feelgletImageIcon);
		feelgletButton.setBackground(Color.WHITE);
		feelgletButton.setName("Feelglet");
		feelgletButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenPokemon.getDescription().equals("0")) {
	            	String chosenPokemonDesc = chosenPokemon.getDescription();
		            	if (chosenPokemonDesc.equals(feelgletImageIcon.getDescription())) {
		            	((JButton)me.getSource()).setBackground(Color.RED);
		            	}
		            	else ((JButton)me.getSource()).setBackground(Color.WHITE);
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (chosenPokemonQueue.contains(feelgletImageIcon)) {
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
	            	chosenPokemon.setDescription("Feelglet");
	            	if(!pokemonNamesList.contains("Feelglet"))
	            	pokemonNamesList.add("Feelglet");
	            	System.out.println("Selected Character: Feelglet");
	            	chosenPokemon.setImage(feelgletImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	if (chosenPokemonQueue.size() == 0) chosenPokemonQueue.add(feelgletImageIcon);
	            	else {
		            	for (int i = 0; i < chosenPokemonQueue.size(); i++) {
		            	if (!chosenPokemonQueue.contains(feelgletImageIcon)) {
		                    	chosenPokemonQueue.add(feelgletImageIcon);
		            	}
		            	}
	            	}
	            	if (chosenPokemonQueue.size() > 3) {
	            	ImageIcon toRemove = chosenPokemonQueue.remove();
	            	pokemonNamesList.remove(0);
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
		readyButton.setFont(new Font("Arial",Font.BOLD, 45));
		readyButton.setBackground(Color.GREEN);
		readyButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 for(int i=0;i<pokemonNamesList.size();++i){
					  System.out.println(pokemonNamesList.get(i) + " THESE NAMES ");
				  }
				 System.out.println("#####");
				
			  if(inputNameTextField.getText().length() != 0 && hasSelectedPokeman && !chosenCharacter.getDescription().equals("0")){
				  //create a player using default constructor and then set name
				  //Player player = new Player();
				 				  
				  Vector<Pokemon>pokemonList = new Vector<Pokemon>();
				  	for(int i=0;i<3;++i){
				  		Pokemon poke = new Pokemon();
				  		poke.setName(pokemonNamesList.get(i));
				  		pokemonList.add(poke);
				  }
//				  Vector<ImageIcon> imageList = new Vector<ImageIcon>();
//				  imageList.add(chosenCharacter);
				  if(chosenCharacter == null)
					  System.out.println("WHY IS CHOSEN CHARACTER NULL ");
				  player = new Player(pokemonList,/*imageList,*/chosenCharacterName, inputNameTextField.getText());
				  
				  done = true;
			  }
			}
		});
		
		readyPanel.add(readyButton);
		add(readyPanel);
	}
	
	private void createCharacters(){
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
		
		String[] imageLocations = {"res/Character_sprites/miller_small.png",
								   "res/Character_sprites/Crowley_small.png",
								   "res/Character_sprites/dolan_small.png",
								   "res/Character_sprites/gooby_small.png",
								   "res/Character_sprites/misty_small.png",
								   "res/Character_sprites/jungIll_small.png"
								  };
		String[] characterNames = {"Miller", "Crowley", "Dolan", "Gooby", "Misty", "JungIll"};
		ArrayList<JButton> characterButtons = new ArrayList<JButton>();
		
		for (int i = 0; i < characterNames.length; ++i){
			Image im = Toolkit.getDefaultToolkit().getImage(imageLocations[i]);
			ImageIcon imIcon = new ImageIcon(im, characterNames[i]);
			JButton button = new JButton(imIcon);
			button.setName(characterNames[i]);
			
			button.setBackground(Color.WHITE);
			button.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseReleased(MouseEvent arg0) {}           
	            @Override
	            public void mousePressed(MouseEvent arg0) {}            
	            @Override
	            public void mouseExited(MouseEvent me) { 
	            	if (!chosenCharacter.getDescription().equals("0")) {
		            	String chosenCharacterDesc = chosenCharacter.getDescription();
		            	if (chosenCharacterDesc.equals(((ImageIcon)((JButton)me.getSource()).getIcon()).getDescription()))
		            		((JButton)me.getSource()).setBackground(Color.RED);
		            	else
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
	            	chosenCharacter.setDescription(((JButton)me.getSource()).getName());
	            	System.out.println("Selected Character: " + ((JButton)me.getSource()).getName());
//		            	chosenCharacter.setImage(millerImage);
	            	((JButton)me.getSource()).setBackground(Color.RED);
	            	String buttonName = ((JButton) me.getSource()).getName();
	            	chosenCharacterName = buttonName;			
	            	//millerButton.setBackground(Color.white);
	            	Component[] buttons = characterSpriteListPanel.getComponents();
	            	for (int i = 0; i < buttons.length; ++i)
	            		if (!((JButton)buttons[i]).getName().equals(((JButton)me.getSource()).getName()))
	            			((JButton)buttons[i]).setBackground(Color.white);
	            	repaint();
	            }
	        });
			characterButtons.add(button);
			characterSpriteListPanel.add(button);
		}		
		add(characterSpriteListPanel);
	}
	
	public Player getPlayer(){
		return this.player;
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
