package object;

import java.awt.Color;
import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class ObjFireBallBlue extends Projectile{
	GamePanel gp;
	public static final String objName = "Blue Fireball";
	public ObjFireBallBlue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_magic;
		name = objName;
		speed = 7;
		maxLife = 100;
		life = maxLife;
		attack = 5;
		useCost = 2;
		alive = false;
		getImage();
		price = 480;
		description = "[Blue FireBall] \n magical fire flame";
		knockBackPower = 5;
	
				
	}
	public void getImage() {
		up1 = setup("/projectile/fireballBlue_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/fireballBlue_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/fireballBlue_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/fireballBlue_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/fireballBlue_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/fireballBlue_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/fireballBlue_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/fireballBlue_right_2", gp.tileSize, gp.tileSize);
	}
	               
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);		
		return color;
	}
	public int getParticleSize() {
		int size = 10; // 6 pixels
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

	
	
}
