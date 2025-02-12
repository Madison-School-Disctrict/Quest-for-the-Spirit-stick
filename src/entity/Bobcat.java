package entity;




import java.util.Random;


import main.GamePanel;


public class Bobcat extends Entity{
    public static boolean follow;
    public Bobcat(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 2;
        follow = false;
        getImage();
        //setDialogue();
    }
    public void getImage(){
        up1 = setup("/NPC/bobcat_move/bobcat_up_walk_01", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/bobcat_move/bobcat_up_walk_02", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/bobcat_move/bobcat_down_walk_01", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/bobcat_move/bobcat_down_walk_02", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/bobcat_move/bobcat_left_walk_01", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/bobcat_move/bobcat_left_walk_02", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/bobcat_move/bobcat_right_walk_01", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/bobcat_move/bobcat_right_walk_02", gp.tileSize, gp.tileSize);  
   }
    public void setDialogue() {
       
    }


    public void update() {
       
        //if(!keyH.APressed){
        super.update();
       
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY-gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;
       
        if(!onPath && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1;
            if(i > 50 ) {
            	//if(tileDistance > 2) {
                onPath = true;
            	//}
            }
        }
       
        if(onPath && tileDistance > 20) {
            onPath = false;
        }
    //}
    }
    public void setAction() {
       
        if(onPath) {
            //int goalCol = 12;
            //int goalRow = 9;
           
            // uncomment the below if you want the NPC to follow the character.
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            
             
            
            if(!follow) {
            	goalCol = 22;
                goalRow = 22;
            }
           
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
   public void speak() {
       
       //Do this charageter specific stuff
      // super.speak();
       onPath = true;
   }
   
   
   
   
}

