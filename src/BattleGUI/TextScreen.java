
package BattleGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextScreen extends JPanel {
	private static final long serialVersionUID = -1657066776722232406L;
	private final int MAXLINES = 7;
	private final int MAXCHARACTERSPERLINE = 91;
	private JTextArea message;
	private PrintWriter pw;
	private BufferedReader br;
	private CardLayout switcher;
	
	private CommandCenterGUI central;
	
	public TextScreen(CommandCenterGUI central,CardLayout switcher){
		createGUI();
		this.central = central;
		this.switcher = switcher;
	}
	
	public TextScreen(CommandCenterGUI central,CardLayout switcher, PrintWriter p, BufferedReader b){
		createGUI();
		this.central = central;
		this.pw = p;
		this.br = b;
		this.switcher = switcher;
	}
	
	private void createGUI(){
		setLayout(new BorderLayout());
		message = new JTextArea(MAXLINES, MAXCHARACTERSPERLINE);
		message.setEditable(false);
		message.setLineWrap(true);
		add(message);
		message.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.out.println("LET's go back");

				String input = "";
				try {
					do{
						//for a specfici message->send the message to the gui (through the ccg) at gui...parse message
						//if its end message->change the battle screen (entire GUI) to something
					//else keep whatever's there
						if(input.contains("END")){//battle's over at this point
							//pw.write(input);
							//System.out.println("SENDING END MESSAGE OVER!");
							sendMessage(input);
						}
						
						else if (input != "")
							appendText(input);
						else if (input.contains("hit")){
							sendMessage(input);
						}else if (input.contains("swap")){
							sendMessage(input);
						}else if (pw != null){
							pw.println("PROGRESS");
							pw.flush();
						}
						System.out.println(input + " TEXT SCREEN INPUT TEXTSCREEN.JAVA");
					}while (!(input = br.readLine()).equals("DONE"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				switcher.show(central, central.ACTION);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	
	private void sendMessage(String m){
		central.send(m);
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
}