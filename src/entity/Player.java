package entity;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import object.ObjAxe;
import object.ObjFireBall;
import object.ObjFireBallBlue;
import object.ObjFireBallWhite;
import object.ObjKey;
import object.ObjLantern;
import object.ObjPotionRed;
import object.ObjShieldWood;
import object.ObjSpiritStick;
import object.ObjSwordNormal;
import object.ObjTent;

public class Player extends Entity{
    
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    boolean hasFireBall = false;
    public boolean lightUpdated = false;
    //public boolean imortal;

    public Player(GamePanel gp, KeyHandler keyH){
    	super(gp);
        
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2- (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;
        
//        attackArea.width = 36;
//        attackArea.height = 36;
        
        setDefaultValues();
             
    }
    public void setDefaultValues(){ 	
    	//Need to edit player starting location with each map 
//    	//STarting location of worldmap
    	worldX = gp.tileSize*23;
    	worldY = gp.tileSize*21;
//    	//STarting location of thunder
//    	worldX = gp.tileSize*31;
//    	worldY = gp.tileSize*80;
    	
    	//Stating location for rigby 1 map
//    	worldX = gp.tileSize*35;
//        worldY = gp.tileSize*95; 
    	defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";
        
        //Player status 
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        strength = 1; // the more strength he has, the more damage he gives
        dexterity = 1; // The more desterity he has, the less damage he recives. 
        exp = 0 ;
        nextLevelExp = 5;
        coin = 500;
        
        currentWeapon = new ObjSwordNormal(gp);
        currentShield = new ObjShieldWood(gp);
        currentLight = null;
        projectile = new ObjFireBall(gp);
        attack = getAttack(); // The total attack value is decided by strength and weapon.
        defense = getDefense(); // The total defense value is decided by dexterity and durrent shield.  
        getImage();
        getAttackImage();
        getGuardImage(); 
        setItems();
        setDialogue();
    }
    public void setDefualtPositions() {
    	
    	gp.currentMap = 0;
    	worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        direction = "down";
        
    }
    public void setDialogue() {
    	dialogues[0][0] = "You are level " + level + " now!\n" 
				   + "You feel stronger!";
    	
    }
    public void restoreStatus() {
    	life = maxLife;
    	mana = maxMana;
    	speed = defaultSpeed;
    	invincible = false;
    	transparent = false;
    	attacking = false;
    	guarding = false;
    	knockBack = false;
    	lightUpdated = true;
    	}
    public void setItems() {
    	inventory.clear();
    	inventory.add(currentWeapon);
    	inventory.add(currentShield);
    	inventory.add(new ObjKey(gp));
    	inventory.add(new ObjLantern(gp));
    	inventory.add(new ObjTent(gp));
    	inventory.add(new ObjAxe(gp));
    	inventory.add(new ObjFireBall(gp));
    	inventory.add(new ObjPotionRed(gp));
    	inventory.add(new ObjFireBallBlue(gp));
    	inventory.add(new ObjFireBallWhite(gp));
    	inventory.add(new ObjSpiritStick(gp));
    	
    	
    	
    }
    public int getAttack() {
    	attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
    	return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense() {
    	
    	return defense = dexterity * currentShield.defenseValue;
    	
    }
    public int getCurrentWeaponSlot() {
    	int currentWeaponSlot = 0;
    	for(int i = 0 ; i< inventory.size(); i++) {
    		if(inventory.get(i) == currentWeapon) {
    			currentWeaponSlot = i;
    		}
    	}
    	return currentWeaponSlot;
    }
    public int getCurrentMagicSlot() {
    	int currentMagicSlot = 0;
    	for(int i = 0 ; i< inventory.size(); i++) {
    		if(inventory.get(i) == currentMagic) {
    			currentMagicSlot = i;
    			
//    			}
    			 
    		}
    	}
    	return currentMagicSlot;
    }
    public int getCurrentShieldSlot() {
    	int currentShieldSlot = 0;
    	for(int i = 0 ; i< inventory.size(); i++) {
    		if(inventory.get(i) == currentShield) {
    			currentShieldSlot = i;
    		}
    	}
    	return currentShieldSlot;
    }
    public void getImage(){
        
    	 up1 = setup("/player/boy_move/boy_up_1", gp.tileSize, gp.tileSize);
         up2 = setup("/player/boy_move/boy_up_2", gp.tileSize, gp.tileSize);
         down1 = setup("/player/boy_move/boy_down_1", gp.tileSize, gp.tileSize);
         down2 = setup("/player/boy_move/boy_down_2", gp.tileSize, gp.tileSize);
         right1 = setup("/player/boy_move/boy_right_1", gp.tileSize, gp.tileSize);
         right2 = setup("/player/boy_move/boy_right_2", gp.tileSize, gp.tileSize);
         left1 = setup("/player/boy_move/boy_left_1", gp.tileSize, gp.tileSize);
         left2 = setup("/player/boy_move/boy_left_2", gp.tileSize, gp.tileSize);          
    } 
    public void getSleepingImage(BufferedImage img) {
    	up1 = img;
        up2 = img;
        down1 = img;
        down2 =  img;
        right1 = img;
        right2 = img;
        left1 = img;
        left2 = img; 
    }
    public void getAttackImage() {	
    	if(currentWeapon.type == type_sword ) {
	    	attackUp1 = setup("/player/boy_attack_sword/boy_attack_up_1", gp.tileSize, gp.tileSize*2);   
	        attackUp2 = setup("/player/boy_attack_sword/boy_attack_up_2", gp.tileSize, gp.tileSize*2);     
	        attackDown1 = setup("/player/boy_attack_sword/boy_attack_down_1", gp.tileSize, gp.tileSize*2);   
	        attackDown2 = setup("/player/boy_attack_sword/boy_attack_down_2", gp.tileSize, gp.tileSize*2);  
	        attackRight1 = setup("/player/boy_attack_sword/boy_attack_right_1", gp.tileSize*2, gp.tileSize);   
	        attackRight2 = setup("/player/boy_attack_sword/boy_attack_right_2", gp.tileSize*2, gp.tileSize);   
	        attackLeft1 = setup("/player/boy_attack_sword/boy_attack_left_1", gp.tileSize*2, gp.tileSize);   
	        attackLeft2 = setup("/player/boy_attack_sword/boy_attack_left_2", gp.tileSize*2, gp.tileSize); 
    	}  
    	if(currentWeapon.type == type_axe) {
    		attackUp1 = setup("/player/boy_attack_axe/boy_axe_up_1", gp.tileSize, gp.tileSize*2);   
    		attackUp2 = setup("/player/boy_attack_axe/boy_axe_up_2", gp.tileSize, gp.tileSize*2); 
    		attackDown1 = setup("/player/boy_attack_axe/boy_axe_down_1", gp.tileSize, gp.tileSize*2);   
    		attackDown2 = setup("/player/boy_attack_axe/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
    		attackLeft1 = setup("/player/boy_attack_axe/boy_axe_left_1", gp.tileSize*2, gp.tileSize);   
    		attackLeft2 = setup("/player/boy_attack_axe/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
    		attackRight1 = setup("/player/boy_attack_axe/boy_axe_right_1", gp.tileSize*2, gp.tileSize);   
    		attackRight2 = setup("/player/boy_attack_axe/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
    	}
    	if(currentWeapon.type == type_pickaxe) {
    		attackUp1 = setup("/player/boy_attack_pick/boy_pick_up_1", gp.tileSize, gp.tileSize*2);   
    		attackUp2 = setup("/player/boy_attack_pick/boy_pick_up_2", gp.tileSize, gp.tileSize*2); 
    		attackDown1 = setup("/player/boy_attack_pick/boy_pick_down_1", gp.tileSize, gp.tileSize*2);   
    		attackDown2 = setup("/player/boy_attack_pick/boy_pick_down_2", gp.tileSize, gp.tileSize*2);
    		attackLeft1 = setup("/player/boy_attack_pick/boy_pick_left_1", gp.tileSize*2, gp.tileSize);   
    		attackLeft2 = setup("/player/boy_attack_pick/boy_pick_left_2", gp.tileSize*2, gp.tileSize);
    		attackRight1 = setup("/player/boy_attack_pick/boy_pick_right_1", gp.tileSize*2, gp.tileSize);   
    		attackRight2 = setup("/player/boy_attack_pick/boy_pick_right_2", gp.tileSize*2, gp.tileSize);
    	}
    	
    }
    
   
    public void getGuardImage() { 	   
		guardUp = setup("/player/GuardingSprites/boy_guard_up", gp.tileSize, gp.tileSize);
		guardDown = setup("/player/GuardingSprites/boy_guard_down", gp.tileSize, gp.tileSize);
		guardLeft = setup("/player/GuardingSprites/boy_guard_left", gp.tileSize, gp.tileSize);
		guardRight = setup("/player/GuardingSprites/boy_guard_right", gp.tileSize, gp.tileSize);
    }

    @Override
    public void update(){	
    	
    		if (knockBack) {
    			//Check Collision
    		 	collisionOn = false;
    		 	gp.cCheck.checkTile(this);
    		 	gp.cCheck.checkObject(this, true);
    		 	gp.cCheck.checkEntity(this, gp.npc);
    		 	gp.cCheck.checkEntity(this, gp.monster);
    		 	gp.cCheck.checkEntity(this, gp.iTile);
			 
			 
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
			 
		 }
    	
 		else if(attacking) { 
    		attacking();
    	}
    	else if(gp.keyH.bPressed) {
    		guarding = true;
    		guardCounter++;
    	}
    		
    	
    	else if(!gp.keyH.directionLast.isEmpty() || gp.keyH.debug  || gp.keyH.spacePressed ) {
			//System.out.println(gp.keyH.directionLast);
				if(!gp.keyH.directionLast.isEmpty()){
					direction = gp.keyH.directionLast.get(gp.keyH.directionLast.size()-1);
				}
    		 	//Check Tile Collision
    		 	collisionOn = false;
    		 	gp.cCheck.checkTile(this);
    		 	
    		 	//check object collision
    		 	int objIndex = gp.cCheck.checkObject(this, true);
    		 	pickUpObject(objIndex);
    		 	    		 	
    		 	//Check NPC Collison
    		 	int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
    		 	interactNPC(npcIndex);
    		 	
    		 	// check nmonster collision
    		 	int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
    		 	contactMonster(monsterIndex);
    		 	
    		    // check interactive tile collision
    		 	gp.cCheck.checkEntity(this, gp.iTile);
    		 	
    		 	//Check event colision
    		 	gp.eHandler.checkEvent();
    		 	
    		 	//IF colliosn is false, player can move
				//commmenting out the conditions allows for no collisions on tiles, monsters and objects.
    		 	if(collisionOn == false && gp.keyH.spacePressed == false ) {
    		 		switch(direction) {
    		 		case "up": worldY -= speed; break;
    		 		case "down": worldY += speed; break;
    		 		case "left": worldX -= speed; break;
    		 		case "right": worldX += speed; break; 
    		 		case "none":  worldX += 0; worldY +=0; break;
    		 		}
    		 	}
    		 
    		 	if(gp.keyH.spacePressed && !attackCanceled) {
    		 		gp.playSE(7);
    		 		attacking = true;
    		 		spriteCounter = 0;
    		 	}
    		 	
    		 	attackCanceled = false;
    		 	gp.keyH.spacePressed = false;
    		 	guarding = false;
    		 	guardCounter = 0;
    		 	
    	        spriteCounter++;
    	        if(spriteCounter > 12) {
    	        	if(spriteNum ==1) {
    	        		spriteNum = 2;
    	        	}
    	        	else if(spriteNum == 2) {
    	        		spriteNum = 1;
    	        	}
    	        	spriteCounter = 0;
    	        }
    	}else{
    		standCounter++;
    		if(standCounter == 20) {
    		spriteNum = 1;
    		standCounter = 0;
    		}
    		guarding = false;
    		guardCounter = 0;
    	}
    	  	
    	///this is outSide the if statement
    	if(this.projectile != null) {
    	if(gp.keyH.shotKeyPressed == true  && !projectile.alive
    			&& shotAvailableCounter == 100 && projectile.haveResource(this) == true && hasFireBall) {
    		// Set Default Coordinates.  Direction and user
    		projectile.set(worldX, worldY, direction, true, this);
    		
    		//Subtract the cost
    		projectile.subtractResource(this);
    		
    		//Check Vacancy  
    		for( int i = 0 ; i < gp.projectile[1].length; i++) {
    			if( gp.projectile[gp.currentMap][i] == null) {
    				gp.projectile[gp.currentMap][i] = projectile;
    				break;
    			}
    		}
    		shotAvailableCounter = 0;
    		gp.playSE(10);
    		
    	}
    	}
       if(invincible) {
    	   invincibleCounter++;
    	   if(invincibleCounter > 60) {
    		   invincible = false;
    		   transparent = false;
    		   invincibleCounter = 0;
    	   }
       }
       
       if(shotAvailableCounter < 100) {
    	   shotAvailableCounter++;
       }
       
       if(life > maxLife) {
			life = maxLife;
		}
       if(mana > maxMana) {
			mana = maxMana;
		}
       
       if(!gp.keyH.imortalModeOn){
	       if(life <= 0) {
	    	   gp.gameState = gp.gameOverState;
	    	   gp.ui.message.clear();
	    	   gp.stopMusic();
	    	   gp.playSE(12);
	       }
       }
    }
    public void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generates Particles
			
			generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i] );
			
			if(gp.iTile[gp.currentMap][i].life <= 0){
				//gp.iTile[gp.currentMap][i].checkDrop()
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
		
	}
	public void pickUpObject(int i) {
    	if(i != 999) {
    		
    		
    		//PickUP Only
    		
    		if(gp.obj[gp.currentMap][i].type == type_pickupOnly){
    			 gp.obj[gp.currentMap][i].use(this);
    			 gp.obj[gp.currentMap][i] = null;
    		}
    		
    		else if( gp.obj[gp.currentMap][i].type == type_obstacle){
    			if (gp.keyH.spacePressed) {
    				attackCanceled = true;
    				gp.obj[gp.currentMap][i].interact();
    			}
    		}
    		
    		// inventory Items
    		else {
    		String text;
    		if(canObtainItem(gp.obj[gp.currentMap][i])) {
    			//inventory.add(gp.obj[gp.currentMap][i]);
    			gp.playSE(1);
    			text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
    		}
    		else {
    			text = " You can not carry any more items";
    		}
    		gp.ui.addMessage(text);
    		gp.obj[gp.currentMap][i] = null;
    	}
    		
    	}
    	
    }      
    public void interactNPC(int i) {
    	
    	if(i != 999) {
	    	if(gp.keyH.spacePressed == true) {
		    		attackCanceled = true;
			        gp.npc[gp.currentMap][i].speak();
		    }	
	    	
	    	gp.npc[gp.currentMap][i].move(direction);
    	}
    } 
	public boolean getImoralmode(){
		return gp.keyH.imortalModeOn;
	}   
    public void contactMonster(int i) {
	   if (i != 999) {
		   if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
			   gp.playSE(6);
			   
				  int damage = gp.monster[gp.currentMap][i].attack - defense;
				  if(damage < 1) {
					  damage = 1;
				  }
				  if(!gp.keyH.imortalModeOn){
			   		life -= damage;
			   		invincible = true;
			  		transparent = true;
					
				  }
		   }
	   }
   }   
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower ) {
	   if(i != 999) {
		  if(!gp.monster[gp.currentMap][i].invincible) {
			  gp.playSE(5);
			  
			  if(knockBackPower > 0 ) {
			  setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
			  }
			  if(gp.monster[gp.currentMap][i].offBalance) {
				  attack = getAttack() * 5;
				  //System.out.println(attack);
			  }
			  
			  int damage = attack - gp.monster[gp.currentMap][i].defense;
			  if(damage < 1) {
				  damage = 1;
			  }
			  
			  if(attacker.type == type_magic) {
				  damage = projectile.attack;			 
			  }
			  
			  gp.monster[gp.currentMap][i].life -= damage;
			  
			  gp.ui.addMessage(damage + " damage!");
			  gp.monster[gp.currentMap][i].invincible = true;
			  gp.monster[gp.currentMap][i].damageReaction();
			  if(gp.monster[gp.currentMap][i].life <= 0) {
				  gp.monster[gp.currentMap][i].dying = true;
				  gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
				  gp.ui.addMessage("exp " + gp.monster[gp.currentMap][i].exp + "!");
				  exp += gp.monster[gp.currentMap][i].exp;
				  checkLevelUp();
				  
			  }
		  }
		  
	   }
   }  
    public void damageProjectile(int i) {
	   if (i != 999){
		   
		   
		   Entity projectile1 = gp.projectile[gp.currentMap][i];
		   projectile1.alive = false;
		   generateParticle(projectile1, projectile1);
	   }
   }
    public void checkLevelUp() {
	   if(exp >= nextLevelExp) {
		   level++;
		   nextLevelExp = nextLevelExp + 20 * level;
		   if(level % 5 == 0){
		   	maxLife += 2;
		   }
		   strength++;
		   dexterity++;
		   attack = getAttack();
		   defense = getDefense();
		   gp.playSE(8);
		   gp.gameState = gp.dialogueState;
//		   dialogues[0][0] = "You are level " + level + " now!\n" 
//				   + "You feel stronger!"; 
		   setDialogue();
		   startDialogue(this,0);
	   }
   }
    public void selectItem() {
    	int itemIndex = gp.ui.getItemDescription(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
    	if(itemIndex < inventory.size()) {
    		Entity selectedItem = inventory.get(itemIndex); 
    		if(selectedItem.type == type_sword || selectedItem.type == type_axe || selectedItem.type == type_pickaxe ) {
    			currentWeapon = selectedItem;
    			attack = getAttack();
    			getAttackImage();
    		}
    		if(selectedItem.type == type_shield) {
    			currentShield = selectedItem;
    			defense = getDefense();
    		}
    		if(selectedItem.type == type_consumable) {
    			if(selectedItem.use(this)){
    				if(selectedItem.amount > 1) {
    					selectedItem.amount--;
    				}
    				else {
    					inventory.remove(itemIndex);
    				}
    			}
    		}
    		if(selectedItem.type == type_magic) {
    			currentMagic = selectedItem;
    			
    			if(currentMagic.name.equals("Fireball")) {
    				projectile = new ObjFireBall(gp);
    				//attack = projectile.attack;
    			}
    			
    			
    			if(currentMagic.name.equals("Blue Fireball")) {
    				projectile = new ObjFireBallBlue(gp);
    				//attack = projectile.attack;
    			}
    			
    			if(currentMagic.name.equals("White Fireball")) {
    				projectile = new ObjFireBallWhite(gp);
    				//attack = projectile.attack;
    			}
    			
    			hasFireBall = true;
    		}
    		if(selectedItem.type == type_light) {
    			if(currentLight == selectedItem) {
    				currentLight = null;
    			} else {
    				currentLight = selectedItem;
    			}
    			lightUpdated = true;
    		}
    		
    		
    	}
    }   
    public int searchItemInventory(String itemName) {
    	int itemIndex = 999; 
    	for(int i = 0; i < inventory.size(); i++) {
    		if(inventory.get(i).name.equals(itemName)) {
    			itemIndex = i;
    			break;
    		}
    	}
    	return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
    	boolean canObtain = false;
    	
    	Entity newItem = gp.eGenerator.getObject(item.name);
    	if(newItem.stackable) {
    		int index = searchItemInventory(newItem.name);
    				if(index != 999) {
    					inventory.get(index).amount++;
    					canObtain = true;
    				}
    				else { // new Item do not currently have
    					if(inventory.size() != maxInventorySize) {
    						inventory.add(newItem);
    						canObtain = true;
    					}
    				}
    	}
    	else {// Not stackable
    		if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
    	}
    	return canObtain;
    }
    public void draw(Graphics2D g2){

        
        // g2.setColor(Color.white);
        // g2.fillRect(x,y,gp.tileSize, gp.tileSize);
    	BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
             
        switch(direction){
            case "up":
            	if(!attacking) {
            	if(spriteNum == 1) {image = up1;}
            	if(spriteNum == 2) {image = up2;}
            	}
            	if(attacking) {
            		tempScreenY = screenY-gp.tileSize;
                	if(spriteNum == 1) {image = attackUp1;}
                	if(spriteNum == 2) {image = attackUp2;}
                	}
            	if(guarding) {
            		image = guardUp;
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
            	if(guarding) {
            		image = guardDown;
            	}
                break;
            case "left": 
            	if(!attacking) {
            	if(spriteNum == 1) {image = left1;}
            	if(spriteNum == 2) {image = left2;}
            	}
            	if(attacking) {
            		tempScreenX = screenX-gp.tileSize;
                	if(spriteNum == 1) {image = attackLeft1;}
                	if(spriteNum == 2) {image = attackLeft2;}
                	}
            	if(guarding) {
            		image = guardLeft;
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
            	if(guarding) {
            		image = guardRight;
            	}
                break;
        }
        
        if(transparent) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        if(drawing) {
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
        //Reset ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
      
        
    }
}
