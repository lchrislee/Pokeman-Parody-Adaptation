package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ChatGUI extends JPanel {
	private static final long serialVersionUID = 8239335834925382510L;
	private JTextArea txeaAllMessages;
	private JTextField txFdCurrentText;
	private JComboBox<String> groups;
	private JButton btnSend;
	
	public ChatGUI(){
		setLayout(new BorderLayout());
		
		createGUI();
		setListeners();
	}
	
	private void createGUI(){
		txeaAllMessages = new JTextArea();
		txeaAllMessages.setEditable(false);
		txeaAllMessages.setText("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nhi");
		txeaAllMessages.setLineWrap(true);
		JScrollPane sp = new JScrollPane(txeaAllMessages);
		add(sp);
		
		txFdCurrentText = new JTextField(70);
		String[] groupNames = {"ALL", "P1 Only", "P2 Only", "P3 Only", "P4 Only", "P1 & P2", "P1 & P3", "P1 & P4", "P2 & P3", "P2 & P4", "P3 & P4"};
		groups = new JComboBox<String>(groupNames);
		btnSend = new JButton("Send");
		
		JPanel holder = new JPanel(new BorderLayout());
		holder.add(groups, BorderLayout.WEST);
		holder.add(txFdCurrentText);
		holder.add(btnSend, BorderLayout.EAST);
		add(holder, BorderLayout.SOUTH);
	}

	private void setListeners(){
		btnSend.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent source) {
				sendMessage();
			}
		});
		
		@SuppressWarnings("serial")
		Action enterPressed = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendMessage();
			}
		};
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		txFdCurrentText.getInputMap().put(enter, "ENTER");
		txFdCurrentText.getActionMap().put("ENTER", enterPressed);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new ChatGUI());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 175);
		f.setVisible(true);
	}

	private void sendMessage(){
		String text = txFdCurrentText.getText();
		txFdCurrentText.setText("");
		System.out.println("sent message: " + text + " to " + groups.getSelectedItem().toString());
	}
}