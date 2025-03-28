package States;
import java.awt.event.KeyEvent;
import main.GamePanel;
import main.Sound;



public class OptionState {
    public static boolean enterPressed = false;
    static Sound music = new Sound(); 
    
    static Sound sound = new Sound();
    
    public static void optionsState(int code,GamePanel gp) {
        music = gp.getMusic();

        sound = gp.getSound();

        if(code == KeyEvent.VK_ESCAPE) {
            gp.ui.commandNum = 0;
            gp.ui.setsubState(0);
            gp.gameState = gp.playState;
            enterPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        
        int maxCommandNum = 0;
        switch(gp.ui.getsubState()) {
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
              if(gp.ui.getsubState() == 0) {
                  if(gp.ui.commandNum == 1  && music.getVolumeScale() > 0){
                      music.setVolumeScale(music.getVolumeScale() - 1);
                      music.checkVolume();
                      gp.playSE(9);
                  }
                 if(gp.ui.commandNum == 2  && sound.getVolumeScale() > 0){
                     sound.setVolumeScale(sound.getVolumeScale() - 1);
                     gp.playSE(9);
                     
                 }
              }
          }
         if (code == KeyEvent.VK_RIGHT) {
             if(gp.ui.getsubState() == 0) {
                 if(gp.ui.commandNum == 1  && music.getVolumeScale() < 5){
                    music.setVolumeScale(music.getVolumeScale() + 1);
                     music.checkVolume();
                     gp.playSE(9);
                 }
                 if(gp.ui.commandNum == 2  && sound.getVolumeScale() < 5){
                    sound.setVolumeScale(sound.getVolumeScale() + 1);
                    gp.playSE(9);
                }
             }
         }
    }
}
