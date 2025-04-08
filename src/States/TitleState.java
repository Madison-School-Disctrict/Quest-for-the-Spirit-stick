package States;

import java.awt.event.KeyEvent;
import main.GamePanel;
import main.GamePanel.InputFocus;


public class TitleState {
    private static boolean isShiftDown = false;
    public static void keyTypedTitle(KeyEvent e, GamePanel gp) {
        isShiftDown = e.isShiftDown();    
        if (gp.gameState == gp.titleState) {
            char c = e.getKeyChar();
            

            if ( (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || isSymbol(c)) && c != KeyEvent.VK_ENTER && c != KeyEvent.VK_TAB) {
                switch (gp.inputFocus) {
                    case USERNAME -> gp.usernameInput += c;
                    case PASSWORD -> gp.passwordInput += c;
                    case CONFIRM_PASSWORD -> gp.confirmPasswordInput += c;
                }
            } else if (c == '\b') {
                switch (gp.inputFocus) {
                    case USERNAME -> {
                        if (!gp.usernameInput.isEmpty()) {
                            gp.usernameInput = gp.usernameInput.substring(0, gp.usernameInput.length() - 1);
                        }
                    }
                    case PASSWORD -> {
                        if (!gp.passwordInput.isEmpty()) {
                            gp.passwordInput = gp.passwordInput.substring(0, gp.passwordInput.length() - 1);
                        }
                    }
                    case CONFIRM_PASSWORD -> {
                        if (!gp.confirmPasswordInput.isEmpty()) {
                            gp.confirmPasswordInput = gp.confirmPasswordInput.substring(0, gp.confirmPasswordInput.length() - 1);
                        }
                    }
                }
            
            }
        }
    }


    private static boolean isSymbol(char c) {
        return "!@#$%^&*()_+-=[]{}|;':,.<>/?".indexOf(c) != -1;
    }



    
    public static void titleState(int code, GamePanel gp) {
        
        switch (gp.ui.titleScreenState) {
            case 0 -> {
                //System.out.println("TitleState: " + gp.ui.titleScreenState);
                if (code == KeyEvent.VK_TAB) {
                    
                    // Cycle input focus with Tab
                    tabSCroll(gp, isShiftDown);
                }
            }
            case 1 -> {
               
                if (code == KeyEvent.VK_TAB) {                  
                    // Cycle input focus with Enter
                    tabSCroll(gp, isShiftDown);
                }

            }
            case 2 -> {
                if (code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }if (code == KeyEvent.VK_UP){
                    
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }

                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.currentMap = 0;
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                       
                    }
                    if(gp.ui.commandNum == 1) {
                        System.out.println(gp.usernameInput);
                        gp.getSaveLoad().load(gp.usernameInput);
                        gp.gameState = gp.playState;
                        gp.ui.commandNum = 0;
                        gp.ui.titleScreenState = 1;
                        gp.playMusic(0);
                        
                    }
                    if(gp.ui.commandNum == 2) {
                        
                        System.exit(0);
                    }
                }
            }
            case 3 -> {
                if (code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }	
                }
                if (code == KeyEvent.VK_UP){
                    
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                    
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.currentMap = 0;
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        
                    }
                    if(gp.ui.commandNum == 2) {
                        
                        System.exit(0);
                    }
                }
            }
            default -> {
            }
        }

        

    
    }


    private static void tabSCroll(GamePanel gp, boolean isShiftDown) {
        //Debug
        // System.out.println("Key pressed: " + code);
        // System.out.println("Key pressed: " + KeyEvent.getKeyText(code));
        // System.out.println("Tab pressed");
        // System.out.println("TitleState: " + gp.ui.titleScreenState);
        // System.out.println("InputFocus: " + gp.inputFocus);
        // System.out.println("Username: " + gp.usernameInput);
        // System.out.println("Password: " + gp.passwordInput);
        // System.out.println("ConfirmPassword: " + gp.confirmPasswordInput);
        // System.out.println("LoginMessage: " + gp.loginMessage);
        
        if (isShiftDown) {
            // Shift + Tab = backwards
            switch (gp.inputFocus) {
                case USERNAME -> gp.inputFocus = gp.ui.titleScreenState == 1
                        ? InputFocus.CONFIRM_PASSWORD : InputFocus.PASSWORD;
                case PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
                case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.PASSWORD;
            }
        } else {
            switch (gp.inputFocus) {
                case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
                case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
                      ? InputFocus.CONFIRM_PASSWORD : InputFocus.USERNAME;
                case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
            }
       //return;
        }

    }  
    
    
}
