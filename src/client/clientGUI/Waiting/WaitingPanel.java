package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.clientGUI.LoginScreen.LoginScreen;

public class WaitingPanel extends JPanel {
	private JLabel waitingLabel;
	Image sexypodImage = null;
	public WaitingPanel() {
		try {
			sexypodImage = ImageIO.read(getClass().getResource("/Pokemon_sprites/Sexy-apod_left_tr.png"));
		} catch(IOException ioe) {
			System.out.println("fail reading sexypod in waiting");
		}
		setPreferredSize(new Dimension(500,600));
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		createWaitingGUI();
	}
	
	public void createWaitingGUI() {
		waitingLabel = new JLabel("Waiting for other players . . .");
		waitingLabel.setFont(new Font("Arial",Font.PLAIN, 30));
		final ImageIcon sexypodImageIcon = new ImageIcon(sexypodImage);
		JLabel sexypodLabel = new JLabel(sexypodImageIcon);
		add(waitingLabel);
		add(sexypodLabel);
	}
	public static void main(String args[]) {
		WaitingPanel w = new WaitingPanel();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(w);
		testWindow.pack();
		testWindow.setVisible(true);
	}
}
