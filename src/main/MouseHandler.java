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
        //JFrame frame = new JFrame("Mouse Handler Example");
       // label = new JLabel("Click me!");
       // label.addMouseListener(this);
        
        // frame.add(label);
        // frame.setSize(300, 200);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);
    }
    
    public void mouseClicked(MouseEvent e) {
       // label.setText("Mouse clicked!");
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
    
        gp.addMouseListener(this);
    if (gp.gameState == gp.titleState) {
        int x = e.getX();
        int y = e.getY();
        if (gp.ui.titleScreenState == 0) {
            if (x >= gp.ui.button1x && x <= gp.ui.button1x + 150 && y >= gp.ui.button1y && y <= gp.ui.button1y + 40) {
                System.out.println("Mouse clicked in button 1");
                gp.handleLogin();
            }
            if (x >= gp.ui.button2x && x <= gp.ui.button2x + 150 && y >= gp.ui.button2y && y <= gp.ui.button2y + 40) {
                System.out.println("Mouse clicked in button 2");
                gp.ui.titleScreenState = 1;
                gp.usernameInput = "";
                gp.passwordInput = "";
                gp.confirmPasswordInput = "";
                gp.loginMessage = "";
            }
        } else if (gp.ui.titleScreenState == 1) {
            // System.out.println("Mouse clicked in title screen state 1" + x + " " + y);
            // System.out.println(gp.ui.button1x + " " + gp.ui.button1y + " " + gp.ui.button2x + " " + gp.ui.button2y);
    
            
            if (x >= gp.ui.button1x && x <= gp.ui.button1x + 150 && y >= gp.ui.button1y && y <= gp.ui.button1y +40) {
                gp.handleCreateAccount();
                
            }
            if (x >= gp.ui.button2x && x <= gp.ui.button2x + 150 && y >= gp.ui.button2y && y <= gp.ui.button2y +40) {
                System.out.println("Mouse clicked in button 2");
               // gp.ui.titleScreenState = 0;
                
                gp.usernameInput = "";
                gp.passwordInput = "";
                gp.confirmPasswordInput = "";
                gp.loginMessage = "";

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
