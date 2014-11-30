package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.clientGUI.LoginScreen.LoginScreen;

public class WaitingPanel extends JPanel {
	private JLabel waitingLabel;
	public WaitingPanel() {
		setPreferredSize(new Dimension(500,600));
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		createWaitingGUI();
	}
	
	public void createWaitingGUI() {
		waitingLabel = new JLabel("Waiting for other players . . .");
		waitingLabel.setFont(new Font("Arial",Font.PLAIN, 30));
		final Image sexypodImage = Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/sexypod_left_tr.png");
		final ImageIcon sexypodImageIcon = new ImageIcon(sexypodImage);
		JLabel sexypodLabel = new JLabel(sexypodImageIcon);
		add(waitingLabel);
		add(sexypodLabel);
	}
	public static void main(String args[]) {
		WaitingPanel w = new WaitingPanel();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		//testWindow.setSize(500,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(w);
		testWindow.pack();
		testWindow.setVisible(true);
	}
}
