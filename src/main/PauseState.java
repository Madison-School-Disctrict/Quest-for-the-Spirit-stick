package main;
import java.awt.event.KeyEvent;

public class PauseState{
    public static void play(int code, GamePanel gp) {
        if(code == KeyEvent.VK_P ) {
            if(gp.gameState == gp.pauseState){ 
                gp.gameState = gp.playState;
    
            }
            else if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
                
            }
        }
    }
}
