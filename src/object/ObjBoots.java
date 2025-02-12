package object;

import entity.Entity;
import main.GamePanel;

public class ObjBoots extends Entity{
	public static final String objName = "Boots";
	public ObjBoots(GamePanel gp) {
		super(gp);
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
		collision = true;
		description = "(" + name + ")" + "\nA pair of boots that appear \nto be magical."; 
		price = 100;
	}

}


