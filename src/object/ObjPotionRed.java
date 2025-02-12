package object;

import entity.Entity;
import main.GamePanel;

public class ObjPotionRed extends Entity {
 
	GamePanel gp;
	public static final String objName = "Red Potion";
	public ObjPotionRed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		stackable = true;
		name = objName;
		value = 5;
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[Red Potion] \n Heals your Life by " + value + ".";
		price = 300;
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You drink the " + name + "!\nYour life has been restored by " + value + ".";
	}
	public boolean use(Entity entity) {
		startDialogue(this,0);
		entity.life += value;
		gp.playSE(2);
		return true;
	}
}
