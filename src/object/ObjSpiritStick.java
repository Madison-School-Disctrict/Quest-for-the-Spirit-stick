package object;

import entity.Entity;
import main.GamePanel;

public class ObjSpiritStick extends Entity{
	GamePanel gp;
	public static final String objName = "Madison Spirit Stick";
	public ObjSpiritStick(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_spiritStick;
		stackable = true;
		name = objName;
		description = "[" + name + "]" + "\nThe spirit stick increaes all magical powers"; 
		spiritStickPower = 10;
		price = 999999;
		value = spiritStickPower;
		setDialogue();
		getImage();
		
		
	}
	
	public void setDialogue() {
		dialogues[0][0] = "You use the " + name + "!\nYour mana has be increased by " + value + ".";
	}
	public void getImage() {    
		down1 = setup("/objects/spirtStick", gp.tileSize, gp.tileSize);
	}
	               
	public boolean use(Entity entity) {
		startDialogue(this,0);
		entity.mana += value;
		gp.playSE(2);
		return true;
	}
	
	
	
	

}


