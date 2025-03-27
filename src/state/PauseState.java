package state;
import java.awt.event.KeyEvent;
import main.GamePanel;

public class PauseState {
    public static void pauseState(int code, GamePanel gp) {
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
}