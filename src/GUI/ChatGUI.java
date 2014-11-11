package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGUI extends JFrame {
	private static final long serialVersionUID = 8239335834925382510L;
	private JTextArea allMessages;
	private JTextField currentType;
	private JComboBox<String> groups;
	private JButton send;
	
	public ChatGUI(){
		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(100,200);
		
		createGUI();
		setVisible(true);
	}
	
	private void createGUI(){
		allMessages = new JTextArea();
		allMessages.setEditable(false);
		JScrollPane sp = new JScrollPane(allMessages);
		add(sp);
		allMessages.setText("TESTTESTTEST\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nhi");
		
		currentType = new JTextField(70);
		String[] groupNames = {"Player 1", "Player 2", "Player 3", "Player 4", "P1 & P2", "P1 & P3", "P1 & P4", "P2 & P3", "P2 & P4", "P3 & P4"};
		groups = new JComboBox<String>(groupNames);
		send = new JButton("Send");
		
		JPanel holder = new JPanel(new BorderLayout());
		holder.add(groups, BorderLayout.WEST);
		holder.add(currentType);
		holder.add(send, BorderLayout.EAST);
		add(holder, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new ChatGUI();
	}

}