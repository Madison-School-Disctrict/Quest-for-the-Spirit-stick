package tileInteractive;


import java.awt.Graphics2D;


import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{
	GamePanel gp;
	public boolean destructible = false;
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	public void playSE() {}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	public void update() {
		if(invincible) {
	     	   invincibleCounter++;
	     	   if(invincibleCounter > 20) {
	     		   invincible = false;
	     		   invincibleCounter = 0;
	     	   }
	        }
	}
	
	public void draw(Graphics2D g2) {

		int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
		int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX > gp.player.worldX-gp.player.screenX - gp.tileSize&& 
		   worldX < gp.player.worldX+gp.player.screenX + gp.tileSize&&
		   worldY > gp.player.worldY-gp.player.screenY - gp.tileSize&& 
		   worldY < gp.player.worldY+gp.player.screenY + gp.tileSize ){
			    
			g2.drawImage(down1, ScreenX, ScreenY, null);
			
			
		}
		
	}
}
 
 
