
package BattleGUI;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import dataStore.Move;

public class AttackSelection extends JPanel {
	private static final long serialVersionUID = 7650587763939250380L;
	private CardLayout selecter;
	private CommandCenterGUI central;
	private PrintWriter pw;
	private BufferedReader br;
	
	public AttackSelection(Vector<Move> m, CardLayout s, CommandCenterGUI c){
		createGUI(m);
		selecter = s;
		central = c;
	}
	
	public AttackSelection(CardLayout s, CommandCenterGUI c, PrintWriter p, BufferedReader b){
		selecter = s;
		central = c;
		pw = p;
		br = b;
		createGUI(new Vector<Move>(getMoves(0)));
	}
	
	public AttackSelection(Move[] m, CardLayout switcher, CommandCenterGUI c) {
		createGUI(m);
		selecter = switcher;
		central = c;
	}

	private void createGUI(Vector<Move> m){
		setLayout(new GridLayout(2,2));
		for (int i = 0; i < m.size(); ++i)
			add(new AttackButton(m.get(i)));
	}
	
	public void replaceMoveGUI(Vector<Move> v){
		this.removeAll();
		createGUI(v);
	}
	
	private void createGUI(Move[] m){
		setLayout(new GridLayout(2,2));
		for (int i = 0; i < m.length; ++i)
			add(new AttackButton(m[i]));
	}
	
	public Vector<Move> getMoves(int index){
		String movesList = null;
//		pw.println("AT_" + index);
		System.out.println("Trying to get moves");
		try {
			movesList = br.readLine();
			System.out.println("GOT MOVES");
			System.out.println(movesList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parse(movesList);
	}
	
	//name+damage=name+damage=name+damage=name+damage
	
	private Vector<Move> parse(String input){
		Vector<Move> v = new Vector<Move>();
		//int moveStartIndex = 0;
		//int moveEndIndex = input.indexOf("?");
		//int space = input.indexOf(" ");
		
		
		
		//String message = input;
		int numMoves = 4;
		System.out.println(input + " INPUT STRING ");
		for(int i=0;i<numMoves;++i){
			int plusIndex;
			String name;
			String damage;
			if(input.contains("=")){
				plusIndex = input.indexOf("+");
				name = input.substring(0,plusIndex);
				damage = input.substring(plusIndex+1,input.indexOf("="));
				System.out.println(name + " NAME ! " + damage + " DAMAGE ");
				input = input.substring(input.indexOf("=")+1);
			}
			else{
				name = input.substring(0,input.indexOf("+"));
				damage = input.substring(input.indexOf("+")+1);
				System.out.println(name + " NAME LAST  ! " + damage + " DAMAGE Last ");
			}
			v.add(new Move(Integer.parseInt(damage),name));
		}
		/*do{
			v.add(new Move(Integer.parseInt(input.substring(space + 1, moveEndIndex)), input.substring(moveStartIndex, space)));
			moveStartIndex = moveEndIndex + 1;
			moveEndIndex = input.indexOf("?", moveStartIndex);
			space = input.indexOf(" ", moveStartIndex);
			++i;
		}while (i < 4);
		*/
		for(int i=0;i<v.size();++i){
			Move move = v.get(i);
			System.out.println(move.getDamage() + " DAMAGE " + move.getName() + " NAME ");
		}
		return v;
	}
	
	private class AttackButton extends JButton{
		private static final long serialVersionUID = -451514209159278415L;
		private Move move;
		
		public AttackButton(Move m){
			super();
			move = m;
			setFont(new Font("Arial", Font.BOLD, 22));
			setFocusPainted(false);
			setFocusable(false);
			setText(move.getName());
			setName(String.valueOf(move.getDamage()));
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(move.getName());
					if (AttackSelection.this.pw != null){
						String output = "At_" + ((JButton)e.getSource()).getText() + "|";
						System.out.println(output);
						AttackSelection.this.pw.println(output/* + ((JButton)e.getSource()).getName()*/);
						AttackSelection.this.pw.flush();
					}
					System.out.println("Pushed attack");
					
					AttackSelection.this.selecter.show(central, central.TEXT);
					central.revalidate();
					central.repaint();
					System.out.println("switched");
					String input = null;
					try {
						input = br.readLine();
						System.out.println("read in: " + input);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					AttackSelection.this.central.text.setText(input);
//					AttackSelection.this.central.text.setText("You used " + move.getName() + "!");
					
				}
			});
		}
		
	}
}
