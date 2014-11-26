package BattleGUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionSelection extends JPanel {
	private static final long serialVersionUID = -1569591653741925028L;
	final String ACTIONS[] = {"Attack", "Switch", "Surrender"};
	
	public ActionSelection(){
		createGUI();
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

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500, 150);
		j.add(new ActionSelection());
		j.setVisible(true);
	}
	
	private class ActionButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(((JButton)e.getSource()).getText()){
				case "Attack":
					System.out.println("ATTACKED");
					break;
				case "Switch":
					System.out.println("SWITCHED");
					break;
				case "Surrender":
					System.out.println("SURRENDERED");
					break;
			}
		}
	}
}