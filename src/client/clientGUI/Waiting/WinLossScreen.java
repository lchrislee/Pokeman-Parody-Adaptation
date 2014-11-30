package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WinLossScreen extends JPanel {
	private static final long serialVersionUID = -3075043633848483329L;

	public WinLossScreen(){
		super();
		createGui();
	}
	
	private void createGui(){
		setLayout(new GridLayout(4,1));
		JLabel top = new JLabel();
		top.setBackground(Color.cyan);
		top.setOpaque(true);
		add(top);
		JLabel winner = new JLabel("Jiwoo won!");
		winner.setFont(new Font("Arial", Font.BOLD, 70));
		winner.setHorizontalAlignment(SwingConstants.CENTER);
		winner.setVerticalAlignment(SwingConstants.BOTTOM);
		winner.setBackground(Color.white);
		add(winner);
		JLabel loser = new JLabel("Alan lost!");
		loser.setFont(new Font("Arial", Font.BOLD, 70));
		loser.setHorizontalAlignment(SwingConstants.CENTER);
		loser.setVerticalAlignment(SwingConstants.NORTH);
		loser.setBackground(Color.white);
		add(loser);
		JLabel bottom = new JLabel();
		bottom.setBackground(Color.cyan);
		bottom.setOpaque(true);
		add(bottom);
	}
	
//	public static void main(String[] args) {
//		JFrame j = new JFrame();
//		j.add(new WinLossScreen());
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setSize(500, 600);
//		j.setVisible(true);
//	}

}