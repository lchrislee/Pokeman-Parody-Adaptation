
package BattleGUI;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import dataStore.Move;

public class AttackSelection extends JPanel {
	private static final long serialVersionUID = 7650587763939250380L;
	private CardLayout selecter;
	private CommandCenterGUI central;
	private PrintWriter pw;
	
	public AttackSelection(Vector<Move> m, CardLayout s, CommandCenterGUI c){
		createGUI(m);
		selecter = s;
		central = c;
	}
	
	public AttackSelection(Vector<Move> m, CardLayout s, CommandCenterGUI c, PrintWriter p){
		createGUI(m);
		selecter = s;
		central = c;
		pw = p;
	}
	
	private void createGUI(Vector<Move> m){
		setLayout(new GridLayout(2,2));
		for (int i = 0; i < m.size(); ++i)
			add(new AttackButton(m.get(i)));
	}
	
	private class AttackButton extends JButton{
		private static final long serialVersionUID = -451514209159278415L;
		private Move move;
		
		public AttackButton(Move m){
			super();
			move = m;
			setFont(new Font("Arial", Font.BOLD, 22));
			setFocusPainted(false);
			setFocusable(false);
			setText(move.getName());
			setName(String.valueOf(move.getDamage()));
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(move.getName());
					if (AttackSelection.this.pw != null){
						AttackSelection.this.pw.println("At_" + ((JButton)e.getSource()).getText() + "|" + ((JButton)e.getSource()).getName());
						AttackSelection.this.pw.flush();
					}
					AttackSelection.this.central.text.setText("You used " + move.getName() + "!");
					AttackSelection.this.selecter.show(central, central.TEXT);
				}
			});
		}
		
	}
}