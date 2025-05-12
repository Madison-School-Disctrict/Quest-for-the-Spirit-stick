package object;

import entity.Entity;
import main.GamePanel;

public class ObjTent extends Entity {
	GamePanel gp;
	public static final String objName = "Tent";
	public ObjTent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = objName;
		stackable = true;
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nYou can sleep until\nnext morning.";
		price = 300;
		
	}
	
	public boolean use(Entity entity) {
		gp.gameState = gp.sleepState;
		gp.playSE(14);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		gp.player.getSleepingImage(down1);
		return false;  /* If you want to be able to use the Tent over and over again set to false  
						If you want to be able to only use it once the set true.
		*/
	}

}
