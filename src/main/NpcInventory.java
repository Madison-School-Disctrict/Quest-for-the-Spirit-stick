package main;
import java.awt.event.KeyEvent;
import state.TradeState;
public class NpcInventory {
    public static void npcInventory(int code, GamePanel gp) {
        if (code == KeyEvent.VK_UP){
            TradeState.enterPressed = false;
            if(gp.ui.npcSlotRow > 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);	
            }else {
                gp.ui.npcSlotRow = 3;
            }
        }
        if (code == KeyEvent.VK_LEFT){
            TradeState.enterPressed = false;
            if(gp.ui.npcSlotCol > 0) {  
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }else {
                gp.ui.npcSlotCol  = 4;
            }
        }
        if (code == KeyEvent.VK_DOWN){
            TradeState.enterPressed = false;
            if(gp.ui.npcSlotRow < 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            } else{
                gp.ui.npcSlotRow = 0;
            } 
        }
        if (code == KeyEvent.VK_RIGHT){
            TradeState.enterPressed = false;
            if(gp.ui.npcSlotCol < 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            } else {
                gp.ui.npcSlotCol = 0;
            }
        }
    }
}
