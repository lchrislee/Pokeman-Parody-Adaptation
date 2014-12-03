package client.clientGUI.sidebarGUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SideBar extends JPanel {
	private static final long serialVersionUID = 5707609256750142398L;

	public SideBar(String address, String name){
		createGUI(address, name);
	}
	
	private void createGUI(String address, String name){
		setLayout(new BorderLayout());
//		add(new SideBarMenuAdapter());
		add(new ChatGUI(address, name)/*, BorderLayout.SOUTH*/);
	}
}