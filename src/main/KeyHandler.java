package main;

import States.CharacterState;
import States.DialogueState;
import States.GameOverState;
import States.MapState;
import States.OptionState;
import States.PauseState;
import States.PlayState;
import States.TitleState;
import States.TradeState;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean debug, axePlus, music = true, enterPressed, spacePressed, shotKeyPressed, bPressed;
	// direction test
	public int dPressed;
    //Debug
    public boolean showDebugText = false;
    public boolean imortalModeOn = false; 
	public ArrayList<String> directionLast = new ArrayList<>(4);
    public KeyHandler(GamePanel gp) {
    	this.gp= gp;
    }
    @Override
    public void keyTyped(KeyEvent e){    
    }  
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        //Title State
        if(gp.gameState == gp.titleState) {
			TitleState.titleState(code, gp);
        }
        //playState
        else  if(gp.gameState == gp.playState) {
			PlayState.pKeyPressed(code, gp);
			setkeys();			        
        }
        //Pause State 
        else if(gp.gameState == gp.pauseState) { 
			PauseState.pKeyPressed(code, gp);
        }
		// Dialogue State
        else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
        	DialogueState.dkeyPressed(code, gp);
			enterPressed = DialogueState.enterPressed;
        }
        //Character State 
        else if (gp.gameState == gp.characterState) {
        	CharacterState.cKeyPressed(code, gp);
        } //Option State 
        else if (gp.gameState == gp.optionsState) {
        	OptionState.optionsState(code, gp);
			enterPressed = OptionState.enterPressed;
        }  //Game Over State 
        else if (gp.gameState == gp.gameOverState) {
        	GameOverState.gameOverState(code, gp);
        } // Trade State
        	else if (gp.gameState == gp.tradeState) {
        	TradeState.tradeState(code, gp);
			enterPressed = TradeState.enterPressed;
        } // map State
        	else if (gp.gameState == gp.mapState) {
        	MapState.mapState(code, gp);
        }       
    }  // end of key pressed    

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
		
		PlayState.pKeyReleased(code, gp);
		setkeys();	 
		if(code == KeyEvent.VK_M){
			MapState.mapStateRelease(code, gp);
		}   
		if(code == KeyEvent.VK_ESCAPE) {
			OptionState.optionsKeyReleased(code, gp);
		}
		if(code == KeyEvent.VK_C) {
			CharacterState.cKeyReleased(code, gp);	
		}
    }
	public void setkeys() {
		if(gp.gameState == gp.playState) {	
			spacePressed = PlayState.spacePressed;
			enterPressed = PlayState.enterPressed;
			shotKeyPressed = PlayState.shotKeyPressed;
			bPressed = PlayState.bPressed;
			imortalModeOn = PlayState.imortalModeOn;
			showDebugText = PlayState.showDebugText;
			music = PlayState.music;
			debug = PlayState.debug;
			axePlus = PlayState.axePlus;
			dPressed = PlayState.dPressed;
			directionLast = PlayState.directionLast;
		}
	}
}
