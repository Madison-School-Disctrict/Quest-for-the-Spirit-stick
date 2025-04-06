package States;

import java.awt.event.KeyEvent;
import main.GamePanel;
import main.GamePanel.InputFocus;


public class TitleState {
    public static void keyTypedTitle(KeyEvent e, GamePanel gp) {
        
        if (gp.gameState == gp.titleState) {
            char c = e.getKeyChar();
            
            if (c == KeyEvent.VK_TAB || c == '\t' || c == 9) {
                e.consume(); // prevent default behavior
                // switch focus logic...
            }

            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || isSymbol(c)) {
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
            } //else if (c == KeyEvent.VK_ENTER) {
            //     // Cycle input focus with tab
            //     switch (gp.inputFocus) {
            //         case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
            //         case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
            //                 ? InputFocus.CONFIRM_PASSWORD : InputFocus.USERNAME;
            //         case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
            //     }
            // }
        }
    }


    private static boolean isSymbol(char c) {
        return "!@#$%^&*()_+-=[]{}|;':,.<>/?".indexOf(c) != -1;
    }



    
    public static void titleState(int code, GamePanel gp) {
       
        //Debug
        // System.out.println("Key pressed: " + code);
        switch (gp.ui.titleScreenState) {
            case 0 -> {
                System.out.println("TitleState: " + gp.ui.titleScreenState);
                if (code == KeyEvent.VK_ENTER) {
                    //Debug
                    // System.out.println("Enter pressed");
                    // System.out.println("TitleState: " + gp.ui.titleScreenState);
                    
                    // Cycle input focus with Enter
                    switch (gp.inputFocus) {
                        case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
                        case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
                                ? InputFocus.CONFIRM_PASSWORD : InputFocus.USERNAME;
                        case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
                    }
                }
            }
            case 1 -> {
                System.out.println("TitleState: " + gp.ui.titleScreenState);
                if (code == KeyEvent.VK_ENTER) {
                    //Debug
                    // System.out.println("Enter pressed");
                    // System.out.println("TitleState: " + gp.ui.titleScreenState);
                    
                    // Cycle input focus with Enter
                    switch (gp.inputFocus) {
                        case USERNAME -> gp.inputFocus = InputFocus.PASSWORD;
                        case PASSWORD -> gp.inputFocus = gp.ui.titleScreenState == 1
                                ? InputFocus.CONFIRM_PASSWORD : InputFocus.USERNAME;
                        case CONFIRM_PASSWORD -> gp.inputFocus = InputFocus.USERNAME;
                    }
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

    
    
}
