package client.clientGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	//800 x 600
	public GUI(){
		createGUI();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	private void createGUI(){
		setLayout(new BorderLayout());
		JPanel sidebarPnl = new JPanel();
		add(sidebarPnl, BorderLayout.EAST);
		JPanel commandCenterPnl =new JPanel();
		add(commandCenterPnl, BorderLayout.SOUTH);
		JPanel gamePnl = new JPanel();
		add(gamePnl, BorderLayout.NORTH);
		
	}
	public static void main(String[] args){
		GUI p = new GUI();
	}
}
