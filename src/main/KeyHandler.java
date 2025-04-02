package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import state.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean debug, axePlus, music = true, enterPressed, spacePressed, shotKeyPressed, bPressed;
    //Debug
    public boolean showDebugText = false;
    public boolean imortalModeOn = false;
    public ArrayList<String> directionLast = new ArrayList<>(4);

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (gp.gameState) {
            case 0 -> //Title State
                TitleState.titleState(code, gp);
            case 1 -> {//Play State
                enterPressed = false;
                PlayState.pKeyPressed(code, gp);
                setkeys();
            }
            case 2 -> //Pause State
                PauseState.pKeyPressed(code, gp);
            case 3 -> {//Dialogue State
                DialogueState.dkeyPressed(code, gp);
                enterPressed = DialogueState.enterPressed;
            }
            case 4 -> //character State
                CharacterState.cKeyPressed(code, gp);
            case 5 -> {//Option State
                OptionState.optionsState(code, gp);
                enterPressed = OptionState.enterPressed;
            }
            case 6 -> //Game Over State
                GameOverState.gameOverState(code, gp);
            case 8 -> {// Trade State
                TradeState.tradeState(code, gp);
                enterPressed = TradeState.enterPressed;
            }
            case 10 -> // map State
                MapState.mapState(code, gp);
            case 11 -> { // cutscene State
                DialogueState.dkeyPressed(code, gp);
                enterPressed = DialogueState.enterPressed;
            }
            default -> {
            }
        }
    }  // end of key pressed    

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        PlayState.pKeyReleased(code, gp);
        setkeys();
        if (code == KeyEvent.VK_M) {
            MapState.mapStateRelease(code, gp);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            OptionState.optionsKeyReleased(code, gp);
        }
        if (code == KeyEvent.VK_C) {
            CharacterState.cKeyReleased(code, gp);
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
            TradeState.enterPressed = false;
            DialogueState.enterPressed = false;
            PlayState.enterPressed = false;
        }

    }

    public void setkeys() {
        if (gp.gameState == gp.playState) {
            spacePressed = PlayState.spacePressed;
            enterPressed = PlayState.enterPressed;
            shotKeyPressed = PlayState.shotKeyPressed;
            bPressed = PlayState.bPressed;
            imortalModeOn = PlayState.imortalModeOn;
            showDebugText = PlayState.showDebugText;
            music = PlayState.music;
            debug = PlayState.debug;
            axePlus = PlayState.axePlus;
            directionLast = PlayState.directionLast;
        }
    }
}
