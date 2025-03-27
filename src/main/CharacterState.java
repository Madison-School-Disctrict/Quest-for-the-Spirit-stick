package main;
import java.awt.event.KeyEvent;
public class CharacterState {
    public static void cKeyPressed(int code, GamePanel gp){
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
         }
        
       if(code == KeyEvent.VK_ENTER) {
           gp.player.selectItem();
       }
       
       PlayerInventory.playerInventory(code, gp);
    }

}
