package BattleGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dataStore.Move;

public class AttackSelection extends JPanel {
	private static final long serialVersionUID = 7650587763939250380L;
	public AttackSelection(Move[] m){
		createGUI(m);
	}
	
	private void createGUI(Move[] m){
		setLayout(new GridLayout(2,2));
		for (int i = 0; i < m.length; ++i)
			add(new AttackButton(m[i]));
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500, 150);
		
		Move[] m = {new Move(50, 1, "Tackle"), new Move(100, 2, "HYPER BEAM"), new Move(60, 3, "Wing Attack"), new Move(0, 4, "Splash")};
		j.add(new AttackSelection(m));
		j.setVisible(true);
	}
	
	private class AttackButton extends JButton{
		private static final long serialVersionUID = -451514209159278415L;
		private Move move;
		
		public AttackButton(Move m){
			super();
			move = m;
			setText(move.getName());
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(move);
				}
			});
		}
		
	}
}