package client.clientGUI.sidebarGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import server.Server;
import client.helper.chatSystem.ChatClient;

public class ChatGUI extends JPanel {
	private static final long serialVersionUID = 8239335834925382510L;
//	private final String[] groupNames = {"ALL", "P1 Only", "P2 Only", "P3 Only", "P4 Only", "P1 & P2", "P1 & P3", "P1 & P4", "P2 & P3", "P2 & P4", "P3 & P4"};
	public static JTextPane txeaAllMessages;
	private JTextField txFdCurrentText;
//	@SuppressWarnings("rawtypes")
//	private JComboBox groups;
	private JButton btnSend;
//	private String chatSelected = groupNames[0];
	private ChatClient chatClient;
	private Socket socket;
	private PrintWriter output;
	private String serverAddress;
	private String playerName;
	
	public ChatGUI(String address, String name){
		playerName = name;
		createGUI();
		setListeners();
		//connect to server
		serverAddress = address;
		//Connect(/*address*/);TODO
	}
	
//	@SuppressWarnings({"rawtypes", "unchecked"})
	private void createGUI(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 250));
		txeaAllMessages = new JTextPane();
		txeaAllMessages.setEditable(false);
		txeaAllMessages.setAutoscrolls(true);
		//txeaAllMessages.setText("TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nhi");
		JScrollPane sp = new JScrollPane(txeaAllMessages);
		
		JPanel container = new JPanel(new BorderLayout());
		container.add(new JLabel("Chat: "), BorderLayout.NORTH);
		container.add(sp);
		add(container);
		
		txFdCurrentText = new JTextField(70);
//		groups = new JComboBox(groupNames);
		btnSend = new JButton("Send");
		
		JPanel holder = new JPanel(new BorderLayout());
//		holder.add(groups, BorderLayout.WEST);
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
		
//		groups.addActionListener(new ActionListener(){
//			@SuppressWarnings("rawtypes")
//			@Override
//			public void actionPerformed(ActionEvent source) {
//				String selected = ((JComboBox)source.getSource()).getSelectedItem().toString();
//				if (selected.equals(chatSelected))
//					return;
//				else{
//					txeaAllMessages.setText("Only " + selected + " can see your messages.");
//				}
//			}
//		});
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
		String text = /*groups.getSelectedItem().toString() +*/ playerName + ": " + content;
		txFdCurrentText.setText("");
		System.out.println("sent message: " + text + " to all" /*+ groups.getSelectedItem().toString()*/);
		//outputs to all clients, received from server
		output.println(text);
		output.flush();
		
		//TODO: processMessage( text);
		// if (box.text != 'all')
		// pretext = "#FROM: tony #TO: " + box.text
		
	}
	public void Connect(/*String address*/){
		try {
			
			socket = new Socket(serverAddress, Server.CHATPORT);
			System.out.println("You connected to: " + socket);
			
			chatClient = new ChatClient(socket);
			
			//TODO: sends the users name
			output = new PrintWriter(socket.getOutputStream());
//			output.println("poopernames");
//			output.flush();
			
			Thread x = new Thread(chatClient);
			x.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
//	private void processMessage(String message) {
//		
//		// readMessage(text);
//	//			output.println(message);
//				// if (box.text != 'all')
//				// pretext = "#FROM: tony #TO: " + box.text
//		//		output.flush();
//		
//	}
}
