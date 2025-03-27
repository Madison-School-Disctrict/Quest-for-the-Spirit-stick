package main;
import java.awt.event.KeyEvent;

public class PlayerInventory {

    public static void playerInventory(int code, GamePanel gp) {
        if (code == KeyEvent.VK_UP){
            if(gp.ui.playerSlotRow > 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);	
            }else {
                gp.ui.playerSlotRow = 3;
            }
        }
        if (code == KeyEvent.VK_LEFT){
            if(gp.ui.playerSlotCol > 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }else {
                gp.ui.playerSlotCol  = 4;
            }
        }
        if (code == KeyEvent.VK_DOWN){
            if(gp.ui.playerSlotRow < 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            } else{
                gp.ui.playerSlotRow = 0;
            } 
        }
        if (code == KeyEvent.VK_RIGHT){
            if(gp.ui.playerSlotCol < 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            } else {
                gp.ui.playerSlotCol = 0;
            }
        }
    }
}
