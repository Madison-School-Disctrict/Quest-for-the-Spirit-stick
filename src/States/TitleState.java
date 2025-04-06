package States;

import java.awt.event.KeyEvent;
import main.GamePanel;


public class TitleState {
    public static void keyTypedTitle(KeyEvent e, GamePanel gp) {
        if(gp.ui.titleScreenState == 1) {

            char c = e.getKeyChar();

            if (Character.isLetterOrDigit(c)) {
                if (gp.usernameInput.length() < 12) {
                    gp.usernameInput += c;
                }
            } else if (c == '\b' && gp.usernameInput.length() > 0) {
                gp.usernameInput = gp.usernameInput.substring(0, gp.usernameInput.length() - 1);
            } else if (c == '\n') { // Enter key
                gp.validateAndStart(gp.usernameInput);
            }
        }
    }
    
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
                        
                        gp.getSaveLoad().load("rikeylee");
                        //gp.gameState = gp.playState;
                        gp.ui.commandNum = 0;
                        gp.ui.titleScreenState = 1;
                        gp.playMusic(0);
                         
                     }
                    if(gp.ui.commandNum == 2) {
                        
                          System.exit(0);
                      }
                }
            } //else if(gp.ui.titleScreenState == 1) {

        //     char c = e.getKeyChar();

        //     if (Character.isLetterOrDigit(c)) {
        //         if (gp.usernameInput.length() < 12) {
        //             gp.usernameInput += c;
        //         }
        //     } else if (c == '\b' && gp.usernameInput.length() > 0) {
        //         gp.usernameInput = gp.usernameInput.substring(0, gp.usernameInput.length() - 1);
        //     } else if (c == '\n') { // Enter key
        //         gp.validateAndStart(gp.usernameInput);
        //     }

                // if (code == KeyEvent.VK_DOWN){
                //     gp.ui.commandNum++;
                //     if(gp.ui.commandNum > 3) {
                //         gp.ui.commandNum = 0;
                //     }	
                // }
                // if (code == KeyEvent.VK_UP){
                   
                //     gp.ui.commandNum--;
                //     if(gp.ui.commandNum < 0) {
                //         gp.ui.commandNum = 3;
                //     }

                // }
                
                // if(code == KeyEvent.VK_ENTER) {
                   
                //     gp.ui.titleScreenState = 0;
                //     gp.ui.commandNum = 0;


                // }
           // }

           

    
    }

    
    
}
