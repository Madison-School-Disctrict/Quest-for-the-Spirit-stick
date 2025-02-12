package object;

import entity.Entity;
import main.GamePanel;

public class ObjLantern extends Entity{
	public static final String objName = "Lantern";
	public ObjLantern(GamePanel gp) {
		super(gp);
		type = type_light;
		name = objName;
		stackable = true;
		down1 = setup("/objects/lantern",gp.tileSize, gp.tileSize);
		description = "[Lanteren]\nIlluminates your \nsurroundings.";
		price = 200;
		lightRadius = 350;
		
	}

}
