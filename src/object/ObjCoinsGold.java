package object;

import entity.Entity;
import main.GamePanel;

public class ObjCoinsGold extends Entity{
	GamePanel gp;
	public static final String objName = "Gold Coin";
	public ObjCoinsGold(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_pickupOnly;
		name = objName;
		value = 3;
		down1 = setup("/objects/coin_gold", gp.tileSize, gp.tileSize);
		
	}
	
	public boolean use(Entity entity) {
		gp.playSE(1);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
