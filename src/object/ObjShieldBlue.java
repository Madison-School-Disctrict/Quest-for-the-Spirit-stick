package object;

import entity.Entity;
import main.GamePanel;

public class ObjShieldBlue extends Entity{
	public static final String objName = "Blue Sheild";
	 public ObjShieldBlue(GamePanel gp) {
		super(gp);
		type = type_shield;
		name = objName;
		down1 =setup("/objects/shield_blue",gp.tileSize,gp.tileSize);
		defenseValue = 2;
		description = "(" + name + ")" + "\nA shinny blue sheild.";
		price = 150;
	}

	
		 
}
