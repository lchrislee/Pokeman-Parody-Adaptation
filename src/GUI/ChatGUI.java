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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class ChatGUI extends JPanel {
	private static final long serialVersionUID = 8239335834925382510L;
	private final String[] groupNames = {"ALL", "P1 Only", "P2 Only", "P3 Only", "P4 Only", "P1 & P2", "P1 & P3", "P1 & P4", "P2 & P3", "P2 & P4", "P3 & P4"};
	private JTextPane txeaAllMessages;
	private JTextField txFdCurrentText;
	private JComboBox<String> groups;
	private JButton btnSend;
	private String chatSelected = groupNames[0];
	
	public ChatGUI(){
		setLayout(new BorderLayout());
		
		createGUI();
		setListeners();
	}
	
	private void createGUI(){
		txeaAllMessages = new JTextPane();
		txeaAllMessages.setEditable(false);
		//txeaAllMessages.setText("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nhi");
		JScrollPane sp = new JScrollPane(txeaAllMessages);
		add(sp);
		
		txFdCurrentText = new JTextField(70);
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
		
		groups.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent source) {
				@SuppressWarnings("unchecked")
				String selected = ((JComboBox<String>)source.getSource()).getSelectedItem().toString();
				if (selected.equals(chatSelected))
					return;
				else{
					txeaAllMessages.setText("You are now only talking to " + selected);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new ChatGUI());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300, 175);
		f.setVisible(true);
	}

	public void readMessage(String message){
		int nameDivide = message.indexOf(": ");
		String name = "";
		if (txeaAllMessages.getText().length() != 0)
			name += "\n";
		name += message.substring(0, nameDivide + 1);
		StyledDocument doc = txeaAllMessages.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), name, doc.getStyle("Bold"));
			doc.insertString(doc.getLength(), message.substring(nameDivide + 1), doc.getStyle("Plain"));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(){
		String content = txFdCurrentText.getText();
		if (content.length() == 0)
			return;
		String text = "You sent: " + content;
		txFdCurrentText.setText("");
		System.out.println("sent message: " + text + " to " + groups.getSelectedItem().toString());
		readMessage(text);
	}
}