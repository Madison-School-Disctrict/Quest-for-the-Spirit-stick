package state;

import entity.Bobcat;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import main.GamePanel;

public class PlayState {
    public static boolean
		debug,
		axePlus,
		music = true,
		enterPressed,
		spacePressed,
		shotKeyPressed,
		bPressed,
		// Debug variables
		showDebugText = false,
		imortalModeOn = false,
		minMapOn = false,
		tPressed = false;

    public static ArrayList<String> directionLast = new ArrayList<>(4);

    public static void pKeyPressed(int code, GamePanel gp) {
        switch (code) {
            case KeyEvent.VK_UP -> {
                if (!directionLast.contains("up")) {
                    directionLast.add("up");
                }
            }
            case KeyEvent.VK_LEFT -> {
                if (!directionLast.contains("left")) {
                    directionLast.add("left");
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (!directionLast.contains("down")) {
                    directionLast.add("down");
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (!directionLast.contains("right")) {
                    directionLast.add("right");
                }
            }
            case KeyEvent.VK_P ->
                PauseState.pKeyPressed(code, gp);
            case KeyEvent.VK_C ->
                CharacterState.cKeyPressed(code, gp);
            case KeyEvent.VK_M -> {
                if (music) {
                    music = false;
                    gp.stopMusic();
                } else {
                    music = true;
                    gp.playMusic(0);
                }
            }
            case KeyEvent.VK_ENTER ->
                enterPressed = true;
            case KeyEvent.VK_SPACE ->
                spacePressed = true;
            case KeyEvent.VK_F ->
                shotKeyPressed = true;
            case KeyEvent.VK_ESCAPE ->
                OptionState.optionsKeyPressed(code, gp);
            case KeyEvent.VK_X -> {
                if (!minMapOn) {
                    gp.setMiniMapOn(!gp.getMiniMapOn());
                    minMapOn = true;
                }
            }
            case KeyEvent.VK_B ->
                bPressed = true;
            case KeyEvent.VK_Z ->
                Bobcat.follow = !Bobcat.follow;
            case KeyEvent.VK_T -> {
                if (!tPressed) {
                    tPressed = true;
                    if (!showDebugText) {
                        showDebugText = true;
                        imortalModeOn = true;
                    } else {
                        showDebugText = false;
                        imortalModeOn = false;
                    }
                }
            }
        }
    }

    public static void pKeyReleased(int code, GamePanel gp) {
        switch (code) {
            case KeyEvent.VK_UP ->
                directionLast.remove("up");
            case KeyEvent.VK_LEFT ->
                directionLast.remove("left");
            case KeyEvent.VK_DOWN ->
                directionLast.remove("down");
            case KeyEvent.VK_RIGHT ->
                directionLast.remove("right");
            case KeyEvent.VK_F ->
                shotKeyPressed = false;
            case KeyEvent.VK_SPACE ->
                spacePressed = false;
            case KeyEvent.VK_B ->
                bPressed = false;
            case KeyEvent.VK_P ->
                PauseState.pKeyReleased(code, gp);
            case KeyEvent.VK_C ->
                CharacterState.cKeyReleased(code, gp);
            case KeyEvent.VK_X ->
                minMapOn = false;
            case KeyEvent.VK_T ->
                tPressed = false;
        }
    }
}
