package client.sidebarGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SideBar extends JPanel {
	private static final long serialVersionUID = 5707609256750142398L;

	public SideBar(){
		String address = JOptionPane.showInputDialog(this, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		createGUI(address);
	}
	
	private void createGUI(String address){
		setLayout(new BorderLayout());
		add(new SideBarMenuAdapter());
		add(new ChatGUI(address), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(300, 600);
		j.add(new SideBar());
		j.setVisible(true);
	}

}