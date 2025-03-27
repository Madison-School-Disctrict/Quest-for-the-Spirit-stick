package state;

import java.awt.event.KeyEvent;
import main.GamePanel;

public class MapState {
    public static void mapState(int code, GamePanel gp) {
        if(code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }
}
