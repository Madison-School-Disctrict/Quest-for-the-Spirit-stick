package main;
import java.awt.event.KeyEvent;
public class GameOverState {
    public static void gameOverState(int code, GamePanel gp) {
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
}
