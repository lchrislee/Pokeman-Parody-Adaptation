
package client.clientGUI.sidebarGUI;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SideBarMenuAdapter extends JPanel {
	private static final long serialVersionUID = -2460067380322702091L;
	public final String MENUNAME = "MENU";
	public final String ITEMNAME = "ITEMLIST";
//	public final String STATUSNAME = "POKEMONSTATUS";
//	public final String USERINFO = "USERINFORMATION";

	public SideBarMenuAdapter(){
		createGUI();
	}

	private void createGUI(){
		setPreferredSize(new Dimension(300, 350));
		CardLayout switcher = new CardLayout();
		setLayout(switcher);
		add(new MenuUI(switcher, this), MENUNAME);
//		add(new ItemListUI(switcher, this), ITEMNAME);
//		add(new PokemonStatusUI(switcher, this), STATUSNAME);
//		add(new UserInformationGUI(switcher, this), USERINFO);
	}
}