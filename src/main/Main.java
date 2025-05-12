/* Created by 2021-22 and 2022-23 CS 112 courses.
 * 
 *  
 */


package main;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static JFrame window;
    public static void main(String[] args){
       // Player canvas = new Player(); 
    	//window = new JFrame();
        window = new JFrame("Madison HighSchool Annual CS Project 2021-2 -- The end of time");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        new Main().setIcon();
        //window.setTitle("Madison HighSchool Annual CS Project");
        
       
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
    	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("player/MadisonM.png"));
    	window.setIconImage(icon.getImage());
    	
    }
    
}
