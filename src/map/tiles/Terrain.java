package map.tiles;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


abstract public class Terrain {
	protected boolean canWalk;
	protected boolean canBattle;
	protected boolean canTransport;
	private int width;
	private int height;
	private JLabel sprite;
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public boolean canWalkOn(){
		return this.canWalk;
	}
	
	public boolean canBattleOn(){
		return this.canBattle;
	}
	
	public boolean doesTransport(){
		return this.canTransport;
	}
	
	public JLabel getSprite(){
		return this.sprite;
	}
	
	public void setApperance(ImageIcon image){
		this.sprite.setIcon(image);
	}
}
