package monster;

import main.GamePanel;
import object.ObjRock;

public class MonPinkSlime extends Monster{
	GamePanel gp;
	public MonPinkSlime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_monster;
		name = "Pink Slime";
		defaultSpeed = 3;
		speed = defaultSpeed;
		maxLife = 45;
		life = maxLife;
		attack = 20;
		defense = 8;
		exp = 15;
		projectile = new ObjRock(gp);
		
		solidArea.x = 3;
		solidArea.y =18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}
	public void getImage() {
		up1 = setup("/monsters/pinkslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/pinkslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/pinkslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/pinkslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/pinkslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/pinkslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/pinkslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/pinkslime_down_2", gp.tileSize, gp.tileSize);
		
	}
	public void setAction() {
		if(onPath) {
			
			//Check if it stops chasing
			checkStopChaseingOrNot(gp.player, 15, 100);
    		
			// Search the direction to go
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
    		
    		//Check if it shoots a projectile
    		checkShootOrNot(200, 30);
		} else {
			
			//check if it starts chasing 
			checkStartChaseingOrNot(gp.player,5,100);
			
			// Get A random direction
			getRandomDirection(120);
			
		}
    }
	public void damageReaction() {
		actionBlockCounter = 0;
		direction = gp.player.direction;
		onPath = true;
		
	}

}
