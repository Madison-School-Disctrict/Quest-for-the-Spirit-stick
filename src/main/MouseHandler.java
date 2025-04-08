package main;
import data.UserManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseHandler implements MouseListener {
    //JLabel label;
    GamePanel gp;
    UserManager uManager = new UserManager(this.gp);

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    public void mouseClicked(MouseEvent e) {
       // label.setText("Mouse clicked!");
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
    





       // gp.addMouseListener(this);
    if (gp.gameState == gp.titleState) {
        int x = e.getX();
        int y = e.getY();
        
        int scaledX = (int)((double) x / gp.getWidth() * gp.screenWidth);
        int scaledY = (int)((double) y / gp.getHeight() * gp.screenHeight);

        gp.mouseX = scaledX;
        gp.mouseY = scaledY;

        // Handle clicks
        //checkButtonClick(scaledX, scaledY);
        
        if (gp.ui.titleScreenState == 0) {
            if (scaledX >= gp.ui.button1x  && scaledX <= gp.ui.button1x + gp.tileSize*4 && scaledY >= gp.ui.button1y && scaledY <= gp.ui.button1y + gp.tileSize) {
                gp.handleLogin();
            }
            if (scaledX >= gp.ui.button2x && scaledX <= gp.ui.button2x + gp.tileSize*4 && scaledY >= gp.ui.button2y && scaledY <= gp.ui.button2y + gp.tileSize) {
                gp.usernameInput = "";
                gp.passwordInput = "";
                gp.confirmPasswordInput = "";
                gp.loginMessage = "";
                gp.ui.titleScreenState = 1;
            }
        } else if (gp.ui.titleScreenState == 1) {
            
    
            
            if (scaledX >= gp.ui.button1x && scaledX <= gp.ui.button1x + gp.tileSize*4 && scaledY >= gp.ui.button1y && scaledY <= gp.ui.button1y + gp.tileSize) {
                gp.handleCreateAccount();  
            }
            if (scaledX >= gp.ui.button2x && scaledX <= gp.ui.button2x + gp.tileSize*4 && scaledY >= gp.ui.button2y && scaledY <= gp.ui.button2y + gp.tileSize) {
                gp.usernameInput = "";
                gp.passwordInput = "";
                gp.confirmPasswordInput = "";
                gp.loginMessage = "";
                gp.ui.titleScreenState = 0;
            }
            
        }
    }

     


}



    public void mouseReleased(MouseEvent e) {
       // label.setText("Mouse released!");
    }

    public void mouseEntered(MouseEvent e) {
       // label.setText("Mouse entered!");
    }

    public void mouseExited(MouseEvent e) {
        //label.setText("Mouse exited!");
    }
    // public void mouseDragged(MouseEvent e) {
    //     label.setText("Mouse dragged!");
    // }
    // public void mouseMoved(MouseEvent e) {
    //     label.setText("Mouse moved!");
}
