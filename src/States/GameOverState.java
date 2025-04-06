package States;
import java.awt.event.KeyEvent;
import main.GamePanel;
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
                if (gp.currentMap == 3) {
                    gp.setWorldX(gp.tileSize*25);
                    gp.setWorldY(gp.tileSize*36);
                }
                gp.gameState = gp.playState;
                gp.resetGame(false);
            }else if(gp.ui.commandNum == 1){
                gp.stopMusic();
                gp.playSE(10);
                gp.ui.titleScreenState = 2;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        
        
    }
}
