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

public class GUI extends JFrame{
	public static  PrintWriter pw;
	public static  BufferedReader bf;
	public GUI(){
		//opening
//		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setLocation(100,100);
		OpeningPanel op = new OpeningPanel();
		add(op);
		System.out.println("OPENING ADDED");
		setVisible(true);
		while(!op.done)
			System.out.println("WAITING");
		System.out.println("OPENING COMPLETE");
		//remove opening panel by setting to null and add op login
		//login
		
		//createGUI(); client calls this
	
	}
	public void createGUI(String address, PrintWriter pw, BufferedReader bf){
		System.out.println("IN GUI CREATEGUI");
		removeAll();
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
		j.add(g);
		g.createGUI("192.168.0.106", pw, bf); //pass in printerwriter to everything in command cneter
		j.setVisible(true);
		//setvisible is before opening
	}
}
