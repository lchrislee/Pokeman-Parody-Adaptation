package chatSystem;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatThread implements Runnable {
	private Socket socket;
	private ChatServer server;
	private Scanner input;
	String message = "";

	public ChatThread(ChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	/*public void CheckConnection() throws IOException {

		if (!socket.isConnected()) {
			System.out.println("NO LONGER CONNECTED");
			
			for (Socket tempSock : ChatServer.connectionArray) {
				System.out.println("NO LONGER CONNECTED");
				if (tempSock == socket) {
					ChatServer.connectionArray.remove(tempSock);
					// remove socket?
				}

			}

			for (int i = 0; i < ChatServer.connectionArray.size(); i++) {
				Socket tempSock = (Socket) ChatServer.connectionArray
						.get(i - 1);
				PrintWriter output = new PrintWriter(tempSock.getOutputStream());
				output.println(tempSock.getLocalAddress().getHostName()
						+ "disconnected");
				output.flush();
				System.out.println(tempSock.getLocalAddress().getHostName()
						+ "disconnected");
			}
		}
	}*/

	public void run() {
		try {
			input = new Scanner(socket.getInputStream());
			// output = new PrintWriter(socket.getOutputStream());
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// }

			while (true) {
				// CheckConnection();

				if (!input.hasNext()) {
				 continue;
				 }
				// read next message
				message = input.nextLine();

				// System.out.println(ChatServer.currentUsers);

				// if (message.contains("#FROM: ") && message.contains("#TO: ")

				System.out.println("Client message: " + message);
			//	 System.out.println("ConnectionList size:");
				// System.out.println(ChatServer.connectionArray.size());
				// send to all here
				server.sendToAll(message);

			}
		} catch (EOFException ie) {
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// try {
			try {
				server.removeConnection(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
