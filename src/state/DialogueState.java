package state;
import java.awt.event.KeyEvent;

import main.GamePanel;;
public class DialogueState {
    public static boolean enterPressed;
    public static void dkeyPressed(int code, GamePanel gp){
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

}
