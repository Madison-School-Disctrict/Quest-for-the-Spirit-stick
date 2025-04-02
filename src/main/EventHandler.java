package main;

import data.Progress;
import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventMaster = new Entity(gp);
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue() {

        eventMaster.dialogues[0][0] = " You have teleported";
        eventMaster.dialogues[1][0] = " You Fall into a pit";
        eventMaster.dialogues[1][1] = "It takes you several hours to climb out \n You exerted half of your life \n you may want to go";
        eventMaster.dialogues[1][2] = "refresh yourself by drinking the water in the pool";
        eventMaster.dialogues[1][3] = "You may want to stay away from this \narea in the future. ";
        eventMaster.dialogues[2][0] = "You drink the Water.\nYour life has been Restored.";
        eventMaster.dialogues[2][1] = "This water is very refreshing";
        eventMaster.dialogues[3][1] = "You have Been Telelported to a Different Locqtion";

    }

    public void checkEvent() {//event Happens
        //Check the player is more than one tile away from last event. 
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if (hit(0, 26, 16, "left") == true && !gp.keyH.imortalModeOn) {
                damagePit(hit(0, 26, 16, "left"), 0, 26, 16, gp.dialogueState);
            }
            for (int i = 21; i < 26; i++) {
                if (hit(0, i, 12, "up") == true) {
                    healingPool(gp.dialogueState);
                }
            }

            teleport(hit(0, 20, 7, "any"), 0, 34, 8, gp.outside);// move to different part of the map
            teleport(hit(0, 10, 39, "up"), 1, 12, 12, gp.indoor); // to merchant shack
            teleport(hit(1, 12, 13, "any"), 0, 10, 40, gp.outside);  // move back to outside
            if (hit(1, 12, 9, "up")) {
                speak(gp.npc[1][0]);
            }// talk with merchant
            //FRom World map to dungeon
            teleport(hit(0, 12, 9, "any"), 2, 9, 41, gp.dungeon);  // to dungeon 
            //From rigby map to dungeon
            //teleport(hit(0,16,39,"any"),2,9,41,gp.dungeon);  // to dungeon 
            teleport(hit(0, 25, 16, "right"), 6, 29, 14, gp.outside);  // to pit transfer
            teleport(hit(6, 24, 11, "any"), 0, 27, 16, gp.outside); // pit transfer
            teleport(hit(2, 9, 41, "any"), 0, 12, 9, gp.outside);  // to outside
            teleport(hit(2, 8, 7, "any"), 3, 26, 41, gp.dungeon);  // to the dungoen floor 2
            teleport(hit(3, 26, 41, "any"), 2, 8, 7, gp.dungeon); //  back to dungoen floor 1
            if (hit(3, 25, 27, "any")) {
                Boss();
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void teleport(boolean on, int map, int col, int row, int area) {

        if (on) {
            gp.gameState = gp.transitionState;
            gp.nextArea = area;
            tempMap = map;
            tempCol = col;
            tempRow = row;
            canTouchEvent = false;
            if (area == gp.dungeon) {
                gp.playSE(13);
            }
        }
    }

    public void damagePit(boolean on, int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        //gp.player.attackCanceled = true;
        //gp.playSE(2);
        eventMaster.startDialogue(eventMaster, 1);
        gp.player.life -= gp.player.life / 2;
        //teleport(on,map ,col , row,gp.outside);
        canTouchEvent = false;

    }

    public void healingPool(int gameState) {
        if (gp.keyH.spacePressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 2);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();

            /*
			 * I removed the save game when resting at the pool but if you want to add something maybe add an option menu here. 
			 * I added the save progress to the options menu. 
			 * 
			 * 
             */
            // if you want to save progress every time you drink from the pool then uncomment the code below. 
            gp.saveLoad.save();
        }

    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
            gp.keyH.enterPressed = false;
        }
    }

    public void Boss() {

        if (!gp.bossBattleOn && !Progress.skeletonBossDefeated) {
            gp.gameState = gp.cutSceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }

    }
}
