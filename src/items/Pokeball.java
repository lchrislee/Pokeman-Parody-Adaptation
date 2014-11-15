package items;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dataStore.Pokemon;


public class Pokeball implements Item{
	private double efficiency;
	private JLabel icon;
	
	public Pokeball(double eff, ImageIcon icon){//making a pokeball
		this.efficiency = eff;
		this.icon = new JLabel(icon);
	}
	
	@Override
	public void use(Pokemon p) {
		// TODO Auto-generated method stub
		//based on efficiency, attempt to catch pokemon
	}

}
