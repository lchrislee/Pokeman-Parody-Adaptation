package LoginScreen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
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
		inputNameTextField = new JTextField();
		
		
		add(inputNameTextField);
		
		/* panel with character sprites to choose from */
		characterSpriteListPanel = new JPanel();
		characterSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		characterSpriteList = new ArrayList<JButton>();
		try {
			Image img = ImageIO.read(getClass().getResource("/res/Character_sprites/miller_normal"));
		} 
		catch (IOException ioe) {
			System.out.println("Exception during reading Image; LoginScreen class createLoginGUI");
		}
		
		/* panel with default pokemans to choose from */
		pokemonSpriteListPanel = new JPanel();
		pokemonSpriteListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		pokemonSpriteList = new ArrayList<JButton>();
		
		/* ready Button */
		readyButton = new JButton("READY");
		add(readyButton);
	}
	
	public static void main() {
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
