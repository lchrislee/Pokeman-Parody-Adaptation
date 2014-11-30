package BattleGUI;

import java.awt.CardLayout;
import java.io.BufferedReader;
//did not finish switch
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private PrintWriter pw;
	private BufferedReader br;
	private ArrayList<Pokemon> pokes;
	
	public CommandCenterGUI(){
		createGUI();
	}

	public CommandCenterGUI(PrintWriter p, BufferedReader bf){
		pw = p;
		br = bf;
		getPokes();
		createGUI();
	}
	
	private void createGUI(){
		CardLayout switcher = new CardLayout();
		setLayout(switcher);
		Move[] m = {new Move(50, 1, "Tackle"), new Move(100, 2, "HYPER BEAM"), new Move(60, 3, "Wing Attack"), new Move(0, 4, "Splash")};
		attacks = (pw == null ? new AttackSelection(m, switcher, this) : new AttackSelection(m, switcher, this, pw));
		add(attacks, ATTACKSELECT);
		text = (pw == null ? new TextScreen(this,switcher) : new TextScreen(this, switcher, pw));
		add(text, TEXT);
		selection = (pw == null ? new ActionSelection(switcher, this) : new ActionSelection(switcher, this, pw));
		add(selection, ACTION);
		switchPokemon = (pw == null ? new SwitchSelection(switcher, this) : new SwitchSelection(switcher, this, pw));
		add(switchPokemon, SWITCH);
		switcher.show(this, ACTION);
	}
	
	private void getPokes(){
		
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500, 150);
		j.add(new CommandCenterGUI());
		j.setVisible(true);
	}
	
}