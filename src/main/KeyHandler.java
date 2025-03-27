package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean music = false, enterPressed, spacePressed, shotKeyPressed, bPressed;
	

    public KeyHandler(GamePanel gp) {
    	this.gp= gp;
    }


    @Override
    public void keyTyped(KeyEvent e){
        
    }  


    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        //Title State
        if(gp.gameState == gp.titleState) {
			TitleState.titleState(code, gp);
        }
        //playState
        else  if(gp.gameState == gp.playState) {
			PlayState.pKeyPressed(code, gp);			        
        }
        //Pause State 
        else if(gp.gameState == gp.pauseState) { 
			PauseState.play(code, gp);
        }
		// Dialogue State
        else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
        	dialogueState(code);
        }
        //Character State 
        else if (gp.gameState == gp.characterState) {
        	characterState(code);
        } //Option State 
        else if (gp.gameState == gp.optionsState) {
        	optionsState(code);
        }  //Game Over State 
        else if (gp.gameState == gp.gameOverState) {
        	gameOverState(code);
        } // Trade State
        	else if (gp.gameState == gp.tradeState) {
        	tradeState(code);
        } // map State
        	else if (gp.gameState == gp.mapState) {
        	mapState(code);
        }
        
        
    }  // end of key pressed    


    public void dialogueState(int code) {
    	 if (code == KeyEvent.VK_ENTER) {
     		enterPressed = true;
     	}
     }

    public void characterState(int code) {
    	 if(code == KeyEvent.VK_C) {
     		gp.gameState = gp.playState;
     	 }
    	 
        if(code == KeyEvent.VK_ENTER) {
        	gp.player.selectItem();
        }
        
        playerInventory(code);
    	 
     }
    public void mapState(int code) {
    	 if(code == KeyEvent.VK_M) {
    		 gp.gameState = gp.playState;
    	 }
     }
    public void playerInventory(int code) {
    	 if (code == KeyEvent.VK_UP){
     		if(gp.ui.playerSlotRow > 0) {
 		        gp.ui.playerSlotRow--;
 		        gp.playSE(9);	
     		}else {
     			gp.ui.playerSlotRow = 3;
     		}
         }
         if (code == KeyEvent.VK_LEFT){
         	if(gp.ui.playerSlotCol > 0) {
 	        	gp.ui.playerSlotCol--;
 	        	gp.playSE(9);
         	}else {
         		gp.ui.playerSlotCol  = 4;
         	}
         }
         if (code == KeyEvent.VK_DOWN){
         	if(gp.ui.playerSlotRow < 3) {
 	        	gp.ui.playerSlotRow++;
 	        	gp.playSE(9);
         	} else{
     			gp.ui.playerSlotRow = 0;
     		} 
         }
         if (code == KeyEvent.VK_RIGHT){
         	if(gp.ui.playerSlotCol < 4) {
 	        	gp.ui.playerSlotCol++;
 	        	gp.playSE(9);
         	} else {
         		gp.ui.playerSlotCol = 0;
         	}
         }
     }
    public void npcInventory(int code) {
    	 if (code == KeyEvent.VK_UP){
     		if(gp.ui.npcSlotRow > 0) {
 		        gp.ui.npcSlotRow--;
 		        gp.playSE(9);	
     		}else {
     			gp.ui.npcSlotRow = 3;
     		}
         }
         if (code == KeyEvent.VK_LEFT){
         	if(gp.ui.npcSlotCol > 0) {
 	        	gp.ui.npcSlotCol--;
 	        	gp.playSE(9);
         	}else {
         		gp.ui.npcSlotCol  = 4;
         	}
         }
         if (code == KeyEvent.VK_DOWN){
         	if(gp.ui.npcSlotRow < 3) {
 	        	gp.ui.npcSlotRow++;
 	        	gp.playSE(9);
         	} else{
     			gp.ui.npcSlotRow = 0;
     		} 
         }
         if (code == KeyEvent.VK_RIGHT){
         	if(gp.ui.npcSlotCol < 4) {
 	        	gp.ui.npcSlotCol++;
 	        	gp.playSE(9);
         	} else {
         		gp.ui.npcSlotCol = 0;
         	}
         }
     }
    
    
    
    
    public void optionsState(int code) {
        	if(code == KeyEvent.VK_ESCAPE) {
        		gp.ui.commandNum = 0;
        		gp.ui.subState = 0;
        		gp.gameState = gp.playState;
        	}
        	if(code == KeyEvent.VK_ENTER) {
        		enterPressed = true;
        	}
        	
        	int maxCommandNum = 0;
        	switch(gp.ui.subState) {
        	case 0: maxCommandNum = 6;break;
        	case 3: maxCommandNum = 1; break;
        	}
        	
        	if (code == KeyEvent.VK_DOWN){
                
         		gp.ui.commandNum++;
         		gp.playSE(9);
         		if(gp.ui.commandNum > maxCommandNum) {
         			gp.ui.commandNum = 0;

         		
         		}
         	}
         	 if (code == KeyEvent.VK_UP){
         		
         		 gp.ui.commandNum--;
         		 gp.playSE(9);
          		if(gp.ui.commandNum < 0) {
          			gp.ui.commandNum = maxCommandNum;
          		}

                  
              }
         	 
         	 if (code == KeyEvent.VK_LEFT) {
         		 if(gp.ui.subState == 0) {
         			 if(gp.ui.commandNum == 1  && gp.music.volumeScale > 0){
         				 gp.music.volumeScale--;
         				 gp.music.checkVolume();
         				 gp.playSE(9);
         			 }
         			if(gp.ui.commandNum == 2  && gp.sound.volumeScale > 0){
        				 gp.sound.volumeScale--;
        				 gp.playSE(9);
        				 
        			 }
         		 }
         	 }
         	if (code == KeyEvent.VK_RIGHT) {
         		if(gp.ui.subState == 0) {
         			if(gp.ui.commandNum == 1  && gp.music.volumeScale < 5){
        				 gp.music.volumeScale++;
        				 gp.music.checkVolume();
        				 gp.playSE(9);
        			 }
         			if(gp.ui.commandNum == 2  && gp.sound.volumeScale < 5){
       				 gp.sound.volumeScale++;
       				 gp.playSE(9);
       			 }
        		 }
        	 }
        }
    
    
    
    
    public void gameOverState(int code) {
    	 if(code == KeyEvent.VK_UP) {
    		 gp.ui.commandNum--;
    		 if(gp.ui.commandNum < 0 ) {
    			 gp.ui.commandNum = 1;
    		 }
    		 gp.playSE(9);
    	 }
    	 if(code == KeyEvent.VK_DOWN) {
    		 gp.ui.commandNum++;
    		 if(gp.ui.commandNum > 1 ) {
    			 gp.ui.commandNum = 0;
    		 }
    		 gp.playSE(9);
    	 }
    	 if(code == KeyEvent.VK_ENTER) {
    		 if(gp.ui.commandNum == 0) {
    			 gp.gameState = gp.playState;
    			 gp.resetGame(false);
    		 }else if(gp.ui.commandNum == 1){
    			 gp.gameState = gp.titleState;
    			 gp.resetGame(true);
    		 }
    	 }
    	 
    	 
     }
    public void tradeState(int code) {
    	
    	 if (code == KeyEvent.VK_ENTER) {
    		 enterPressed = true;
    	 }
    	 
    	 if (gp.ui.subState == 0) {
	    	 if(code == KeyEvent.VK_UP) {
	    		 gp.ui.commandNum--;
	    		 if(gp.ui.commandNum < 0 ) {
	    			 gp.ui.commandNum = 2;
	    		 }
	    		 gp.playSE(9);
	    	 }
	    	 if(code == KeyEvent.VK_DOWN) {
	    		 gp.ui.commandNum++;
	    		 if(gp.ui.commandNum > 2 ) {
	    			 gp.ui.commandNum = 0;
	    		 }
	    		 gp.playSE(9);
	    	 }
    	 }
    	 
    	 if(gp.ui.subState == 1) {
    		 npcInventory(code);
    		 if(code == KeyEvent.VK_ESCAPE) {
    			 gp.ui.subState = 0;
    		 }
    	 }
    	 if(gp.ui.subState == 2) {
    		 playerInventory(code);
    		 if(code == KeyEvent.VK_ESCAPE) {
    			 gp.ui.subState = 0;
    		 }
    	 }
    	
     }

	 ///key pressed 182
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
		PlayState.pKeyReleased(code, gp);        
    }
}
