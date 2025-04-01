package state;

import java.awt.event.KeyEvent;
import main.GamePanel;
import main.NpcInventory;
import main.PlayerInventory;

public class TradeState { 
    public static boolean enterPressed = false;
    public static void tradeState(int code, GamePanel gp) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        
        if (gp.ui.getsubState() == 0) {
            if(code == KeyEvent.VK_UP) {
                enterPressed = false;
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0 ) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if(code == KeyEvent.VK_DOWN) {
                enterPressed = false;
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2 ) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        
        if(gp.ui.getsubState() == 1) {
            NpcInventory.npcInventory(code, gp);
            if(code == KeyEvent.VK_ESCAPE) {
                enterPressed = false;
                gp.ui.setsubState(0); 
            }
        }
        if(gp.ui.getsubState() == 2) {
           PlayerInventory.playerInventory(code, gp);
            if(code == KeyEvent.VK_ESCAPE) {
                enterPressed = false;
                gp.ui.setsubState(0);
            }
        }
       
    }

}
