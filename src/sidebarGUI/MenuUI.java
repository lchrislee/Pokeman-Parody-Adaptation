package sidebarGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuUI extends JPanel {
	private static final long serialVersionUID = 7397593496707020802L;
	//instance of player
	
	public MenuUI(){
		createGui();
	}

	public void createGui(){
		
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.add(new MenuUI());
		j.setSize(300, 350);
		j.setLocation(100, 200);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

}
