package main;

import entity.PlayerDummy;
import java.awt.Graphics2D;
import monster.MonSkeletonBoss;
import object.ObjDoorIron;

public class CutSceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	
	// Scene Number
	public final int NA = 0;
	public final int skeletonLord = 1;
	
	public CutSceneManager(GamePanel gp) {
		this.gp = gp;
	}
	
	//Methods
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case skeletonLord: sceneSkeletonLord(); break; 
		
		}
	}
	public void sceneSkeletonLord() {
		if(scenePhase == 0 ) {
			gp.bossBattleOn = true;
			
			// shut door behind him
			
			for (int i = 0; i < gp.obj[1].length; i++ ) {
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new ObjDoorIron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(21);
					break;
				}
			}
			//search for vacate slot of NPC
			for(int i = 0; i< gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			scenePhase++;
			
		}
		if(scenePhase == 1 ) {
			gp.player.worldY -= 2;
			
			if(gp.player.worldY < gp.tileSize * 16) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2 ) {
			for (int i = 0; i < gp.monster[1].length; i++ ) {
				if(gp.monster[gp.currentMap][i] != null && 
						gp.monster[gp.currentMap][i].name == MonSkeletonBoss.monName) {
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		
		
		if(scenePhase == 3 ) {
			
			gp.ui.drawDialogueScreen();
			
			
		}
		if(scenePhase == 4 ) {
			
			// return to player 
			
			for(int i = 0; i< gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName )) {
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					// delete the dummy 
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			//Start drawing the player
			gp.player.drawing = true;
			
			// Reset sceneNum and scene Phase 
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			//Change Music
			gp.stopMusic();
			gp.playMusic(22);
		}
		
		gp.gameState = gp.dialogueState;
	}
}
