package items;
import dataStore.Pokemon;


public class Potion implements Item {

	@Override
	public void use(Pokemon p) {
		// TODO Auto-generated method stub
		if(p.isConscious())
			System.out.println("Recover a certain amount of health");
		
		
	}

}
