package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;




import javax.swing.JFrame;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dataStore.NetworkPlayer;

public class WinLossScreen extends JPanel {
	private static final long serialVersionUID = -3075043633848483329L;
	NetworkPlayer winner = null;
	NetworkPlayer loser = null;
	public WinLossScreen(NetworkPlayer winner, NetworkPlayer loser){
		super();
		this.winner = winner;
		this.loser = loser;
		setPreferredSize(new Dimension(500,600));
		createGui();
	}
	
	public WinLossScreen(){
		super();
		setPreferredSize(new Dimension(500,600));
		createGui();
	}
	
	private void createGui(){
		setLayout(new GridLayout(4,1));
		JLabel top = new JLabel();
		top.setBackground(Color.CYAN);
		top.setOpaque(true);
		add(top);
		JLabel winnerLabel;
		if (winner == null) {
			winnerLabel = new JLabel("Dummy0 won!");
		}
		else {
			winnerLabel = new JLabel(winner.getName() + " won!");
		}
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 50));
		winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winnerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		winnerLabel.setBackground(Color.white);
		add(winnerLabel);
		JLabel loserLabel;
		if (loser == null) {
			loserLabel = new JLabel("Dummy1 lost!");
		}
		else {
			loserLabel = new JLabel(loser.getName() + " lost!");
		}
		loserLabel.setFont(new Font("Arial", Font.BOLD, 50));
		loserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loserLabel.setVerticalAlignment(SwingConstants.NORTH);
		loserLabel.setBackground(Color.white);
		add(loserLabel);
		JLabel bottom = new JLabel();
		bottom.setBackground(Color.cyan);
		bottom.setOpaque(true);
		add(bottom);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new WinLossScreen());
		j.pack();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

}