import java.util.ArrayList;


public class Map {
	private ArrayList<Screen> screenList;			// 2 x 2 square
	private int height;
	private int width;
	
	public void switchScreen(Player p,Screen current, Screen next){
		
	}
	
	//come back to this soon
	
	public void movePlayer(){
		
	}
	
	public Screen getScreen(int index){
		if(index < this.screenList.size()){
			return this.screenList.get(index);
		}
		return null;//other wise we are empty
	}
}
