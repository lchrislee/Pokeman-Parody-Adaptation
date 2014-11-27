package BattleGUI;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionSelection extends JPanel {
	private static final long serialVersionUID = -1569591653741925028L;
	final String ACTIONS[] = {"Attack", "Switch", "Surrender"};
	private CardLayout selecter;
	private CommandCenterGUI central;
	
	public static Lock lock = new ReentrantLock();
	
	public ActionSelection(CardLayout s, CommandCenterGUI c){
		createGUI();
		selecter = s;
		central = c;
	}
	
	private void createGUI(){
		setLayout(new GridLayout(1,3));
		for (int i = 0; i < ACTIONS.length; ++i){
			JButton b = new JButton(ACTIONS[i]);
			b.setFont(new Font("Arial", Font.BOLD, 26));
			b.setFocusable(false);
			b.setFocusPainted(false);
			b.addActionListener(new ActionButtonListener());
			add(b);
		}
	}

//	public static void main(String[] args) {
//		JFrame j = new JFrame();
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setSize(500, 150);
//		j.add(new ActionSelection());
//		j.setVisible(true);
//	}
	
	private class ActionButtonListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()){
				case "Attack":
					ActionSelection.lock.lock();
					System.out.println("ATTACKED");
					ActionSelection.this.selecter.show(central, central.ATTACKSELECT);
					break;
				case "Switch":
//					//AttackSelection.this.central.text.getLock().lock();
					ActionSelection.lock.lock();
					lock.lock();
					System.out.println("SWITCHED");
					ActionSelection.this.selecter.show(central, central.SWITCH);
					
					break;
				case "Surrender":
					ActionSelection.lock.lock();
					System.out.println("SURRENDERED");
					ActionSelection.this.selecter.show(central, central.TEXT);
					ActionSelection.this.central.text.setText("You tried to run away!");
					
			}
		}
	}
}