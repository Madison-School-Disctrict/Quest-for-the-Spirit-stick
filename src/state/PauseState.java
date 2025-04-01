package state;

import java.awt.event.KeyEvent;
import main.GamePanel;

public class PauseState {

    public static boolean paused = false;

    public static void pKeyPressed(int code, GamePanel gp) {
        if (code == KeyEvent.VK_P && paused == false) {
            if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                paused = true;
            } else if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                paused = true;
            }
        }
    }

    public static void pKeyReleased(int code, GamePanel gp) {
        if (code == KeyEvent.VK_P) {
            paused = false;
        }
    }
}
