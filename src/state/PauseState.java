package state;

import java.awt.event.KeyEvent;
import main.GamePanel;

public class PauseState {
    public static boolean puased = false;

    public static void pKeyPressed(int code, GamePanel gp) {
        if (code == KeyEvent.VK_P && puased == false) {
            if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                puased = true;
            } else if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
                puased = true;
            }
        }
    }

    public static void pKeyReleased(int code, GamePanel gp) {
        if (code == KeyEvent.VK_P) {
            puased = false;
        }
    }
}
