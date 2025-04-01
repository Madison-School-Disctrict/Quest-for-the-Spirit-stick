package state;

import java.awt.event.KeyEvent;
import main.GamePanel;
import main.PlayerInventory;

public class CharacterState {
    private static boolean inventoryOn = false;
    public static void cKeyPressed(int code, GamePanel gp){
        if (code == KeyEvent.VK_C && !inventoryOn){
            if (gp.gameState == gp.characterState){ 
                gp.gameState = gp.playState;
                inventoryOn = true;    
            }
            else if (gp.gameState == gp.playState){
                gp.gameState = gp.characterState;
                inventoryOn = true;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        
        PlayerInventory.playerInventory(code, gp);
    }

    public static void cKeyReleased(int code, GamePanel gp){
        if (code == KeyEvent.VK_C) {
            inventoryOn = false;
        }
    }
}
