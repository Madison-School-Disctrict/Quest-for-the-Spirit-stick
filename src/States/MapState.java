package States;
import java.awt.event.KeyEvent;
import main.GamePanel;
public class MapState {
    private static boolean mapOn = false;
    public static void mapState(int code, GamePanel gp) {
        if(code == KeyEvent.VK_M && !mapOn) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.mapState;
                mapOn = true; 
                gp.keyH.music=false;              
            } else if( gp.gameState == gp.mapState){
                gp.gameState = gp.playState;
                mapOn = true;
                gp.keyH.music = true;
            }
        }
    }
    public static void mapStateRelease(int code, GamePanel pg){
        mapOn = false;
    }
}
