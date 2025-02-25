package object;

import entity.Entity;
import main.GamePanel;

public class ObjPickaxe extends Entity {
	public static final String objName = "Pickaxe";
	       //ObjPickaxe
	public ObjPickaxe(GamePanel gp) {
		super(gp);
		type = type_pickaxe;
		stackable = true;
		name = objName;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 0;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name + "]" + "\nYou can dig"; 
		price = 75;
		knockBackPower = 5;
		motion1_duration = 10;
		motion2_duration = 20;
	}

}
