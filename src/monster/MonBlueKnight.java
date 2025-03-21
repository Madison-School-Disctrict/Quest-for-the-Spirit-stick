package monster;

import entity.Entity;
import main.GamePanel;

public class MonBlueKnight extends Entity{
	GamePanel gp;
	public MonBlueKnight(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_monster;
		name = "Blue Knight";
		defaultSpeed = 5;
		speed = defaultSpeed;
		maxLife = 10;
		life = maxLife;
		attack = 12;
		defense = 8;
		exp = 30;
		knockBackPower = 5;
		
		solidArea.x = 4;
		solidArea.y =4;
		solidArea.width = 40;
		solidArea.height = 44;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		motion1_duration = 40;
		motion2_duration = 85;
		getImage();
		//getAttackImage();
	}
	public void getImage() {
		up1 = setup("/monsters/orc_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monsters/orc_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monsters/orc_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monsters/orc_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monsters/orc_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monsters/orc_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monsters/orc_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monsters/orc_right_2", gp.tileSize, gp.tileSize);
		
	}
	// public void getAttackImage() {
	// 	attackUp1 = setup("/monsters/orc_attack_up_1", gp.tileSize, gp.tileSize*2);   
    //     attackUp2 = setup("/monsters/orc_attack_up_2", gp.tileSize, gp.tileSize*2);     
    //     attackDown1 = setup("/monsters/orc_attack_down_1", gp.tileSize, gp.tileSize*2);   
    //     attackDown2 = setup("/monsters/orc_attack_down_2", gp.tileSize, gp.tileSize*2);  
    //     attackRight1 = setup("/monsters/orc_attack_right_1", gp.tileSize*2, gp.tileSize);   
    //     attackRight2 = setup("/monsters/orc_attack_right_2", gp.tileSize*2, gp.tileSize);   
    //     attackLeft1 = setup("/monsters/orc_attack_left_1", gp.tileSize*2, gp.tileSize);   
    //     attackLeft2 = setup("/monsters/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
	// }
	public void setAction() {
		if(onPath) {
			
			//Check if it stops chasing
			checkStopChaseingOrNot(gp.player, 15, 100);
    		
			// Search the direction to go
    		searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
    		
		} else {
			
			//check if it starts chasing 
			checkStartChaseingOrNot(gp.player,5,100);
			
			// Get A random direction
			getRandomDirection(120);
			
		}
		
		//check if it attacks
		if(!attacking) {
			checkAttackOrNot(30,gp.tileSize*4,gp.tileSize);
		}
    }
	public void damageReaction() {
		actionBlockCounter = 0;
		direction = gp.player.direction;
		onPath = true;
		
	}

}
