package client;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String address = JOptionPane.showInputDialog(null, "Enter the server's IP address", "Get IP Address", JOptionPane.DEFAULT_OPTION);
		System.out.println(address + " ADDRESS ");
	}

}
