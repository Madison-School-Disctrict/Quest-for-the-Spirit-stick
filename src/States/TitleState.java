package States;

import java.awt.event.KeyEvent;
import main.GamePanel;
import main.GamePanel.InputFocus;


public class TitleState {
    public static boolean isShiftDown = false;
    public static void keyTypedTitle(KeyEvent e, GamePanel gp) {
            
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
        if(code == KeyEvent.VK_SHIFT) {
            TitleState.isShiftDown = true;
        }


        switch (gp.ui.titleScreenState) {

            case 0 -> {
                //System.out.println("TitleState: " + gp.ui.titleScreenState);
                if (code == KeyEvent.VK_TAB || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
                    // Cycle input focus with Tab or Up/Down Arrow keys
                    tabSCroll(gp, isShiftDown, code);    
                }
            }
            case 1 -> {
               
                if (code == KeyEvent.VK_TAB || code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN|| code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {                  
                    // Cycle input focus with Enter
                    tabSCroll(gp, isShiftDown, code);
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

        
        if (code == KeyEvent.VK_ENTER) {
            
            switch (gp.inputFocus) {
                case LOGIN_BUTTON -> gp.handleLogin();
                case CREATE_BUTTON ->{ 
                    if(gp.ui.titleScreenState == 1){ 
                        gp.handleCreateAccount();
                    }else { 
                        gp.inputFocus = InputFocus.USERNAME;
                        gp.usernameInput = "";
                        gp.passwordInput = "";
                        gp.confirmPasswordInput = "";
                        gp.loginMessage = "";
                        gp.ui.titleScreenState = 1;}
                }
                case DELETE_BUTTON -> { 
                    System.out.println("Delete Button Pressed");
                    gp.handleDeleteAccount();
                    gp.usernameInput = "";
                    gp.passwordInput = "";
                    gp.confirmPasswordInput = "";
                    gp.loginMessage = "";
                    gp.inputFocus = InputFocus.USERNAME;
                    gp.ui.titleScreenState = 0;
                }
                case BACK_BUTTON -> { gp.ui.titleScreenState = 0;
                    gp.inputFocus = InputFocus.USERNAME;
                    gp.usernameInput = "";
                    gp.passwordInput = "";
                    gp.loginMessage = "";
                    }
                case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
                case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
                      ? InputFocus.CONFIRM_PASSWORD : InputFocus.USERNAME;
                case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
            }
        }

    
    }


    private static void tabSCroll(GamePanel gp, boolean isShiftDown, int code) {
        
        if (isShiftDown || code == KeyEvent.VK_UP || code == KeyEvent.VK_LEFT) {
            // Shift + Tab = backwards
                switch (gp.inputFocus) {
                    case USERNAME -> gp.inputFocus = gp.ui.titleScreenState == 1
                            ? InputFocus.BACK_BUTTON : InputFocus.CREATE_BUTTON;
                    case PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
                    case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.PASSWORD;
                    case LOGIN_BUTTON -> gp.inputFocus = InputFocus.PASSWORD;
                    case CREATE_BUTTON -> gp.inputFocus =  gp.ui.titleScreenState == 1
                    ? InputFocus.CONFIRM_PASSWORD : InputFocus.LOGIN_BUTTON;
                    case BACK_BUTTON -> gp.inputFocus = InputFocus.CREATE_BUTTON;
                    case DELETE_BUTTON -> gp.inputFocus = InputFocus.BACK_BUTTON;
                    default -> gp.inputFocus = InputFocus.USERNAME;
                }
        } else {
            // Tab = forwards
            switch (gp.inputFocus) {
                case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
                case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
                    ? InputFocus.CONFIRM_PASSWORD : InputFocus.LOGIN_BUTTON;
                case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.CREATE_BUTTON;
                case LOGIN_BUTTON -> gp.inputFocus = InputFocus.CREATE_BUTTON;
                case CREATE_BUTTON -> gp.inputFocus =  gp.ui.titleScreenState == 1
                    ? InputFocus.BACK_BUTTON : InputFocus.DELETE_BUTTON;
                case BACK_BUTTON -> gp.inputFocus = InputFocus.DELETE_BUTTON;   
                case DELETE_BUTTON -> gp.inputFocus = InputFocus.USERNAME;
                default -> gp.inputFocus = InputFocus.USERNAME;  
            }
       //return;
        }
        

    }  
    
    
}
