
package BattleGUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextScreen extends JPanel {
	private static final long serialVersionUID = -1657066776722232406L;
	private final int MAXLINES = 7;
	private final int MAXCHARACTERSPERLINE = 91;
	private JTextArea message;
	
	public TextScreen(){
		createGUI();
	}
	
	private void createGUI(){
		setLayout(new BorderLayout());
		message = new JTextArea(MAXLINES, MAXCHARACTERSPERLINE);
		message.setEditable(false);
		message.setLineWrap(true);
		add(message);
	}
	
	public void setText(String s){
		message.setText(s);
	}
	
	public void appendText(String s){
		if (message.getLineCount() == MAXLINES)
			message.setText(s);
		else
			message.append("\n" + s);
	}
	
//	public static void main(String[] args) {
//		JFrame j = new JFrame();
//		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		j.setSize(500, 150);
//		TextScreen t = new TextScreen();
//		j.add(t);
//		j.setVisible(true);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		t.setText("asdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']\\=-0987654321`2345678ljajoijdaoijfjqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm[);.,./']1234");		
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t.appendText("Player 1 used Tackle!");
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t.setText("It's super effective!");
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t.setText("Player 2 tried to run away.");
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t.appendText("But it failed!");
//		t.appendText("TEST");
//		t.appendText("ASDF");
//		t.appendText("ASDF");
//		t.appendText("47289");
//		t.appendText("Last Line");
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t.appendText("FIRST LINE");
//	}

}
