package BattleGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextScreen extends JPanel {
	private static final long serialVersionUID = -1657066776722232406L;
	private JTextArea message;
	
	public TextScreen(){
		createGUI();
	}
	
	private void createGUI(){
		setLayout(new BorderLayout());
		message = new JTextArea(7, 91);
		message.setEditable(false);
		message.setLineWrap(true);
		add(message);
	}
	
	public void setText(String s){
		message.setText(s);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(500, 150);
		TextScreen t = new TextScreen();
		j.add(t);
		j.setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.setText("asdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']1234");		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.setText("Player 1 used Tackle!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.setText("It's super effective!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.setText("Player 2 tried to run away.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.setText("It failed!");
	}
}