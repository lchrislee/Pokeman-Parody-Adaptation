package sidebarGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInformationGUI extends JPanel {
	private static final long serialVersionUID = -3880475893174044643L;
	private CardLayout switcher;
	private SideBarMenuAdapter parent;
	private JButton btnBack;

	public UserInformationGUI(){
		createGUI();
	}
	
	public UserInformationGUI(CardLayout c, SideBarMenuAdapter p){
		switcher = c;
		parent = p;
		createGUI();
		addListeners();
	}
	
	private void createGUI(){
		setLayout(new BorderLayout());
		btnBack = new JButton("Back");
		add(btnBack, BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.show(parent, parent.MENUNAME);
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame temp = new JFrame();
		temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		temp.setSize(300, 350);
		temp.add(new UserInformationGUI());
		temp.setVisible(true);
	}

}