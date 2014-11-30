package BattleGUI;

import java.awt.CardLayout;
import java.io.BufferedReader;
//did not finish switch
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private ArrayList<Pokemon> pokes = null;
	
	public CommandCenterGUI(){
		createGUI();
	}

	public CommandCenterGUI(PrintWriter p, BufferedReader bf){
		pw = p;
		br = bf;
		//getPokes();
		createGUI();
	}
	
	private void createGUI(){
		CardLayout switcher = new CardLayout();
		setLayout(switcher);
		//Move[] m = {new Move(50, 1, "Tackle"), new Move(100, 2, "HYPER BEAM"), new Move(60, 3, "Wing Attack"), new Move(0, 4, "Splash")};
		attacks = (pw == null ? new AttackSelection(pokes.get(0).getMoveList(), switcher, this) : new AttackSelection(pokes.get(0).getMoveList(), switcher, this, pw, br));
		add(attacks, ATTACKSELECT);
		text = (pw == null ? new TextScreen(this,switcher) : new TextScreen(this, switcher, pw, br));
		add(text, TEXT);
		selection = (pw == null ? new ActionSelection(switcher, this) : new ActionSelection(switcher, this, pw, br));
		add(selection, ACTION);
		switchPokemon = (pw == null ? new SwitchSelection(switcher, this) : null/*new SwitchSelection(switcher, this, pw, br, pokes)*/);
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