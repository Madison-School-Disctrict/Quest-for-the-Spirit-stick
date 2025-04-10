package tileInteractive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class ITDrayTree extends InteractiveTile{
	//GamePanel gp;

	public ITDrayTree(GamePanel gp, int col, int row) {
		super(gp,  col, row);
		this.gp = gp;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		down1 = setup("/interactive_tiles/drytree", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 1;
				
	}
	@Override
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	public void playSE() {
		gp.playSE(7);
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new ITTrunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65,50,30);		
		return color;
	}
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}
