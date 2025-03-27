package main;
import java.awt.event.KeyEvent;;

public class OptionState {
    public static boolean enterPressed = false;
    public static void optionsState(int code,GamePanel gp) {
        if(code == KeyEvent.VK_ESCAPE) {
            gp.ui.commandNum = 0;
            gp.ui.subState = 0;
            gp.gameState = gp.playState;
            enterPressed = false;
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
            enterPressed = false;
             gp.ui.commandNum++;
             gp.playSE(9);
             if(gp.ui.commandNum > maxCommandNum) {
                 gp.ui.commandNum = 0;

             
             }
         }
          if (code == KeyEvent.VK_UP){
            enterPressed = false;
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
}
