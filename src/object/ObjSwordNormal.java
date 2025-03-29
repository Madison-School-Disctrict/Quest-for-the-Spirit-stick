package object;

import entity.Entity;
import main.GamePanel;

public class ObjSwordNormal extends Entity{
	public static final String objName = "Normal Sword";
	public ObjSwordNormal(GamePanel gp) {
		super(gp);
		type = type_sword;
		name = objName;
		stackable = true;
		down1 = setup("/objects/sword_normal", gp.tileSize,gp.tileSize);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]" +"\nAn old sword."; 
		price = 15;
		knockBackPower = 7;
		motion1_duration = 5;
		motion2_duration = 25;
	
	}

}


/*
	I am thinking about creating a weapons class in between the weapons and the entity class to handle weapon specific stuff. 
*/