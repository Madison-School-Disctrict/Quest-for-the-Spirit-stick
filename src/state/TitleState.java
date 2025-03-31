package state;

import java.awt.event.KeyEvent;

import main.GamePanel;


public class TitleState {
    
    public static void titleState(int code, GamePanel gp) {
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
                       
                    }
                    if(gp.ui.commandNum == 1) {
                        
                        gp.getSaveLoad().load();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                         
                     }
                    if(gp.ui.commandNum == 2) {
                        
                          System.exit(0);
                      }
                }
           }
    
    }
    
}
