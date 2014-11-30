package client.clientGUI.Waiting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import dataStore.NetworkPlayer;

public class FirstBracket extends JPanel implements ActionListener {
	Timer timer = new Timer(1,this);
	int timerCount = 0;
	int timerCountSpeed = 1;
	public boolean done = false;
	ArrayList<NetworkPlayer> players;
	public FirstBracket(ArrayList<NetworkPlayer> players) {
		this.players = players;
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(Color.black);
		int i = 0;
		
		String firstPlayerName = "Player 0"; i++;
		Image firstImage = Toolkit.getDefaultToolkit().getImage("res/Character_sprites/Dolan_normal.png");
		
		/* use the commented code for real players and not dummies */
		/* String firstPlayerName = players.get(i).getName();
		 * ImageIcon firstPlayerImageIcon = players.get(i).getCurrentSprite();
		 * Image firstPlayerImage = firstPlayerImageIcon.getImage();
		 * i++; */
		String secondPlayerName = "Player 1";
		/* String secondPlayerName = players.get(i).getName(); */
		/* String secondPlayerName = players.get(i).getName();
		 * ImageIcon secondPlayerImageIcon = players.get(i).getCurrentSprite();
		 * Image secondPlayerImage = secondPlayerImageIcon.getImage();
		 * i++; */
		String thirdPlayerName = "Player 2";
		/* String thirdPlayerName = players.get(i).getName(); */
		/* String thirdPlayerName = players.get(i).getName();
		 * ImageIcon thirdPlayerImageIcon = players.get(i).getCurrentSprite();
		 * Image thirdPlayerImage = thirdPlayerImageIcon.getImage();
		 * i++; */
		String fourthPlayerName = "Player 3";
		/* String fourthPlayerName = players.get(i).getName(); */
		/* String fourthPlayerName = players.get(i).getName();
		 * ImageIcon fourthPlayerNameImageIcon = players.get(i).getCurrentSprite();
		 * Image fourthPlayerNameImage = fourthPlayerNameImageIcon.getImage();
		 * i++; */
		g.setFont(new Font("Arial",Font.BOLD, 20));
		
		g.fillRect(100,300,5,100);
		g.fillRect(300,300,5,100);
		g.fillRect(500,300,5,100);
		g.fillRect(700,300,5,100);
		g.setColor(Color.BLUE);
		// giving dummies for now
		g.drawString(firstPlayerName,70,420);
		g.drawImage(firstImage,10,420,this);
		g.drawString(secondPlayerName,270,420);
		g.drawImage(firstImage,210,420,this);
		g.drawString(thirdPlayerName,470,420);
		g.drawImage(firstImage,400,420,this);
		g.drawString(fourthPlayerName,670,420);
		g.drawImage(firstImage,600,420,this);

		g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.BOLD, 35));
		g.drawString("WINNER", 330, 60);
		
		g.setColor(Color.BLACK);
		
		
		g.fillRect(100,300,200,5);
		g.fillRect(500,300,200,5);
		
		g.fillRect(235,200,5,100);
		g.fillRect(545,200,5,100);
		
		g.fillRect(235,200,310,5);
		
		g.fillRect(400,70,5,130);
		
		timer.start();
	}
	
	public void actionPerformed (ActionEvent e) {
		timerCount += timerCountSpeed;
		if (timerCount >= 1300) {
			done = true;
			System.out.println("Ready to move");
			timer.stop();
		}
		repaint();
	}
	public static void main (String args []) {
		ArrayList<NetworkPlayer> dummy = new ArrayList<NetworkPlayer>();
		FirstBracket f = new FirstBracket(dummy);
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Bracket");
		testWindow.setSize(800,600);
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(f);
		testWindow.setVisible(true);
	}
 }
