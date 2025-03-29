package States;

import entity.Bobcat;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import main.GamePanel;

public class PlayState {
    public static boolean debug, axePlus, music = true, enterPressed, spacePressed, shotKeyPressed, bPressed;
	// direction test
	public static int dPressed;
    //Debug
    public static boolean showDebugText = false;
    public static boolean imortalModeOn = false; 
	public static ArrayList<String> directionLast = new ArrayList<>(4);
    private static boolean minMapOn = false;
    public static void pKeyPressed(int code, GamePanel gp) {
			if (code == KeyEvent.VK_UP){
              if(!directionLast.contains("up")){
				directionLast.add("up");
			  }
            }
            if (code == KeyEvent.VK_LEFT ){
                if(!directionLast.contains("left")){
					directionLast.add("left");
				  }
                
            }
            if (code == KeyEvent.VK_DOWN ){
                if(!directionLast.contains("down")){
					directionLast.add("down");
				  }
            }
            if (code == KeyEvent.VK_RIGHT){
                if(!directionLast.contains("right")){
					directionLast.add("right");
				  }
            }
	        if(code == KeyEvent.VK_P) {
				
	        	PauseState.pKeyPressed(code, gp);
	        }
	        if(code == KeyEvent.VK_C) {
	        	CharacterState.cKeyPressed(code, gp); 
	        }
	        if(code == KeyEvent.VK_M) {
	        	if(music == true) {
	        		music = false;
	        		gp.stopMusic();
	        	}
	        	else if(music == false) {
	        		music = true;
	        		gp.playMusic(0);
	        	} 
	        }
                enterPressed = code == KeyEvent.VK_ENTER; 
	        
	        if(code == KeyEvent.VK_SPACE) {
	        	spacePressed = true;
	        }
	        
	        if(code == KeyEvent.VK_F) {
	        	shotKeyPressed = true;
	        	
	        }	        
	        if(code == KeyEvent.VK_ESCAPE) {
	        	OptionState.optionsKeyPressed(code, gp);
	        }
	        if(code == KeyEvent.VK_M) {
	        	MapState.mapState(code, gp);
	        	
	        }
	        
	        if(code == KeyEvent.VK_X) {
				if(!minMapOn){
                	gp.setMiniMapOn(gp.getMiniMapOn() == false);
					minMapOn = true;
				}
	        }
	        if(code == KeyEvent.VK_B) {
	        	bPressed = true;
	        }
	        
	        if(code == KeyEvent.VK_Z) {
	        	if(Bobcat.follow) {
	        	Bobcat.follow = false;
	        	} else if(!Bobcat.follow) {
	        		Bobcat.follow = true;
	        	}
	        }
	        
	         //DEBUG and imortal mode
	        if(code == KeyEvent.VK_T) {
	        	if(showDebugText == false) {
	        		showDebugText = true;
	        		imortalModeOn = true;
	        	}
	        	else if (showDebugText == true) {
	        		showDebugText = false;
	        		imortalModeOn = false;
	        	}
	        }        
        }

    public static void pKeyReleased(int code, GamePanel gp) {
        if (code == KeyEvent.VK_UP){
            directionLast.remove("up");
        }
        if (code == KeyEvent.VK_LEFT){
            directionLast.remove("left");
        }
        if (code == KeyEvent.VK_DOWN){
            directionLast.remove("down");
        }
        if (code == KeyEvent.VK_RIGHT){
            directionLast.remove("right");
        }
        if(code == KeyEvent.VK_F) {
        	shotKeyPressed = false;	
        	
        }
        if(code == KeyEvent.VK_SPACE) {
        	spacePressed = false;
        }
        if(code == KeyEvent.VK_B) {
        	bPressed = false;
        }
		if(code == KeyEvent.VK_P) {
			PauseState.pKeyReleased(code, gp);
        }
		if(code == KeyEvent.VK_C) {
			CharacterState.cKeyReleased(code, gp);
		}

		if(code == KeyEvent.VK_X) {
			minMapOn = false;
		}
    }
}
