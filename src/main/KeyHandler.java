package main;

import entity.Bobcat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import entity.Bobcat;

public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, debug, axePlus, music = true, enterPressed, 
            spacePressed, shotKeyPressed, bPressed;
    // direction test
    public int dPressed;
    //Debug 
    boolean showDebugText = false;
    public boolean imortalModeOn = false; 
    public int[] lastDirection = new int[4];



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
            titleState(code);
        }
        
        //playState
        else  if(gp.gameState == gp.playState) {
            playState(code);
        }
        
        //Pause State 
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
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
        
        
    }   
    public void titleState(int code) {
        if(gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }   
                }
                 if (code == KeyEvent.VK_UP){
                    
                     gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }

                 }
                 
                 if(code == KeyEvent.VK_ENTER) {
                     if(gp.ui.commandNum == 0) {
                         gp.gameState = gp.playState;
                         gp.playMusic(0);
                         //to add characters screen uncomment the next line. and comment out the one above
                        // gp.ui.titleScreenState = 1;  
                         //System.out.println(gp.ui.titleScreenState);
                    }
                     if(gp.ui.commandNum == 1) {
                         
                         gp.saveLoad.load();
                         gp.gameState = gp.playState;
                         gp.playMusic(0);
                        
                    }
                     if(gp.ui.commandNum == 2) {
                         
                        System.exit(0);
                    }
                 }
            }
     
        else if(gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_DOWN){
                
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;

                
                }
            }
             if (code == KeyEvent.VK_UP){
                
                 gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }

                  
              }
             if(code == KeyEvent.VK_ENTER) {
                 if(gp.ui.commandNum == 0) {
                    System.out.println("Add code here  fighter");
                    gp.gameState = gp.playState;
                    //fighter 
                    if(music) {
                    gp.playMusic(0);
                    }
                }
                 if(gp.ui.commandNum == 1) {
                     System.out.println("Add code here theif");
                     gp.gameState = gp.playState;
                    //Theif
                     if(music) {
                    gp.playMusic(0);
                     }
                }
                 if(gp.ui.commandNum == 2) {
                     
                     System.out.println("Add code here sorcerer");
                     gp.gameState = gp.playState;
                    //sorcerer
                     if(music) {
                    gp.playMusic(0);
                     }
                    }
                 if(gp.ui.commandNum == 3) {                         
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                 
              }
            
        
        
        }
     
     }
    public void pauseState(int code) {
         if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
         }
     }
    
    public boolean getImortalmodeON(){
            return imortalModeOn;
    }


    
    public void playState(int code) {
            

            /// key release about 563

            if (code == KeyEvent.VK_UP && upPressed == false){
                upPressed = true;
                for (int i = 0; i < 4; i++){ // adds preference to held keys and inserts new key as 1st preference
                    if (lastDirection[i] != 0){
                        lastDirection[i] += 1;
                    }
                }
                lastDirection[0] = 1;
            }
            if (code == KeyEvent.VK_LEFT && leftPressed == false){
                leftPressed = true;
                for (int i = 0; i < 4; i++){
                    if (lastDirection[i] != 0){
                        lastDirection[i] += 1;
                    }
                }
                lastDirection[2] = 1;
            }
            if (code == KeyEvent.VK_DOWN && downPressed == false){
                downPressed = true;
                for (int i = 0; i < 4; i++){
                    if (lastDirection[i] != 0){
                        lastDirection[i] += 1;
                    }
                }
                lastDirection[1] = 1;
            }
            if (code == KeyEvent.VK_RIGHT && rightPressed == false){
                rightPressed = true;
                for (int i = 0; i < 4; i++){
                    if (lastDirection[i] != 0){
                        lastDirection[i] += 1;
                    }
                }
                lastDirection[3] = 1;
            }


            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_C) {
                gp.gameState = gp.characterState; 
            }
            if(code == KeyEvent.VK_M) {
                if(music == true) {
                    music = false;
                    gp.stopMusic();
                }
                else if(music == false) {
                    music = true;
                    gp.playMusic(0);
                } 
            }
                enterPressed = code == KeyEvent.VK_ENTER; // in place of if else statment 
                /*
                 * if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            `````} else {enterPressed = false; }
                 */
            
            if(code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            
            if(code == KeyEvent.VK_F) {
                shotKeyPressed = true;
                
            }
            
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.commandNum = 0;
                
                gp.gameState = gp.optionsState;
                
            }
            
            if(code == KeyEvent.VK_M) {
                gp.gameState = gp.mapState;
                
            }
            
            if(code == KeyEvent.VK_X) {
                    gp.map.miniMapOn = gp.map.miniMapOn == false;
            }
            if(code == KeyEvent.VK_B) {
                bPressed = true;
            }
            
            if(code == KeyEvent.VK_Z) {
                if(Bobcat.follow) {
                Bobcat.follow = false;
                } else if(!Bobcat.follow) {
                    Bobcat.follow = true;
                }
            }
            
             //DEBUG and imortal mode
            if(code == KeyEvent.VK_T) {
                if(showDebugText == false) {
                    showDebugText = true;
                    imortalModeOn = true;
                }
                else if (showDebugText == true) {
                    showDebugText = false;
                    imortalModeOn = false;
                }
            }
            
//          
     }
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
          
        if (code == KeyEvent.VK_UP){
            upPressed = false;
            for (int i = 0; i < 4; i++){ // sets key released to 0 and subtracts numbers above to make room for preference
                if (lastDirection[i] > lastDirection[0]){
                    lastDirection[i] -= 1;
                }
            }
            lastDirection[0] = 0;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
            for (int i = 0; i < 4; i++){
                if (lastDirection[i] > lastDirection[2]){
                    lastDirection[i] -= 1;
                }
            }
            lastDirection[2] = 0;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
            for (int i = 0; i < 4; i++){
                if (lastDirection[i] > lastDirection[1]){
                    lastDirection[i] -= 1;
                }
            }
            lastDirection[1] = 0;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
            for (int i = 0; i < 4; i++){
                if (lastDirection[i] > lastDirection[3]){
                    lastDirection[i] -= 1;
                }
            }
            lastDirection[3] = 0;
        }


        


        if(code == KeyEvent.VK_F) {
            shotKeyPressed = false; 
            
        }
        if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if(code == KeyEvent.VK_B) {
            bPressed = false;
        }
        
//        if(code == KeyEvent.VK_Z) {
//          Bobcat.follow = true;
//        }
        
        
    }
}
