/* Created by 2021-22 and 2022-23 CS 112 courses.
 * 
 *  
 */


package main;
 
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static JFrame window;
        public static void main(String[] args){

        window = new JFrame("Madison HighSchool Annual CS Project 2021 -- The end of time");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This is a test
        window.setResizable(false);
        new Main().setIcon();
       
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        gamePanel.config.loadConfig();
        
        
        if(gamePanel.fullScreenOn) {
        	window.setUndecorated(true);
        }
        window.pack();   
/*
 * 
 * public void pack()
 * Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 
 * The resulting width and height of the window are automatically enlarged if either of dimensions 
 * is less than the minimum size as specified by the previous call to the setMinimumSize method.If 
 * the window and/or its owner are not displayable yet, both of them are made displayable before 
 * calculating the preferred size. The Window is validated after its size is being calculated. 
 */
        
        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
                
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    } 
    
    public void setIcon() {
        URL iconURL = getClass().getClassLoader().getResource("player/MadisonM.png");
        if (iconURL == null) {
            System.out.println("ERROR: Icon image not found! Make sure 'player/MadisonM.png' is in the JAR root.");
        } else {
            ImageIcon icon = new ImageIcon(iconURL);
            window.setIconImage(icon.getImage());
        }
    }

    
}
