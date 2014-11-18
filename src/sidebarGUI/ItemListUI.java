package sidebarGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ItemListUI extends JPanel{
	private static final long serialVersionUID = 8785936817939868292L;
	private int numItem[];
	private final String itemNames[] = {"Masterball", "Ultraball", "Pokeball", "Honey", "Potion", "Rock"};
	private final String itemLocations[] = {"res/ItemSprites/masterball_enlarged.png",
											"res/ItemSprites/ultraball_enlarged.png",
											"res/ItemSprites/pokeball_enlarged.png",
											"res/ItemSprites/honey_jar_enlarged.png",
											"res/ItemSprites/potion_enlarged.png",
											"res/ItemSprites/rock_enlarged.png"};
	private ArrayList<ItemPanel> panels;
	private CardLayout switcher;
	private SideBarMenuAdapter parent;
	private JButton btnBack;
	
	public ItemListUI(){
		panels = new ArrayList<ItemPanel>();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		initializeItems();
		createGUI();
	}
	
	public ItemListUI(CardLayout c, SideBarMenuAdapter p){
		switcher = c;
		parent = p;
		panels = new ArrayList<ItemPanel>();
		setLayout(new BorderLayout());
		setBackground(Color.white);
		initializeItems();
		createGUI();
		addListeners();
	}
	
	public void initializeItems(){
		numItem = new int[]{1,2,5, 5, 6, 7};
	}
	
	private void createGUI(){
		JPanel holder = new JPanel(new GridLayout(6, 3));
		holder.setBackground(Color.white);
		add(new JLabel("Click an item to use!"), BorderLayout.NORTH);
		for (int i = 0; i < 6; ++i){
			ItemPanel p = getItemPanel(i);
			panels.add(p);
			holder.add(p);
		}
		JScrollPane sp = new JScrollPane(holder);
		add(sp);
		btnBack = new JButton("Back");
		add(btnBack, BorderLayout.SOUTH);
	}
	
	private void addListeners(){
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.show(parent, parent.MENUNAME);
			}
		});
	}
	
	private ItemPanel getItemPanel(int i){
		ItemPanel p = new ItemPanel(i);
		return p;
	}
	
	private class ItemPanel extends JPanel{ //add listener
		private static final long serialVersionUID = -5599471922315413657L;

		public ItemPanel(int index){
			setLayout(new BorderLayout());
			createGUI(index);
		}
		
		private void createGUI(int i){
			JButton b = new JButton(new ImageIcon(itemLocations[i]));
			b.setFocusPainted(false);
			b.setName(itemNames[i]);
			b.setBorderPainted(false);
			b.setBackground(Color.white);
			JLabel name = new JLabel(itemNames[i]);
			name.setBackground(Color.white);
			name.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel number = new JLabel(" x" + numItem[i]);
			number.setBackground(Color.white);
			
			add(b, BorderLayout.WEST);
			add(name);
			add(number, BorderLayout.EAST);
			setBackground(Color.white);
		}
	}
}