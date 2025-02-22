package main;

import data.Progress;
import entity.Bobcat;
import entity.NPCBigRock;
import entity.NPCMerchant;
import entity.NPCOldMAn;
import monster.MonBat;
import monster.MonGreenSlime;
import monster.MonOrc;
import monster.MonPinkSlime;
import monster.MonRedSlime;
import monster.MonSkeletonBoss;
import object.ObjAxe;
import object.ObjChest;
import object.ObjCoinsBronze;
import object.ObjDoor;
import object.ObjDoorIron;
import object.ObjHeart;
import object.ObjKey;
import object.ObjLantern;
import object.ObjManaCrystal;
import object.ObjPickaxe;
import object.ObjPotionRed;
import object.ObjShieldBlue;
import object.ObjSpiritStick;
import object.ObjTent;
import tileInteractive.ITDestructibleWall;
import tileInteractive.ITDrayTree;
import tileInteractive.ITMetalPlate;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void setObject() {
	
		int mapNum = 0;
		int i = 0;

		//mapNum ++;
		gp.obj[mapNum][i] = new ObjCoinsBronze(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		
		gp.obj[mapNum][i] = new ObjCoinsBronze(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		
		gp.obj[mapNum][i] = new ObjLantern(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		
		gp.obj[mapNum][i] = new ObjTent(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		i++;
		gp.obj[mapNum][i] = new ObjDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new ObjDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
		gp.obj[mapNum][i].worldY = gp.tileSize * 12;
		i++;
		gp.obj[mapNum][i] = new ObjHeart(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 36;
		gp.obj[mapNum][i].worldY = gp.tileSize * 43;
		i++;
		gp.obj[mapNum][i] = new ObjPotionRed(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjKey(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		gp.obj[mapNum][i] = new ObjHeart(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 27;
		i++;
		gp.obj[mapNum][i] = new ObjManaCrystal(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		
		i++;
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjKey(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 13;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		
		i++;
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjAxe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 15;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		
		i++;
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjShieldBlue(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 17;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		
		mapNum = 2;
		i=0; 
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjPickaxe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 40;
		gp.obj[mapNum][i].worldY = gp.tileSize * 41;
		i++;
		
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjPotionRed(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 13;
		gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		i++;
		
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjPotionRed(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		gp.obj[mapNum][i].worldY = gp.tileSize * 34;
		i++;
		
		gp.obj[mapNum][i] = new ObjChest(gp);
		gp.obj[mapNum][i].setLoot(new ObjPotionRed(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		
		gp.obj[mapNum][i] = new ObjDoorIron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 18;
		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
		i++;
		
		mapNum = 3;
		i = 0;
		
		gp.obj[mapNum][i] = new ObjDoorIron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		
		gp.obj[mapNum][i] = new ObjSpiritStick(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		gp.obj[mapNum][i].worldY = gp.tileSize * 9;
		i++;
		
		
	}
	public void setNPC() {
		
		// map 0
		int mapNum = 0;
		int i = 0 ;

		//mapNum++;
		gp.npc[mapNum][i] = new NPCOldMAn(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*21;
		gp.npc[mapNum][i].worldY = gp.tileSize*21; i++;
		
		gp.npc[mapNum][i] = new Bobcat(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*36;
		gp.npc[mapNum][i].worldY = gp.tileSize*36;  i++;
		
		
		// map 1
		mapNum++;
		i = 0;
		gp.npc[mapNum][i] = new NPCMerchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*12;
		gp.npc[mapNum][i].worldY = gp.tileSize*7;
		
		mapNum =2;
		i = 0;
		gp.npc[mapNum][i] = new NPCBigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*20;
		gp.npc[mapNum][i].worldY = gp.tileSize*25;  i++;
		
		gp.npc[mapNum][i] = new NPCBigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*11;
		gp.npc[mapNum][i].worldY = gp.tileSize*18;  i++;
		
		gp.npc[mapNum][i] = new NPCBigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*23;
		gp.npc[mapNum][i].worldY = gp.tileSize*14;  i++;
		
		
		
		
		
		
	}
	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		
		//mapNum ++;
		gp.monster[mapNum][i] = new MonOrc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;
		
		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;	

		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*11;	
		
		i++;
		
		gp.monster[mapNum][i] = new MonGreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*40;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;	
		
		i++;
		if(gp.player.level < 3) {
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*12;
			gp.monster[mapNum][i].worldY = gp.tileSize*31;
	
//			i++;
//			
//			gp.monster[mapNum][i] = new MonPinkSlime(gp);
//			gp.monster[mapNum][i].worldX = gp.tileSize*10;
//			gp.monster[mapNum][i].worldY = gp.tileSize*41;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;
	
			i++;
//			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*39;
			gp.monster[mapNum][i].worldY = gp.tileSize*11;
	
			i++;
			
			gp.monster[mapNum][i] = new MonGreenSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*37;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;
	
//			i++;
//			gp.monster[mapNum][i] = new MonPinkSlime(gp);
//			gp.monster[mapNum][i].worldX = gp.tileSize*13;
//			gp.monster[mapNum][i].worldY = gp.tileSize*40;
//	
			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
			
		} 
		else if (gp.player.level < 6) {
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*10;
			gp.monster[mapNum][i].worldY = gp.tileSize*41;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*24;
			gp.monster[mapNum][i].worldY = gp.tileSize*41;

			i++;
			
			gp.monster[mapNum][i] = new MonRedSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
		} else {
//			gp.monster[mapNum][i] = new MonPinkSlime(gp);
//			gp.monster[mapNum][i].worldX = gp.tileSize*10;
//			gp.monster[mapNum][i].worldY = gp.tileSize*11;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*37;
			gp.monster[mapNum][i].worldY = gp.tileSize*41;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*39;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*38;
			gp.monster[mapNum][i].worldY = gp.tileSize*10;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*21;
			gp.monster[mapNum][i].worldY = gp.tileSize*38;

			i++;
			
			gp.monster[mapNum][i] = new MonPinkSlime(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*40;
			gp.monster[mapNum][i].worldY = gp.tileSize*12;
		}
		
		mapNum = 2; 
		i = 0;
		gp.monster[mapNum][i] = new MonBat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;
		i++;
		gp.monster[mapNum][i] = new MonBat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*36;
		gp.monster[mapNum][i].worldY = gp.tileSize*25;
		i++;
		gp.monster[mapNum][i] = new MonBat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[mapNum][i] = new MonBat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[mapNum][i] = new MonBat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*10;
		gp.monster[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		if(!Progress.skeletonBossDefeated) {
		
		mapNum = 3; 
		i = 0;
		gp.monster[mapNum][i] = new MonSkeletonBoss(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*16;
		
		}
		
	}
	
	public void setInteractiveTiles() {
		
		int mapNum = 0;
		
		//mapNum++;
		for(int i = 0; i < 45; i++) {
			if(i < 9) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i + 25), 12);
			}
			else if(i < 14) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i + 12), 23);
			}
			else if(i < 18) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i - 4), 41);
			}
			else if(i < 24) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, (i - 5), 40);
			}
			else if(i < 28) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, 19, (41 - (i-24)));
			}
			else if(i < 34) {
				gp.iTile[mapNum][i] = new ITDrayTree(gp, 20, (38 + (i-30)));
			}
			// else if (i < 40){
			// 	gp.iTile[mapNum][i] = new ITDrayTree(gp, 21, i+2);  
			// }
			// else if (i < 45){
			// 	gp.iTile[mapNum][i+5] = new ITDrayTree(gp, 22, i+2);
			// } 
			// else {
			// 	gp.iTile[mapNum][i+10] = new ITDrayTree(gp, 23, i-3);
			// }
		
			
		}
		// for(int i = 35; i < 39; i++){
		// //  
		// gp.iTile[mapNum][i] = new ITDrayTree(gp, 21, i+1);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   gp.iTile[mapNum][i] = new ITDrayTree(gp, 10, 41);i++;
		// gp.iTile[mapNum][i+5] = new ITDrayTree(gp, 22, i+2);
		// gp.iTile[mapNum][i+10] = new ITDrayTree(gp, 23, i+2);
		// }
//		
		mapNum = 2;
		int i = 0;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 18, 30 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 17, 31 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 17, 32 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 17, 34 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 18, 34 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 18, 33 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 10, 22 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 10, 24 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 38, 18 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 38, 19 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 38, 20 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 38, 21 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 18, 13 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 18, 14 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 22, 28 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 30, 28 ); i++;
		gp.iTile[mapNum][i] = new ITDestructibleWall(gp, 32, 28 ); i++;
		
		gp.iTile[mapNum][i] = new ITMetalPlate(gp, 20, 22 ); i++;
		gp.iTile[mapNum][i] = new ITMetalPlate(gp, 8, 17 ); i++;
		gp.iTile[mapNum][i] = new ITMetalPlate(gp, 39, 31 ); i++;
		

//		
	}
}
