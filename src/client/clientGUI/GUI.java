package client.clientGUI;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BattleGUI.CommandCenterGUI;
import client.clientGUI.LoginScreen.LoginScreen;
import client.clientGUI.Opening.OpeningPanel;
import client.clientGUI.sidebarGUI.ChatGUI;
import client.clientGUI.sidebarGUI.SideBar;
import client.clientGUI.sidebarGUI.SideBarMenuAdapter;
import dataStore.Player;

public class GUI extends JFrame{
	public static  PrintWriter pw;
	public static  BufferedReader bf;
	
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	
	private LoginScreen l;
	public GUI(){
		//opening
//		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setLocation(100,100);
		OpeningPanel op = new OpeningPanel();
		op.run();
		add(op);
		
		System.out.println("OPENING ADDED");
		setVisible(true);
		
		while(!op.done)
			Thread.yield();//System.out.println("WAITING");
		System.out.println("OPENING COMPLETE");
		
		remove(op);
		op = null;
		l = new LoginScreen();
		add(l);
		revalidate();
		repaint();
		while(!l.done)
			Thread.yield();
		
	
		//remove opening panel by setting to null and add op login
		//login//
		
		//createGUI(); client calls this
		
		
	}
	
	public void createGUI(String address, PrintWriter pw, BufferedReader bf){
		System.out.println("IN GUI CREATEGUI");
		remove(l);
		l = null;
		JPanel leftContainer = new JPanel(new BorderLayout());
		add(new SideBar(address), BorderLayout.EAST);
		add(leftContainer, BorderLayout.CENTER);
		leftContainer.add(new CommandCenterGUI(), BorderLayout.SOUTH);
		validate();
		repaint();
	}
	
	//trying something
	public void createGUI(String address,ObjectOutputStream oos, ObjectInputStream ois){//same as above function but with objectstreams
		System.out.println("IN GUI CREATEGUI");
		remove(l);
		l = null;
		JPanel leftContainer = new JPanel(new BorderLayout());
		add(new SideBar(address), BorderLayout.EAST);
		add(leftContainer, BorderLayout.CENTER);
		leftContainer.add(new CommandCenterGUI(), BorderLayout.SOUTH);
		validate();
		repaint();
	}

	public Player getPlayer(){
		return l.getPlayer();
	}
		
	public static void main(String[] args) {
		/*JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(800, 600);
		*/
		GUI g = new GUI();
		System.out.println("CREATE ME");
		//j.add(g);
		//g.createGUI("192.168.0.106",oos,ois);
		g.createGUI("192.168.0.106", pw, bf); //pass in printerwriter to everything in command cneter
		g.setVisible(true);
		//j.setVisible(true);
		//setvisible is before opening
	}
}
