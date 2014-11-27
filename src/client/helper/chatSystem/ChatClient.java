package client.helper.chatSystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;

import client.clientGUI.sidebarGUI.ChatGUI;

public class ChatClient implements Runnable {
	Scanner input;
	public static PrintWriter output;
	String message = "";

	public ChatClient(Socket sock) throws IOException {
		input = new Scanner(sock.getInputStream());
		output = new PrintWriter(sock.getOutputStream());
	}

	@Override
	public void run() {
		while (true) {
			receive();
		}
	}

	public void receive() {
		if (input.hasNext()) {
			message = input.nextLine();
			ChatGUI.txeaAllMessages.setText(ChatGUI.txeaAllMessages.getText()
					+ "\n" + message);
		}
	}

}
