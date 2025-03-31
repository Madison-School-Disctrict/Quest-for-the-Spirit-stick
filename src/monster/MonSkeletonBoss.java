package monster;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.ObjDoorIron;

public class MonSkeletonBoss extends Entity{
	
	GamePanel gp;
	public static final String monName = "Skeleton Boss";
	public MonSkeletonBoss(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_monster;
		boss = true;
		name = monName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 1500;
		life = maxLife;
		attack = 250;
		defense = 50;
		exp = 350;
		knockBackPower = 10;
		sleep = true;	
		int size = gp.tileSize * 5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 96;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		motion1_duration = 25;
		motion2_duration = 50;
		getImage();
		getAttackImage();
		setDialogue();
	}
	
	public void getImage() {
		int i = 5;
		if(!inRage) {
		up1 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_up_1", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_up_2", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_down_1", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_down_2", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_left_1", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_left_2", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_right_1", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/monsters/skeletonlord_phase1_move/skeletonlord_right_2", gp.tileSize*i, gp.tileSize*i);
		} else {
			up1 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i);
			up1 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_up_1", gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_up_2", gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_down_1", gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_down_2", gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_left_1", gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_left_2", gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_right_1", gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monsters/skeletonlord_phase2_move/skeletonlord_phase2_right_2", gp.tileSize*i, gp.tileSize*i);
		}
	}
	public void getAttackImage() {
		int i = 5;
		if(!inRage) {
		attackUp1 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_up_1", gp.tileSize*i, gp.tileSize*2*i);   
        attackUp2 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_up_2", gp.tileSize*i, gp.tileSize*2*i);     
        attackDown1 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_down_1", gp.tileSize*i, gp.tileSize*2*i);   
        attackDown2 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_down_2", gp.tileSize*i, gp.tileSize*2*i);  
        attackRight1 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);   
        attackRight2 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);   
        attackLeft1 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);   
        attackLeft2 = setup("/monsters/skeletonlord_phase1_attack/skeletonlord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
		} else {
			attackUp1 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_up_1", gp.tileSize*i, gp.tileSize*2*i);   
	        attackUp2 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_up_2", gp.tileSize*i, gp.tileSize*2*i);     
	        attackDown1 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_down_1", gp.tileSize*i, gp.tileSize*2*i);   
	        attackDown2 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_down_2", gp.tileSize*i, gp.tileSize*2*i);  
	        attackRight1 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);   
	        attackRight2 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);   
	        attackLeft1 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);   
	        attackLeft2 = setup("/monsters/skeletonlord_phase2_attack/skeletonlord_phase2_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
		}
	}
	public void setDialogue() {
		dialogues[0][0] = "No one can still my treasure";
		dialogues[0][1] = "You will die here";
		dialogues[0][2] = "welcome to your doom";
		dialogues[0][3] = "Ha Ha MuWahahahahahah";
	}
	public void setAction() {
		if(!inRage & life < maxLife/2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack *= 2;
		}
		if(getTileDistance(gp.player) < 10) {
			moveTowardPlayer(60);
		} else {
			// Get A random direction
			getRandomDirection(120);	
		}
		
		//check if it attacks
		if(!attacking) {
			checkAttackOrNot(60,gp.tileSize*7,gp.tileSize*5);
		}
    }
	public void damageReaction() {
		actionBlockCounter = 0;
	}
	public void checkDrop() {
		gp.bossBattleOn = false;
		Progress.skeletonBossDefeated = true;
		
		//Restore previous music
		gp.stopMusic();
		gp.playMusic(19);
		
		// remove iron doors
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null  && gp.obj[gp.currentMap][i].name.equals(ObjDoorIron.objName)) {
				gp.playSE(21);
				gp.obj[gp.currentMap][i] = null; 
			}
		}
		
	}


}
