package main;
import java.awt.event.KeyEvent;
public class MapState {
    private static boolean mapOn = false;
    public static void mapState(int code, GamePanel gp) {
        if(code == KeyEvent.VK_M && !mapOn) {
            if(gp.gameState == gp.playState){
                gp.gameState = gp.mapState;
                mapOn = true;
            } else if( gp.gameState == gp.mapState){
                gp.gameState = gp.playState;
                mapOn = true;
            }
        }
    }
    public static void mapStateRelease(int code, GamePanel pg){
        mapOn = false;
    }
}
