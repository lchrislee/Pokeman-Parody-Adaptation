package client.clientGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BattleGUI.CommandCenterGUI;
import client.clientGUI.sidebarGUI.ChatGUI;
import client.clientGUI.sidebarGUI.SideBar;
import client.clientGUI.sidebarGUI.SideBarMenuAdapter;

public class GUI extends JPanel{
	public GUI(){
		//opening
		//login
		
		//createGUI(); client calls this
	
	}
	public void createGUI(String address){
		setLayout(new BorderLayout());
		JPanel leftContainer = new JPanel(new BorderLayout());
		add(new SideBar(address), BorderLayout.EAST);
		add(leftContainer, BorderLayout.CENTER);
		leftContainer.add(new CommandCenterGUI(), BorderLayout.SOUTH);		
	}

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(800, 600);
		GUI g = new GUI();
		g.createGUI("localhost");
		j.add(g);
		j.setVisible(true);
	}
}
