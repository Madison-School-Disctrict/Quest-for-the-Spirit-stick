package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;


public class Entity {
	 GamePanel gp;
	 //the many, many instance variables of this class
	 //images
	 public BufferedImage up1, up2, down1, down2, left1, left2, 
	 right1, right2;
	 public BufferedImage attackUp1, attackUp2, attackDown1,
	 attackDown2, attackLeft1, attackLeft2, attackRight1,
	 attackRight2, guardUp, guardDown, guardLeft, guardRight; 
	 public BufferedImage axeUp1, axeUp2, axeDown1, axeDown2, 
	 axeLeft1, axeLeft2, axeRight1, axeRight2;
	 public BufferedImage image, image2, image3;
	 public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
	 public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	 public int solidAreaDefaultX, solidAreaDefaultY;
	 public boolean collisionOn =  false;
	 public String dialogues[][] = new String[20][20];
	 public Entity attacker;
	 public Entity linkedEntity;
	 public boolean temp = false;
	 
	 
	 //State
	 public int worldX,worldY;
	 public String direction = "down";
	 public int spriteNum = 1;
	 public int dialogueSet = 0;
	 public int dialogueIndex = 0;
	 public boolean collision = false;
	 public boolean invincible = false;
	 public boolean attacking = false;
	 public boolean alive = true;
	 public boolean dying = false;
	 public boolean hpBarOn = false;
	 public boolean onPath = false;
	 public boolean knockBack = false;
	 public String knockBackDirection;
	 public boolean guarding = false;
	 public boolean transparent = false;
	 public boolean offBalance = false;
	 public Entity loot;
	 public boolean opened = false;
	 public boolean inRage;
	 public boolean sleep = false;
	 public boolean drawing = true;
	 //counters
	 public int spriteCounter = 0;
	 public int actionBlockCounter = 0;
	 public int invincibleCounter = 0;
	 public int shotAvailableCounter = 0;
	 public int dyingCounter = 0;
	 public int hpBarCounter = 0;
	 public int knockBackCounter  = 0;
	 public int guardCounter = 0;
	 public int offBalanceCounter = 0;

	 //Character attributes
	 public String name;
	 public int defaultSpeed;
	 public int speed;
	 public int maxLife;
	 public int life;
	 public int maxMana;
	 public int mana;
	 public int maxAmmo;
	 public int ammo;
	 public int level;
	 public int strength;
	 public int dexterity;
	 public int attack;
	 public int defense;
	 public int exp;
	 public int nextLevelExp;
	 public int coin; 
	 public int motion1_duration;
	 public int motion2_duration;
	 public Entity currentWeapon;
	 public Entity currentShield;
	 public Entity currentMagic;
	 public Entity currentLight;
	 public Projectile projectile;
	 public boolean boss;
	 
	 //Items attributes
	 public int value;
	 public int attackValue;
	 public int defenseValue;
	 public String description = "";
	 public int useCost;
	 public ArrayList<Entity> inventory = new ArrayList<>();
	 public int maxInventorySize = 20;
	 public int price;
	 public int knockBackPower = 0;
	 public boolean stackable = false;
	 public int amount = 1;
	 public int lightRadius;
	 public int spiritStickPower;
	 
	 //type
	 public int type; // 0 = player; 1 = npc; 2 = monster; 3 = object
	 public final int type_player = 0; 
	 public final int type_npc = 1;
	 public final int type_monster = 2;
	 public final int type_sword = 3;
	 public final int type_axe = 4;
	 public final int type_shield = 5;
	 public final int type_consumable = 6;
	 public final int type_pickupOnly = 7;
	 public final int type_magic = 8;
	 public final int type_obstacle = 9;
	 public final int type_light = 10;
	 public final int type_pickaxe = 11;
	 public final int type_spiritStick = 12;
	 //public float alpha;
	 
	 //the class constructor. Finally. 
	 public Entity(GamePanel gp) {
		 this.gp = gp;}
	
	 //Methods
	 public int getScreenX() {
		 int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
		 return ScreenX; 
	 }
	 public int getScreenY() {
		 int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
		 return ScreenY;
	 }
	 public int getLeftX() {
		 return worldX + solidArea.x;
	 }
	 public int getRightX() {
		 return worldX + solidArea.x + solidArea.width;
	 }
	 public int getTopY() {
		 return worldY + solidArea.y;
	 }
	 public int getBottomY() {
		 return worldY + solidArea.y + solidArea.width;
	 }
	 public int getCol() {
		 return (worldX + solidArea.x)/gp.tileSize;
	 }
	 public int getRow() {
		 return (worldY + solidArea.y)/gp.tileSize;
	 }
	 public int getCenterX() {
		 int centerX = worldX + left1.getWidth()/2;
		 return centerX;
 	 }
	 public int getCenterY() {
		 int centerY = worldY + up1.getHeight()/2;
		 return centerY;
 	 }
	 public int getXdistance(Entity target) {
		 int xDistance = Math.abs(getCenterX() - target.getCenterX()); 
		 return xDistance;
	 }
	 public int getYdistance(Entity target) {
		 int yDistance = Math.abs(getCenterY() - target.getCenterY()); 
		 return yDistance;
	 }
	 public int getTileDistance(Entity target) {
		 int tileDistance = (getXdistance(target)+ getYdistance(target))/gp.tileSize;
		 return tileDistance;
	 }
	 public int getGoalCol(Entity target) {
		 int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
		 return goalCol;
	 }
	 public int getGoalRow(Entity target) {
		 int goalRow= (target.worldY + target.solidArea.y)/gp.tileSize;
		 return goalRow;
	 }
	 public void resetCounters() {
		 
		 spriteCounter = 0;
		 actionBlockCounter=0;
		 invincibleCounter = 0;
		 shotAvailableCounter=0;
		 dyingCounter = 0;
		 hpBarCounter = 0;
		 knockBackCounter  = 0;
		 guardCounter = 0;
		 offBalanceCounter = 0;
		 
	 }
	 public void setLoot(Entity loot) {
		 
	 }
	 public void setAction() { }
	 public void move(String direction) { }
	 public void damageReaction() {}
	 public void speak() { }  
	 public void facePlayer() {
		 switch(gp.player.direction) {
		   case "up": direction = "down";  break;
		   case "down": direction = "up";  break;
		   case "left":  direction = "right";  break;
		   case " right":  direction = "left";  break;
		 }
	 }
	 public void startDialogue(Entity entity, int setNum) {
		 gp.gameState = gp.dialogueState;
		 gp.ui.npc = entity;
		 dialogueSet = setNum;		 
	 }
	 public void interact() {}
	 public boolean use(Entity entity) {return false;}
	 public void checkDrop(){}
	 public void dropItem(Entity droppedItem) {
		 for(int i = 0; i < gp.obj[1].length; i++ ) {
			 if(gp.obj[gp.currentMap][i] == null) {
				 gp.obj[gp.currentMap][i] = droppedItem;
				 gp.obj[gp.currentMap][i].worldX = worldX;
				 gp.obj[gp.currentMap][i].worldY = worldY;
				 break;
			 }
		 }
	 } 
	 public Color getParticleColor() {
			Color color = null;		
			return color;
		}
	 public int getParticleSize() {
			int size = 0; // 6 pixels
			return size;
		}
	 public int getParticleSpeed() {
			int speed = 0;
			return speed;
		}	
	 public int getParticleMaxLife() {
			int maxLife = 0;
			return maxLife;
		}  
	 public void generateParticle(Entity generator, Entity target) {
		 Color color = generator.getParticleColor();
		 int size = generator.getParticleSize();
		 int speed = generator.getParticleSpeed();
		 int maxLife = generator.getParticleMaxLife();
		//Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd )
		 Particle pl = new Particle(gp, target, color, size, speed, maxLife,-2, -1);
		 Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		 Particle p3 = new Particle(gp, target, color, size, speed, maxLife,-2, 1);
		 Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		 gp.particleList.add(pl);
		 gp.particleList.add(p2);
		 gp.particleList.add(p3);
		 gp.particleList.add(p4);
		 
	 } 
	 public void checkCollision() {
		 collisionOn = false;
		 gp.cCheck.checkTile(this);
		 gp.cCheck.checkObject(this, false);
		 gp.cCheck.checkEntity(this, gp.npc);
		 gp.cCheck.checkEntity(this, gp.monster);	
		 gp.cCheck.checkEntity(this, gp.iTile);
		 boolean contactPlayer = gp.cCheck.checkPlayer(this);
		 
		 if(this.type == type_monster && contactPlayer == true) {
			 damagePlayer(attack);
		 }
	 } 
	 public void update() {
		 
		 if(!sleep) {
		 if (knockBack) {
			 
			 checkCollision();
			 
			 if( collisionOn) {
				 knockBackCounter = 0;
				 knockBack = false;
				 speed = defaultSpeed;
			 } else if( !collisionOn) {
				 switch(knockBackDirection) {
				 	case "up": worldY -= speed; break;
			 		case "down": worldY += speed; break;
			 		case "left": worldX -= speed; break;
			 		case "right": worldX += speed; break; 
			 		case "none":  worldX += 0; worldY +=0; break;
				 }
			 }
			 knockBackCounter++;
			 if (knockBackCounter > 10) {
				 knockBackCounter = 0;
				 knockBack = false;
				 speed = defaultSpeed;
			 }
			 
		 } else if(attacking) {attacking();} 
		 
		 else {
			 
			 setAction();
			 checkCollision(); 
			 //If collision is false , can move
			 	if(collisionOn == false ) {
			 		switch(direction) {
			 		case "up": worldY -= speed; break;
			 		case "down": worldY += speed; break;
			 		case "left": worldX -= speed; break;
			 		case "right": worldX += speed; break; 
			 		case "none":  worldX += 0; worldY +=0; break;
			 		}
			 	}
			 	
			 	 spriteCounter++;
			        if(spriteCounter > 24) {
			        	if(spriteNum ==1) {
			        		spriteNum = 2;
			        	}
			        	else if(spriteNum ==2) {
			        		spriteNum = 1;
			        	}
			        	spriteCounter = 0;
			        }
			 
		 }
		 
	       
	        
	        if(invincible) {
	     	   invincibleCounter++;
	     	   if(invincibleCounter > 40) {
	     		   invincible = false;
	     		   invincibleCounter = 0;
	     	   }
	        }
	        if(shotAvailableCounter < 30) {
	     	   shotAvailableCounter++;
	        }
	        if(offBalance) {
	        	offBalanceCounter++;
	        	if(offBalanceCounter > 60) {
	        		offBalance = false;
	        		offBalanceCounter = 0;
	        	}
	        }
		 }
		 
	 }
	 public void checkAttackOrNot(int rate, int straight, int horizontal) {
		 boolean targetInRange = false;
		 int xDis = getXdistance(gp.player);
		 int yDis = getYdistance(gp.player);
		 
		 switch(direction) {
		 case "up": 
			 if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
			 targetInRange = true;
		 }
			 break;
		 case "down":
			 if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
				 targetInRange = true;
			 }
			 break;
		 case "left":
			 if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
				 targetInRange = true;
			 }
			 break;
		 case "right":
			 if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
				 targetInRange = true;
			 }
			 break;
				 
		 }
		 if(targetInRange) {
			 //check for initiation of an attack
			 int i = new Random().nextInt(rate);
			 if (i == 0 ) {
				 attacking = true;
				 spriteNum = 1;
				 spriteCounter = 0;
				 shotAvailableCounter = 0;
			 }
		 }
	 }
 	 public void checkShootOrNot(int rate, int shotInterval) {
		 
		 int i = new Random().nextInt(rate) + 1;
		 //System.out.println(i);
 		if(i == 1 && projectile.alive == false && shotAvailableCounter == shotInterval) {
 			projectile.set(worldX, worldY, direction, true, this);
 			
 			for( int ii = 0 ; ii < gp.projectile[1].length; ii++) {
     			if( gp.projectile[gp.currentMap][ii] == null) {
     				gp.projectile[gp.currentMap][ii] = projectile;
     				break;
     			}
     		}
 			
 			shotAvailableCounter = 0;
 		}
		 
	 }
	 public void checkStartChaseingOrNot(Entity target, int distance, int rate) {
		 if(getTileDistance(target) < distance) {
			 int i = new Random().nextInt(rate);
			 if(i == 0) {
				 onPath = true;
			 }
		 }
	 }
	 public void checkStopChaseingOrNot(Entity target, int distance, int rate) {
		 if(getTileDistance(target) > distance) {
			 int i = new Random().nextInt(rate);
			 if(i == 0) {
				 onPath = false;
			 }
		 }
	 }
	 public void getRandomDirection(int interval) {
		 
		 actionBlockCounter ++;
	    
		 if(actionBlockCounter > interval ) {
			 
	    	Random random = new Random();
	    	int i = random.nextInt(100) + 1; // picks a number from 1-100
	    	
	    	if(i <= 25) {direction = "up";}
	    	if(i > 25 && i <= 50) {direction = "down";}
	    	if (i > 50 && i <= 75) {direction = "left";}
	    	if(i > 75 && i <= 100) {direction = "right";}
	    	actionBlockCounter = 0;
	    	}
	 }
	 public void moveTowardPlayer(int interval) {
		 actionBlockCounter ++;
		 if(actionBlockCounter > interval ) {
			 if(getXdistance(gp.player) > getYdistance(gp.player)) {
				 if(gp.player.getCenterX() < getCenterX()) {
					 direction = "left";
				 }else { 
					 direction = "right";
				 }
			 } else if(getXdistance(gp.player) < getYdistance(gp.player)){
				 if(gp.player.getCenterY() < getCenterY()) {
					 direction = "up";
				 }else { 
					 direction = "down";
				 }
			 }
			 actionBlockCounter = 0;
		 }
	 }
	 public String getOppositeDirection(String getDirection) {
		 String oppositeDirection = "";
		 switch(direction) {
		 case "up": oppositeDirection = "down"; break;
		 case "down": oppositeDirection = "up"; break;
		 case "left": oppositeDirection = "right"; break;
		 case "right": oppositeDirection = "left"; break;
		 }
		 return oppositeDirection;
	 }
	 public void attacking() {
	    	spriteCounter++;
	    	
	    	if(spriteCounter <= motion1_duration) {
	    		spriteNum = 1;
	    	}
	    	if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
	    		spriteNum = 2;
	    		//save current x and  y 
	    		int currentWorldX = worldX;
	    		int currentWorldY = worldY;
	    		int solidAreaWidth = solidArea.width;
	    		int solidAreaHeight = solidArea.height;
	    		//adjust players location
	    		
	    		switch(direction) {
	    		case "up": worldY -= attackArea.height; break;
	    		case "down": worldY += attackArea.height; break;
	    		case "left": worldX -= attackArea.width; break;
	    		case "right": worldX += attackArea.width; break;  		
	    		}
	    		
	    		//attackArea becomes solid area
	    		solidArea.width = attackArea.width;
	    		solidArea.height = attackArea.height;
	    		
	    		
	    		if(type == type_monster) {
	    			if(gp.cCheck.checkPlayer(this)) {
	    				damagePlayer(attack);
	    			}
	    		}else { // For Player only 
	    			// Check monster collision with updated possion and area.
		    		int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
		    		gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
		    		
		    		// check interactive tile collision
		    		int iTileIndex = gp.cCheck.checkEntity(this, gp.iTile);
		    		gp.player.damageInteractiveTile(iTileIndex);
		    		
		    		int projectileIndex = gp.cCheck.checkEntity(this, gp.projectile);
		    		gp.player.damageProjectile(projectileIndex);
	    		}
	    		
	    		
	    		
	    		//after attack return to original position and arear
	    		worldX = currentWorldX;
	    		worldY = currentWorldY;
	    		solidArea.width = solidAreaWidth;
	    		solidArea.height = solidAreaHeight;
	    	}
	    	if(spriteCounter > motion2_duration) {
	    		spriteNum = 1;
	    		spriteCounter = 0;
	    		attacking = false;
	    	} 
	    	
	    	
	    } 
	 public void damagePlayer(int attack) {
		 if(gp.player.invincible == false) {
			 

			  int damage = attack - gp.player.defense;
			  
			  //Get an opposite direction of this attacker
			  String canGuardDirection = getOppositeDirection(direction);
			  
			  if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {
				 // check parry 
				  if(gp.player.guardCounter < 10000) {//increase the number to make parry easier.
					  damage = 0;
					  gp.playSE(16);
					  setKnockBack(this, gp.player,knockBackPower);
					  offBalance = true;
					  spriteCounter = -60;
				  }else {//Normal blocked damage
				  
					  damage /=3;
					  gp.playSE(15);
				  }
			  } else {
				  gp.playSE(6);
				  if(damage < 1) {
					  damage = 1;
				  }
			  }
			  // we can give damage	  
			  if(!gp.player.getImoralmode()){
				if(damage != 0 ) {
					gp.player.transparent = true;
					setKnockBack(gp.player,this,knockBackPower);
				}
			 	gp.player.life -= damage;
			 	gp.player.invincible = true;
			  }//else{System.out.println("imortal mode on:  " + gp.player.getImoralmode());}
		 }
	}
	 public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
    	this.attacker = attacker;
    	target.knockBackDirection = attacker.direction;
    	target.speed += knockBackPower;
    	target.knockBack = true;
    }
	 public boolean inCamera() {
		 boolean inCamera = false;
		 if(worldX + gp.tileSize *5 > gp.player.worldX-gp.player.screenX - gp.tileSize&& 
				   worldX - gp.tileSize < gp.player.worldX+gp.player.screenX + gp.tileSize&&
				   worldY + gp.tileSize *5 > gp.player.worldY-gp.player.screenY - gp.tileSize&& 
				   worldY - gp.tileSize < gp.player.worldY+gp.player.screenY + gp.tileSize ) {
			 inCamera = true;
		 }
		 
		 
		 return inCamera;
	 }
	 public void draw(Graphics2D g2) {
			
		 	BufferedImage image = null;
		
			if(inCamera()){
				
				 int tempScreenX = getScreenX();
			     int tempScreenY = getScreenY();
				
				switch(direction){
	            case "up":
	            	if(!attacking) {
	            	if(spriteNum == 1) {image = up1;}
	            	if(spriteNum == 2) {image = up2;}
	            	}
	            	if(attacking) {
	            		tempScreenY = getScreenY() - up1.getHeight();
	                	if(spriteNum == 1) {image = attackUp1;}
	                	if(spriteNum == 2) {image = attackUp2;}
	                	}
	                break;
	            case "down":
	            	if(!attacking) {
	            	if(spriteNum == 1) {image = down1;}
	            	if(spriteNum == 2) {image = down2;}
	            	}
	            	if(attacking) {
	                	if(spriteNum == 1) {image = attackDown1;}
	                	if(spriteNum == 2) {image = attackDown2;}
	                	}
	                break;
	            case "left": 
	            	if(!attacking) {
	            	if(spriteNum == 1) {image = left1;}
	            	if(spriteNum == 2) {image = left2;}
	            	}
	            	if(attacking) {
	            		tempScreenX = getScreenX() - left1.getHeight();
	                	if(spriteNum == 1) {image = attackLeft1;}
	                	if(spriteNum == 2) {image = attackLeft2;}
	                	}
	                break;
	            case "right":
	            	if(!attacking) {
	            	if(spriteNum == 1) {image = right1;}
	            	if(spriteNum == 2) {image = right2;}
	            	}
	            	if(attacking) {
	                	if(spriteNum == 1) {image = attackRight1;}
	                	if(spriteNum == 2) {image = attackRight2;}
	                	}
	                break;
	        }    
			        
			        if(invincible) {
			        	hpBarOn = true;
			        	hpBarCounter = 0;
			        	changeAlpha(g2, 0.4f);
			        	
			        }
			        if(dying) {
			        	dyingAnimation(g2);
			        }
				g2.drawImage(image, tempScreenX, tempScreenY, null);
				
				changeAlpha(g2, 1f);
			}
			
		}
	 public void dyingAnimation(Graphics2D g2) {
		 dyingCounter++;
		 int i=5;
		 if(dyingCounter <= i ) { changeAlpha(g2, 0f);}
		 if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		 if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		 if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);} 
		 if(dyingCounter > i*8) {
		 alive = false;
		 }
	 }
	 public void changeAlpha(Graphics2D g2, float alphaValue) {
		 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
	 }
	 public BufferedImage setup(String imagePath , int width, int height) {
		   	UtilityTool uTool = new UtilityTool();
		   	BufferedImage image = null;
				try {
					image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
					image = uTool.scaledImage(image, width, height);
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				return image;
		   }
	 public void searchPath(int goalCol, int goalRow) {
		 int startCol = (worldX + solidArea.x)/gp.tileSize;
		 int startRow = (worldY + solidArea.y)/gp.tileSize;
		 gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow,this);
		
		 if(gp.pFinder.search() == true) {
			 //next  wolrdX and wolrdY
			 int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			 int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			 // entity's solid area position
			 int enLeftX = worldX + solidArea.x;
			 int enRightX = enLeftX + solidArea.width;
			 int enTopY = worldY + solidArea.y;
			 int enBottomY = enTopY + solidArea.height;
			 
			 if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize ) {
				 direction = "up";
			 }
			 else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize ) {
				 direction = "down";
			 }
			 else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize ) {
				 //left or right 
				 if(enLeftX > nextX) {
					 direction = "left";
				 }
				 if(enLeftX < nextX) {
					 direction = "right";
				 }

			 }
			 
			 
			 else if(enTopY > nextY && enLeftX > nextX) {
				 // up or left
				 direction = "up";
				 checkCollision();
				 if(collisionOn) {
					 direction = "left";
				 }
			 } 
			 else if(enTopY > nextY && enLeftX < nextX) {
				//up or right
				 direction = "up";
				 checkCollision();
				 if(collisionOn) {
					 direction = "right";
				 }
			 }  
			 else if(enTopY < nextY && enLeftX > nextX) {
				 //down or left
				 direction = "down";
				 checkCollision();
				 if(collisionOn) {
					 direction = "left";
				 }
			 }  
			 else if(enTopY < nextY && enLeftX < nextX) {
				 //down or right
				 direction = "down";
				 checkCollision();
				 if(collisionOn) {
					 direction = "right";
				 }
			 }
			 
			 
			// if he reaches the goal stop the search
			// /* use the comment out if you want the NPC to follow you. 
			 
			 int nextCol = gp.pFinder.pathList.get(0).col;
			 int nextRow = gp.pFinder.pathList.get(0).row;
			 if(nextCol == goalCol && nextRow == goalRow) {
				 onPath = false;
			 }
			
			 // */ 
		 }
	 }
	 public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;
		//Check surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch (user.direction) {
			case "up": nextWorldY = user.getTopY() - gp.player.speed; break;
			case "down": nextWorldY = user.getBottomY() + gp.player.speed; break;
			case "left": nextWorldX = user.getLeftX() - gp.player.speed; break;
			case "right": nextWorldX = user.getRightX() + gp.player.speed; break;
		}
		
		int col = nextWorldX / gp.tileSize;
		int row = nextWorldY / gp.tileSize;
		
		for( int i = 0; i <  target[1].length ;i++ ) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col && 
						target[gp.currentMap][i].getRow() == row && 
						target[gp.currentMap][i].name.equals(targetName)) {
					index = i;
					break;
				}
			}
			
		}
		
		 return index;
	 }
}
