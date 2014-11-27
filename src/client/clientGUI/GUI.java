package client.clientGUI;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BattleGUI.CommandCenterGUI;
import client.clientGUI.Opening.OpeningPanel;
import client.clientGUI.sidebarGUI.ChatGUI;
import client.clientGUI.sidebarGUI.SideBar;
import client.clientGUI.sidebarGUI.SideBarMenuAdapter;

public class GUI extends JPanel{
	public static  PrintWriter pw;
	public static  BufferedReader bf;
	public GUI(){
		//opening
		setLayout(new BorderLayout());
		OpeningPanel op = new OpeningPanel();
		add(op);
		//while(!op.done);
		//remove opening panel by setting to null and add op login
		//login
		
		//createGUI(); client calls this
	
	}
	public void createGUI(String address, PrintWriter pw, BufferedReader bf){
		
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
		g.createGUI("localhost", pw, bf); //pass in printerwriter to everything in command cneter
		j.add(g);
		j.setVisible(true);
		//setvisible is before opening
	}
}
