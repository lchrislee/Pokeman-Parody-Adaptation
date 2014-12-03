package client.clientGUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import BattleGUI.BattleScreen;
import BattleGUI.CommandCenterGUI;
import client.clientGUI.LoginScreen.LoginScreen;
import client.clientGUI.Opening.OpeningPanel;
import client.clientGUI.Waiting.WaitingPanel;
import client.clientGUI.sidebarGUI.ChatGUI;
import client.clientGUI.sidebarGUI.SideBar;
import client.clientGUI.sidebarGUI.SideBarMenuAdapter;
import dataStore.Player;
public class GUI extends JFrame {
	public static PrintWriter pw;
	public static BufferedReader bf;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private LoginScreen l;
	private String playerName;
	private BattleScreen bs = null;
	private WaitingPanel waiting;
	public GUI(PrintWriter p, BufferedReader b) {
		// opening
		// setLayout(new BorderLayout());
		pw = p;
		bf = b;
		setTitle("Pokeman!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setLocation(100, 100);
		// OpeningPanel op = new OpeningPanel();
		// // op.run();
		// add(op);
		//
		// System.out.println("OPENING ADDED");
		setVisible(true);
		//
		// while(!op.done)
		// Thread.yield();//System.out.println("WAITING");
		// op.stop();
		// System.out.println("OPENING COMPLETE");
		//
		// remove(op);
		// op = null;
		l = new LoginScreen();
		add(l);
		l.setNameFocus();
		revalidate();
		repaint();
		System.out.println("BEFORE L.DONE WHILE");
		while (!l.done)
			Thread.yield();
		l.stop();
		// remove(l);
		System.out.println("L IS FINISHED");
		System.out.println("BEFORE WAITING");
		remove(l);
		waiting = new WaitingPanel();
		add(waiting);
		revalidate();
		repaint();
		System.out.println("BEFORE BATTLE SCREEn");
		/*
		 * while(!waiting.done) Thread.yield(); waiting.done();
		 */
	}
	public void createGUI(String address) {
		System.out.println("IN GUI CREATEGUI");
		// remove(l);
		// l = null;
		
		//remove(waiting);
		System.err.println("GUI CREATEGUI AFTER REMOVING WAITING");

		// try {
		// System.out.println(bf.readLine());
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		JPanel leftContainer = new JPanel(new BorderLayout());
		
		leftContainer.add(new CommandCenterGUI(this, pw, bf, playerName),
				BorderLayout.SOUTH);
//		repaint();
//		revalidate();
		try {
			String action = bf.readLine();
			System.out.println(action + " THIS ACTION!!!! ");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		remove(waiting);
		//repaint();
		
		System.out.println("REMOVED WAITING");
		switchToBattleScreen(leftContainer);
		add(leftContainer, BorderLayout.CENTER);
		System.out.println("waiting to read");
		
		l = null;
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		repaint();
		revalidate();
	}
	public void createChat(String address) {
		add(new SideBar(address, playerName), BorderLayout.EAST);
	}
	public Player getPlayer() {
		Player p = l.getPlayer();
		playerName = p.getName();
		return p;
	}
	public static void main(String[] args) {
		/*
		 * JFrame j = new JFrame();
		 * j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); j.setSize(800,
		 * 600); //
		 */
		// GUI g = new GUI();
		// System.out.println("CREATE ME");
		// //j.add(g);
		// //g.createGUI("192.168.0.106",oos,ois);
		// g.createGUI("192.168.0.106", pw, bf); //pass in printerwriter to
		// everything in command cneter
		// g.setVisible(true);
		// //j.setVisible(true);
		// //setvisible is before opening
	}
	public void send(String m) {// work on this when u get back
		if (m.contains("END")) {
			switchToWaitingScreen();
		}
		// for a specfici message->send the message to the gui (through the ccg)
		// at gui...parse message
		// if its end message->change the battle screen (entire GUI) to
		// something
		// else keep whatever's there
		// battleScreen.update(m);
	}
	public void switchToBattleScreen(JPanel leftContainer) {
	
		if (bs == null)
			bs = new BattleScreen(pw, bf, playerName);

		leftContainer.add(bs,BorderLayout.CENTER);
	
		System.out.println("ADDED BATTLE SCREEN");
	}
	
	private void switchToWaitingScreen() {
		remove(bs);
		add(waiting);
		revalidate();
	}
}
