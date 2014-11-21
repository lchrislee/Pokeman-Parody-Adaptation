package items;
import dataStore.Pokemon;


public class Potion implements Item {

	@Override
	public void use(Pokemon p) {
		// TODO Auto-generated method stub
		if(p.isConscious() && p.getHealth()!=p.getMaxHealth()){
			int newHealth = (int) (p.getHealth()*1.2);
			p.setHealth(newHealth);
		
			
		}
	}

}
