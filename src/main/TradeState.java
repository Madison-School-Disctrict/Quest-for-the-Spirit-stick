package main;
import java.awt.event.KeyEvent;
public class TradeState {
    public static boolean enterPressed;
    public static void tradeState(int code, GamePanel gp) {
    	
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        
        if (gp.ui.subState == 0) {
            if(code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0 ) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if(code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2 ) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        
        if(gp.ui.subState == 1) {
            NpcInventory.npcInventory(code, gp);
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if(gp.ui.subState == 2) {
           PlayerInventory.playerInventory(code, gp);
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
       
    }

}
