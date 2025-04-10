package entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GamePanel;
import object.ObjDoorIron;
import tileInteractive.ITMetalPlate;
import tileInteractive.InteractiveTile;

public class NPCBigRock extends Entity{
	public static final String npcName= "BigRock";
	public NPCBigRock(GamePanel gp) {
		super(gp);
		name = npcName;
		direction = "down";
		speed = 4;
		
		solidArea = new Rectangle();
		solidArea.x = 2;
		solidArea.y = 6;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 40;
		
		
		dialogueSet = -1;
		getImage();
		setDialogue();
		
	}
    public void getImage(){
        
   	 	up1 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/bigrock", gp.tileSize, gp.tileSize);   
   }
    public void setDialogue() {
    	
    	dialogues[0][0] = "It's a Giant Rock";   	
    }
    public void setAction() {
   	
    }
    public void update() {
    	
    }
    public void move(String d) {
    	this.direction = d;
    	checkCollision();
    	if(!collisionOn) {
    		switch(direction) {
		 	case "up": worldY -= speed; break;
	 		case "down": worldY += speed; break;
	 		case "left": worldX -= speed; break;
	 		case "right": worldX += speed; break; 
	 		case "none":  worldX += 0; worldY +=0; break;
		 } 
    	}
    	
    	detectPlate();
    }
   public void speak() {
	   
	   //Do this charageter specific stuff
	   facePlayer();
	   startDialogue(this, dialogueSet);
	   dialogueSet++;
	   if(dialogues[dialogueSet][0] == null) {
		   dialogueSet--;
	   }
   }
   public void detectPlate() {
	   ArrayList<InteractiveTile> plateList = new ArrayList<>();
	   ArrayList<Entity> rockList = new ArrayList<>();
	   
	   //creates a plate LIst
	   for(int i = 0; i < gp.iTile[1].length; i++) {
		   
		   if(gp.iTile[gp.currentMap][i] != null && 
				   gp.iTile[gp.currentMap][i].name != null &&
			      gp.iTile[gp.currentMap][i].name.equals(ITMetalPlate.itName)) {
			   plateList.add(gp.iTile[gp.currentMap][i]);
		   }
	   }
	   
	   //Creates a rock list
	   for(int i = 0; i < gp.npc[1].length; i++) {
		   
		   if(gp.npc[gp.currentMap][i] != null && 
			      gp.npc[gp.currentMap][i].name.equals(NPCBigRock.npcName)) {
			   rockList.add(gp.npc[gp.currentMap][i]);
		   }
	   }
	   
	   int count = 0;
	   
	   //Scan Plate list
	   
	   for(int i = 0 ; i < plateList.size(); i++) {
		   int xDistance = Math.abs(worldX - plateList.get(i).worldX);
		   int yDistance = Math.abs(worldY - plateList.get(i).worldY);
		   int distance = Math.max(xDistance, yDistance);
		   if(distance < 8) {
			   if(linkedEntity == null) {
			   linkedEntity = plateList.get(i);
			   gp.playSE(3);
			   }
		   }else {
			   if(linkedEntity == plateList.get(i)) {
			   linkedEntity = null;
			   }
		   }
	   }
	   
	   //Scan RockList
	   for(int i = 0; i <  rockList.size(); i++) {
		   
		   //count the rock on the plate
		   if(rockList.get(i).linkedEntity != null) {
			   count++;
		   }
	   }
	   
	   if( count == rockList.size()) {
		   for(int i = 0; i < gp.obj[1].length; i++ ) {
			   if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(ObjDoorIron.objName)) {
				   gp.obj[gp.currentMap][i] = null;
				   gp.playSE(21);
			   }
		   }
	   }
	   
   }
}
