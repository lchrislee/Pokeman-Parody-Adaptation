package BattleGUI;

import java.awt.CardLayout;
//did not finish switch

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dataStore.Move;

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
	
	public CommandCenterGUI(){
		createGUI();
	}
	
	private void createGUI(){
		CardLayout switcher = new CardLayout();
		setLayout(switcher);
		Move[] m = {new Move(50, 1, "Tackle"), new Move(100, 2, "HYPER BEAM"), new Move(60, 3, "Wing Attack"), new Move(0, 4, "Splash")};
		attacks = new AttackSelection(m, switcher, this);
		add(attacks, ATTACKSELECT);
		text = new TextScreen(this,switcher);
		add(text, TEXT);
		selection = new ActionSelection(switcher, this);
		add(selection, ACTION);
		switchPokemon = new SwitchSelection(switcher, this);
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
	
}


