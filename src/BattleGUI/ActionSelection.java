package BattleGUI;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionSelection extends JPanel {
	private static final long serialVersionUID = -1569591653741925028L;
	final String ACTIONS[] = {"Attack", "Switch", "Surrender"};
	private CardLayout selecter;
	private CommandCenterGUI central;
	private PrintWriter pw;
	private BufferedReader br;
	
	//private Lock lock = new ReentrantLock();
	
	public ActionSelection(CardLayout s, CommandCenterGUI c){
		createGUI();
		selecter = s;
		central = c;
	}
	
	public ActionSelection(CardLayout s, CommandCenterGUI c, PrintWriter p, BufferedReader b){
		createGUI();
		selecter = s;
		central = c;
		pw = p;
		br = b;
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
	
	private class ActionButtonListener implements ActionListener{//storing reference to actionselection
		
		
		
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()){
				case "Attack":
					
					System.out.println("ATTACKED");
					ActionSelection.this.selecter.show(central, central.ATTACKSELECT);
					break;
				case "Switch":
//				
					
					System.out.println("SWITCHED");
					ActionSelection.this.selecter.show(central, central.SWITCH);
					
					break;
				case "Surrender":
					
					System.out.println("SURRENDERED");
					if (ActionSelection.this.pw != null){
						System.out.println("SENDING SURRENDER MESSAGE ");
						ActionSelection.this.pw.println("Su");
						ActionSelection.this.pw.flush();
						System.out.println("SENt SURRENDER MESSAGE ");
					}
					
					String input = "";
					try {
						System.out.println("WAITING FOR INPUT IN ACTIONSELECTION.java");
						input = br.readLine();
						System.out.println(input + " SURRENDER INPUT ACTIONSELECTION.java");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if(input.equals("1"))
						input = "Player 2 wins!";
					else if(input.equals("2"))
						input = "Player 1 wins!";
					
					ActionSelection.this.central.text.setText(input);
					ActionSelection.this.selecter.show(central, central.TEXT);
//					ActionSelection.this.central.text.setText("You tried to run away!");
					System.out.println("ACTION SELECTION COMPLETE ACTIONSELECTION.JAVA");
			}
		}
	}
}