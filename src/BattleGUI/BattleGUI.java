package BattleGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.clientGUI.sidebarGUI.SideBar;

public class BattleGUI extends JFrame{
	public BattleGUI(){
		createGUI();
		
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void createGUI(){
		setLayout(new BorderLayout());
		JPanel battlePnl = new JPanel();
		add(battlePnl, BorderLayout.NORTH);
		JPanel menuPnl = menu();
		add(menuPnl, BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		BattleGUI j = new BattleGUI();
	}
	
	public JPanel menu(){
		JPanel panel = new JPanel(new BorderLayout());
		JButton fightBtn = new JButton("Fight");
	//	JButton surrenderBtn = new JButton("Surrender");
		
		fightBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Object values[] = {"Punch", "Lick", "Kick", "Laugh"};
				Object defaultValue = "Lick";
				Object value = JOptionPane.showInputDialog(BattleGUI.this,
				"Input", "Input Dialog", JOptionPane.QUESTION_MESSAGE, null, values, defaultValue);
				
			
				
			}
		});
		panel.add(fightBtn);
	//	panel.add(surrenderBtn);
		return panel;
	
	}

}
