package client.clientGUI.sidebarGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SideBar extends JPanel {
	private static final long serialVersionUID = 5707609256750142398L;

	public SideBar(String address){
	
		createGUI(address);
	}
	
	private void createGUI(String address){
		setLayout(new BorderLayout());
		add(new SideBarMenuAdapter());
		add(new ChatGUI(address), BorderLayout.SOUTH);
	}
	
}