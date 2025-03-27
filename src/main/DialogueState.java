package main;
import java.awt.event.KeyEvent;;
public class DialogueState {
    public static boolean enterPressed;
    public static void dkeyPressed(int code, GamePanel gp){
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

}
