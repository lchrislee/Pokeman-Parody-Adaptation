package BattleGUI;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
//did not finish switch
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import client.clientGUI.GUI;
import dataStore.Move;
import dataStore.Pokemon;

public class CommandCenterGUI extends JPanel {
	private static final long serialVersionUID = 3387827640413193491L;
	public final String ATTACKSELECT = "ATTACKSELECTION";
	public final String TEXT = "TEXTSCREEN";
	public final String ACTION = "ACTIONSELECTION";
	public final String SWITCH = "SWITCHSELECTION";

	private AttackSelection attacks;
	TextScreen text;
	private ActionSelection selection;
	private SwitchSelection switchPokemon;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private GUI holder;
	private String playerName;
	
	public CommandCenterGUI(){
		createGUI();
	}

	public CommandCenterGUI(GUI h, PrintWriter p, BufferedReader bf, String name){
		holder = h;
		pw = p;
		br = bf;
		this.playerName = name;
		createGUI();
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
	}
	
	private void createGUI(){
		CardLayout switcher = new CardLayout();
		setLayout(switcher);
		Move[] m = {new Move(50,/* 1, */"Tackle"), new Move(100,/* 2, */"HYPER BEAM"), new Move(60,/* 3, */"Wing Attack"), new Move(0,/* 4, */"Splash")};
		attacks = (pw == null ? new AttackSelection(m, switcher, this) : new AttackSelection(switcher, this, pw, br));
		add(attacks, ATTACKSELECT);
		text = (pw == null ? new TextScreen(this,switcher) : new TextScreen(this, switcher, pw, br));
		add(text, TEXT);
		selection = (pw == null ? new ActionSelection(switcher, this) : new ActionSelection(switcher, this, pw, br));
		add(selection, ACTION);
		switchPokemon = (pw == null ? new SwitchSelection(switcher, this, playerName) : new SwitchSelection(switcher, this, pw, br));
		add(switchPokemon, SWITCH);
		switcher.show(this, ACTION);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500, 150);
		j.add(new CommandCenterGUI());
		j.setVisible(true);
	}

	protected void send(String m) {
		parseForSwitch(m);
		
		
		holder.send(m);
	}

	private void parseForSwitch(String m) {
		String mess = m.substring(0, 2);
		if(mess.equals("Sw")){
			int index = Integer.parseInt(m.substring(m.length()));
			attacks.replaceMoveGUI(attacks.getMoves(index));
		}
	}
	
}