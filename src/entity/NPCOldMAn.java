package entity;



import java.util.Random;
import main.GamePanel;

public class NPCOldMAn extends Entity{
	public NPCOldMAn(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 2;
		
		dialogueSet = -1;
		getImage();
		setDialogue();
	}
    public void getImage(){
        
   	 	up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);   
   }
    public void setDialogue() {
    	
    	dialogues[0][0] = "Hello lad.";
    	dialogues[0][1] = "So, you've come to the island to \nfind the teasure";
    	dialogues[0][2] = "I used to be a great Wizard \nbut now... I'm a bit too old for \ntaking an adventure.";
    	dialogues[0][3] = "Well, good luck on your journey.";
    	
    	dialogues[1][0] = "If you become tired rest at the water.";
    	dialogues[1][1] = "However the monsteres reapper if you rest. \nI have no freaken clue why they just work that way";
    	dialogues[1][2] = "in any case, don't push yourself too hard\nI think that \ntaking an adventure.";
    	dialogues[1][3] = "Remember you are great";
    	
    	dialogues[2][0] = " I wonder how to get through that door...";
		dialogues[2][1] = "Be careful there is a dangerous pit around here";

		dialogues[3][0] = "You again, don't you have \nanything better to do?";
		dialogues[3][1] = "You can learn some powerful magic \nin the house to the north west ";
    	
    }
	@Override
    public void setAction() {
    	
    	if(onPath) {
    		int goalCol = 12;
    		int goalRow = 9;
    		
    		// uncomment the below if you want the NPC to follow the character. 
//    		int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
//    		int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
    		
    		searchPath(goalCol, goalRow);
    	}
    	else {
	    	actionBlockCounter ++;
	    	if(actionBlockCounter == 120 ) {
	    		
	    	
	    	Random random = new Random();
	    	int i = random.nextInt(100) + 1; // picks a number from 1-100
	    	if(i <= 25) {
	    		direction = "up";
	    	}
	    	if(i> 25 && i<=50) {
	    		direction = "down";
	    		
	    	}
	    	if (i>50 && i<=75) {
	    		direction = "left";
	    	}
	    	if(i>75 && i<=100) {
	    		direction = "right";
	    	}
	    	actionBlockCounter = 0;
	    	}
    	}
    }

	@Override
	public void speak() {
	   
	   //Do this charageter specific stuff
	   facePlayer();
	   startDialogue(this, dialogueSet);
	   dialogueSet++;
	   if(dialogues[dialogueSet][0] == null) {
		  // dialogueSet = 0;   Or you can just negate the last one and repeat the last phrase
		   dialogueSet--;
	   }
	   
	   /*
	   if(gp.player.life < gp.player.maxLife/3) {
		   dialogueSet = 1;
	   }
	  
	    */
	   // maybe speaks something new after you kill a certain monster. 
	    
	   //onPath = true;
   }
   
   
   
	
}
