package LoginScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public LoginScreen() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		createLoginGUI();
	}
	
	private void createLoginGUI() {
		
		/* name text input */
		JPanel nameLabelPanel = new JPanel();
		nameLabelPanel.setLayout(new GridBagLayout());
		nameLabelPanel.setMaximumSize(new Dimension(200,200));
		add(nameLabelPanel);
		JLabel nameLabel = new JLabel("Enter your name");
		nameLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		nameLabelPanel.add(nameLabel);
		inputNameTextField = new JTextField();
		inputNameTextField.setMaximumSize(new Dimension(500,50));
		
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
		characterSpriteList = new ArrayList<JButton>();
		
		Image millerImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/miller_small.png");
		ImageIcon millerImageIcon = new ImageIcon(millerImage);
		JButton millerButton = new JButton(millerImageIcon);
		millerButton.setBackground(Color.WHITE);
		millerButton.addMouseListener(new MouseListener() {            
            @Override
            public void mouseReleased(MouseEvent arg0) {}           
            @Override
            public void mousePressed(MouseEvent arg0) {}            
            @Override
            public void mouseExited(MouseEvent arg0) { 
                millerButton.setBackground(Color.WHITE);
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
                    millerButton.setBackground(Color.BLUE);
            }           
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });
		//millerButton.setMaximumSize(new Dimension(200,200));
		
		Image crowleyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/Crowley_small.png");
		ImageIcon crowleyImageIcon = new ImageIcon(crowleyImage);
		JButton crowleyButton = new JButton(crowleyImageIcon);
		crowleyButton.setBackground(Color.WHITE);
		
		Image dolanImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/dolan_small.png");
		ImageIcon dolanImageIcon = new ImageIcon (dolanImage);
		JButton dolanButton = new JButton(dolanImageIcon);
		dolanButton.setBackground(Color.WHITE);
		
		Image goobyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/gooby_small.png");
		ImageIcon goobyImageIcon = new ImageIcon (goobyImage);
		JButton goobyButton = new JButton(goobyImageIcon);
		goobyButton.setBackground(Color.WHITE);
		
		Image mistyImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/misty_small.png");
		ImageIcon mistyImageIcon = new ImageIcon (mistyImage);
		JButton mistyButton = new JButton(mistyImageIcon);
		mistyButton.setBackground(Color.WHITE);
		
		Image jungIllImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/jungIll_small.png");
		ImageIcon jungIllImageIcon = new ImageIcon (jungIllImage);
		JButton jungIllButton = new JButton(jungIllImageIcon);
		jungIllButton.setBackground(Color.WHITE);
		
		characterSpriteListPanel.add(millerButton);
		characterSpriteListPanel.add(crowleyButton);
		characterSpriteListPanel.add(dolanButton);
		characterSpriteListPanel.add(goobyButton);
		characterSpriteListPanel.add(mistyButton);
		characterSpriteListPanel.add(jungIllButton);
		
		add(characterSpriteListPanel);
		/* panel with default pokemans to choose from */
		JPanel pokemonSelectPanel = new JPanel();
		pokemonSelectPanel.setLayout(new GridBagLayout());
		pokemonSelectPanel.setMaximumSize(new Dimension(300,50));
		add(pokemonSelectPanel);
		JLabel pokemonSelectLabel = new JLabel("Choose your pokeman/s");
		pokemonSelectLabel.setFont(new Font("Arial",Font.PLAIN, 25));
		pokemonSelectPanel.add(pokemonSelectLabel);
		
		pokemonSpriteListPanel = new JPanel();
		pokemonSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		pokemonSpriteList = new ArrayList<JButton>();
		
		Image feelgletImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/feelglet_left_tr.png");
		ImageIcon feelgletImageIcon = new ImageIcon(feelgletImage);
		JButton feelgletButton = new JButton(feelgletImageIcon);
		
		Image magikunaImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/magikuna_left_tr.png");
		ImageIcon magikunaImageIcon = new ImageIcon(magikunaImage);
		JButton magikunaButton = new JButton(magikunaImageIcon);
		
		Image lickisterImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/lickister_left_tr.png");
		ImageIcon lickisterImageIcon = new ImageIcon(lickisterImage);
		JButton lickisterButton = new JButton(lickisterImageIcon);
		
		Image pikayuImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/pikayu_left_tr.png");
		ImageIcon pikayuImageIcon = new ImageIcon(pikayuImage);
		JButton pikayuButton = new JButton(pikayuImageIcon);
		
		pokemonSpriteListPanel.add(feelgletButton);
		pokemonSpriteListPanel.add(magikunaButton);
		pokemonSpriteListPanel.add(lickisterButton);
		pokemonSpriteListPanel.add(pikayuButton);
		add(pokemonSpriteListPanel);
		/* ready Button */
		JPanel readyPanel = new JPanel();
		readyPanel.setLayout(new GridBagLayout());
		readyPanel.setMaximumSize(new Dimension(300,50));
		readyButton = new JButton("READY");
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
