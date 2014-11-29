package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.clientGUI.LoginScreen.LoginScreen;

public class WaitingPanel extends JPanel {
	private JLabel waitingLabel;
	public WaitingPanel() {
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		createWaitingGUI();
	}
	
	public void createWaitingGUI() {
		waitingLabel = new JLabel("Waiting for other players . . .");
		waitingLabel.setFont(new Font("Arial",Font.PLAIN, 30));
//		Image sexyPod =
		add(waitingLabel);
	}
	public static void main(String args[]) {
		WaitingPanel w = new WaitingPanel();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Login");
		testWindow.setSize(500,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(w);
		testWindow.setVisible(true);
	}
}
