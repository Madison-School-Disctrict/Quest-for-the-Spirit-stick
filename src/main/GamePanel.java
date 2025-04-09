package main;

import Environment.EnvironmentManager;
import ai.PathFinder;
import data.SaveLoad;
import data.UserManager;
import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import tile.Map;
import tile.TileManager;
import tileInteractive.InteractiveTile;
import data.Progress;

//import entity.Player;
public class GamePanel extends JPanel implements Runnable{
  
	/* eclipse told me I needed this. Serial Number
	 * 
	 * 
	 */
private static final long serialVersionUID = 1L;
//screen settings
  final int originalTileSize = 16; // 16x16 tile
  final int scale = 3;
  public final int tileSize = originalTileSize * scale; // 48x48 tile
  public final int maxScreenCol = 20;
  public final int maxScreenRow = 12; 
  public final int screenWidth = tileSize * maxScreenCol;//960 pixels
  public final int screenHeight = tileSize * maxScreenRow;//576 pixels
  //Debug mouse settings
  public int mouseX, mouseY;
  public boolean mouseDebugEnabled = false; // Optional toggle
  
  //World Settings
  public int maxWorldCol;  
  public int maxWorldRow;
  public final int maxMap = 10;  // the number of maps we can create
  public int currentMap = 0;  // the current map we are on. 
  public int mapNum = 0;
  
  //for Full Screen
  int screenWidth2 = screenWidth;
  int screenHeight2 = screenHeight;
  public int screenWidth3 = getWidth();
  public int screenHeight3 = getHeight();
  BufferedImage tempScreen;
  Graphics2D g2;
  public boolean fullScreenOn = false;
  
  
  
  
  
  //FPS
  //FrameRate()
  
  int FPS = 60;
  
  //System
  public TileManager tileM = new TileManager(this);
  public KeyHandler keyH = new KeyHandler(this);
  public MouseHandler mouseH = new MouseHandler(this);
  UserManager userManager = new UserManager(this);
  Sound sound = new Sound();
  Sound music = new Sound();
  public CollisionCheck cCheck = new CollisionCheck(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public UI ui = new UI(this);
  public EventHandler eHandler = new EventHandler(this);
  Config config = new Config(this);
  public PathFinder pFinder = new PathFinder(this);
  EnvironmentManager eManager = new EnvironmentManager(this);
  Map map = new Map(this);
  SaveLoad saveLoad = new SaveLoad(this);
  public EntityGenerator eGenerator = new EntityGenerator(this);
  public CutSceneManager csManager = new CutSceneManager(this);
  Thread gameThread;
  
  //Entity and Object
  public Player player = new Player(this, keyH) ;//Creates player from player class
  public Entity obj[][] = new Entity[maxMap][20];
  public Entity npc[][] = new Entity[maxMap][10];
  public Entity monster[][] = new Entity[maxMap][20];
  public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
  public Entity[][] projectile = new Entity[maxMap][20];
  //public ArrayList<Entity> projectileList = new ArrayList<>();
  public ArrayList<Entity> particleList = new ArrayList<>();
  ArrayList<Entity> entityList = new ArrayList<>();
  
  
  //game State 
  public int gameState;
  public final int titleState = 0;
  public final int playState = 1;
  public final int pauseState =2;
  public final int dialogueState = 3;
  public final int characterState = 4;
  public final int optionsState = 5;
  public final int gameOverState = 6;
  public final int transitionState = 7;
  public final int tradeState = 8;
  public final int sleepState = 9;
  public final int mapState = 10;
  public final int cutsceneState = 11;
  
  //Other 
  public boolean bossBattleOn = false;
  long lastAutoSaveTime = System.currentTimeMillis();
  final long AUTO_SAVE_INTERVAL = 2 * 60 * 1000; // 2 minutes in milliseconds

  //Area
  public int currentArea ;
  public final int outside = 50;
  public final int indoor = 51;
  public final int dungeon = 52;
  public int nextArea;
  public String usernameInput = "";
  public String passwordInput = "";
  public String confirmPasswordInput = "";
  public String loginMessage = "";
  public boolean passwordFocused = false;
  public InputFocus inputFocus = InputFocus.USERNAME;

  //constructor
  public GamePanel(){
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground(Color.black);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);
      this.addMouseListener(mouseH);
      this.setFocusable(true);
      this.requestFocusInWindow(); 
      this.setFocusTraversalKeysEnabled(false);
  }
  
  //Methods
  public SaveLoad getSaveLoad() {
      return saveLoad;
  }
  
  public Sound getMusic(){
        return music;
  }
  public Sound getSound(){
    return sound;
  }
  public boolean getMiniMapOn() {
    return map.getMiniMapOn();
  }
  public void setMiniMapOn(boolean miniMapOn) {
    map.setMiniMapOn(miniMapOn);
  }

public enum InputFocus {
    USERNAME, PASSWORD, CONFIRM_PASSWORD, LOGIN_BUTTON, 
    CREATE_BUTTON, BACK_BUTTON, DELETE_BUTTON,
    FORGOT_PASSWORD_BUTTON, LOGOUT_BUTTON
}

public void setInputFocus(InputFocus focus) {
    this.inputFocus = focus;
    switch (inputFocus) {
        case USERNAME -> inputFocus = InputFocus.PASSWORD;
        case PASSWORD -> inputFocus = InputFocus.CONFIRM_PASSWORD;
        case CONFIRM_PASSWORD -> inputFocus = InputFocus.CREATE_BUTTON;
        case CREATE_BUTTON -> inputFocus = InputFocus.BACK_BUTTON;
        case BACK_BUTTON -> inputFocus = InputFocus.USERNAME; // cycle back
        default -> inputFocus = InputFocus.USERNAME; // default to username
}
}

   
   public void setWorldX(int x) {
		player.worldX = x;
	}
	public void setWorldY(int y) {
		player.worldY = y;
	}


  public void setUpGame() {
	  aSetter.setObject();
	  aSetter.setNPC();
	  aSetter.setMonster();
	  aSetter.setInteractiveTiles();
	  
	  eManager.setup();
	 
	  gameState = titleState; 
	  currentArea = outside;
	  
	  //For different Screen resize
	  tempScreen = new BufferedImage(screenWidth,screenHeight, BufferedImage.TYPE_INT_ARGB);
	  g2 = (Graphics2D)tempScreen.getGraphics();
	 if(fullScreenOn) {
	  setFullScreen();
	 }
	  
  }
  public void resetGame(boolean restart) {

	  stopMusic();
	  currentArea = outside;
	  removeTempEntity();
	  bossBattleOn = false;
	  //player.setDefualtPositions();
	  player.restoreStatus();
	  player.resetCounters();
	  aSetter.setNPC();
	  aSetter.setMonster();
	  
	  if(restart) {
	  player.setDefaultValues();
	  aSetter.setObject();
	  aSetter.setInteractiveTiles();
	  eManager.lighting.resetDay();
	  }
  }
  public void setFullScreen() {
	  // get the local screen device
	  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	  GraphicsDevice gd = ge.getDefaultScreenDevice();
	  gd.setFullScreenWindow(Main.window);
	  
	  // Get Full Screen Width and Height
	  screenWidth2 = Main.window.getWidth();
	  screenHeight2 = Main.window.getHeight();
	  
  }
  public void startGameThread(){
      gameThread = new Thread(this);
      gameThread.start();
  }  
  public void run(){
      double drawInterval = 1000000000/FPS;
      double delta = 0;
      long lastTime = System.nanoTime();
      long currentTime;
      
      while(gameThread != null){
          currentTime = System.nanoTime();
          delta += (currentTime - lastTime) / drawInterval;
           
          lastTime = currentTime;
          if (delta >= 1 ){
           
          update();
          //repaint();
          drawToTempScreen(); //draws to temp screen
          drawToScreen(); // draws to Screen
          delta--;
          }
          
      }
      
      
  } 
  


  
  



  public void update(){

        //Auto Save
        if (System.currentTimeMillis() - lastAutoSaveTime >= AUTO_SAVE_INTERVAL && gameState == playState && (!EventHandler.boss1Dead || Progress.skeletonBossDefeated)) {
            saveLoad.save(usernameInput);
            lastAutoSaveTime = System.currentTimeMillis();
        }

        
	  if(gameState == playState) {
      player.update(); // updates the player
       for(int i = 0; i < npc[1].length; i++) { // NPC update
    	   if(npc[currentMap][i] != null) {
    		   npc[currentMap][i].update();
    	   }
       }
       
       
       for(int i = 0; i < monster[1].length; i++) { // Monster update
    	   if(monster[currentMap][i] != null) {
    		   if(monster[currentMap][i].alive && !monster[currentMap][i].dying) {
    		   monster[currentMap][i].update();
    		   }
    		   if(!monster[currentMap][i].alive) {
    			   monster[currentMap][i].checkDrop();
        		   monster[currentMap][i] = null;
        		   }
    	   }
       }

       
       for(int i = 0; i < projectile[1].length; i++) { // projecttile update
    	   if(projectile[currentMap][i] != null) {
    		   if(projectile[currentMap][i].alive) {
    			   projectile[currentMap][i].update();
    		   }
    		   if(!projectile[currentMap][i].alive) {
    			   projectile[currentMap][i] = null;
        		   }
    	   }
       }
       
       for(int i = 0; i < particleList.size(); i++) { // Particle update
    	   if(particleList.get(i) != null) {
    		   if(particleList.get(i).alive) {
    			   particleList.get(i).update();
    		   }
    		   if(!particleList.get(i).alive) {
    			   particleList.remove(i);
        		   }
    	   }
    	   
       }
       eManager.update();
       for(int i = 0; i < iTile[1].length; i++) {
    	   if(iTile[currentMap][i] != null) {
    		   iTile[currentMap][i].update();
    	   }
       }
       
	  }
	  if(gameState == pauseState) {
		  //nothing  Maybe add something latter
		 
	  }
      
	 
	
  } 
  public void drawToTempScreen() {
	  
    //DEbug
      long drawStart = 0;
      if(keyH.showDebugText == true) {    	  
    	  drawStart = System.nanoTime();
      }
      
      
      //Title Screen
      if(gameState == titleState) {
    	  ui.draw(g2);
      }
      
      else if(gameState == mapState){
    	  map.drawFullMapScreen(g2);
      }
      else {
      //tile
      tileM.draw(g2);
      
      //interactive Tiles
      for(int i = 0; i < iTile[1].length;i++) {
    	  if(iTile[currentMap][i] != null) {
    		  iTile[currentMap][i].draw(g2);
    	  }
      }
      //player
    
      // Add entities to List 
      //***********************
      
      entityList.add(player);
      for(int i = 0; i < npc[1].length; i++ ) {
    	  if(npc[currentMap][i] != null) {
    		  entityList.add(npc[currentMap][i]);
    	  }
      }
      
      for( int i = 0; i < obj[1].length; i++ ) {
    	  if(obj[currentMap][i] != null) {
    		  entityList.add(obj[currentMap][i]);
    	  }
      }
      
      for( int i = 0; i < monster[1].length; i++ ) {
    	  if(monster[currentMap][i] != null) {
    		  entityList.add(monster[currentMap][i]);
    	  }
      }
      
      // /*   ************************************************************************************ 
      
      
      for( int i = 0; i < projectile[1].length; i++ ) {
    	  if(projectile[currentMap][i] != null) {
    		  entityList.add(projectile[currentMap][i]);
    	  }
      }
      
      
       // ******************************************************************************************
       //     */
      
      
      for( int i = 0; i < particleList.size(); i++ ) {
    	  if(particleList.get(i) != null) {
    		  entityList.add(particleList.get(i));
    	  }
      }
      
      //***********************
      
      //SORT 
      Collections.sort(entityList, new Comparator<Entity>() {
      
      @Override
      public int compare(Entity e1, Entity e2) {
    	  int result = Integer.compare(e1.worldY, e2.worldY);
    	  return result;
      }
      });
      
      //Draw Enttities 
      for( int i = 0; i < entityList.size() ; i++ ) {
    	  entityList.get(i).draw(g2);
      }
      // Empty List      
      entityList.clear();
      
      //Environment When we are in the dungeon we need to use the following.
      eManager.draw(g2);
      
      //Mini Map
      map.drawMiniMap(g2);
      
      //cutScene
      csManager.draw(g2);
      
      //UI
     ui.draw(g2);
     
      }
      
     //Debug
        if(keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 150;
            int lineHeight = 20 ;
            g2.drawString("WorldX: " + player.worldX, x, y);  y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);  y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;
            g2.drawString("Draw Time: " + passed, x,y);
            y += lineHeight;
            g2.drawString("Imortal Mode:", x, y);
            y += lineHeight;
            g2.drawString("Life: " + player.life,x,y);
        }
     }


  public void drawToScreen() {
	  Graphics g = getGraphics();
	  g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
	  g.dispose();
  }
  public void playMusic(int i) {
	  music.setFiles(i);
	  music.play();
	  music.loop();
  }
  public void stopMusic() {
	  music.stop();
  } 
  public void playSE(int i) {
	  
	  sound.setFiles(i);
	  sound.play();
  }
  public void changeArea() {
	  
	  if(nextArea != currentArea) {
		  
		  stopMusic();
		  
		  if(nextArea == outside) {
			  playMusic(0);
		  }
		  if(nextArea == indoor) {
			  playMusic(18);
		  }
		  if(nextArea == dungeon) {
			  playMusic(19);
		  }
		  
		  aSetter.setNPC();
		  
	  }
	  
	  currentArea = nextArea;
	  aSetter.setMonster();
	  
  }
  public void removeTempEntity() {
	  for(int mapNum1 = 0; mapNum1 < maxMap; mapNum1++) {
		  for(int i = 0; i < obj[1].length; i++) {
			  if(obj[mapNum1][i] != null && obj[mapNum1][i].temp) {
				  obj[mapNum1][i] =null;
			  }
		  }
	  }
  }


public void handleLogin() {   

    if (!userManager.userExists(usernameInput)) {
        loginMessage = "User not found. Click 'Create Account'.";
    } else if (!userManager.validateUser(usernameInput, passwordInput)) {
        loginMessage = "Incorrect password.";
    } else {
        loginMessage = "Login successful!";

        ui.titleScreenState  = 2;
    }
}

public void handleCreateAccount() {
    if (usernameInput.isEmpty()) {
        loginMessage = "Username cannot be empty.";
    } else if (passwordInput.isEmpty()) {
        loginMessage = "Password cannot be empty.";
    } else if (!passwordInput.equals(confirmPasswordInput)) {
        loginMessage = "Passwords do not match.";
    } else if (userManager.userExists(usernameInput)) {
        loginMessage = "Username already exists.";
    } else {
        userManager.createUser(usernameInput, passwordInput);
        loginMessage = "Account created successfully!";
        ui.titleScreenState  = 3;
    }
}



public void handleDeleteAccount() {
    
    if (!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
        

        if (userManager.validateUser(usernameInput, passwordInput)){ 
            
            System.out.println("Delete Button Pressed");
            File saveFile1 = new File("src/data/saves/" + usernameInput + "_save.dat");
            boolean deleted1 =  saveFile1.exists() && saveFile1.delete();
            System.out.println("Trying to delete: " + saveFile1.getAbsolutePath());
            
            if (!deleted1) {
                System.out.println("Delete Button Pressed it made it passed validator");
                userManager.deleteUser(usernameInput, passwordInput);
                System.out.println("Save data deleted for " + usernameInput + ".");
            } else {
                loginMessage = "No save data found for " + usernameInput + ".";
            }
        } else {
            loginMessage = "Invalid username or password.";
        } 
    }else {
        loginMessage = "Enter username and password to delete save data.";
    }

    // Clear inputs
    usernameInput = "";
    passwordInput = "";
    confirmPasswordInput = "";
}







public void handleForgotPassword() {
    if (usernameInput.isEmpty()) {
        loginMessage = "Username cannot be empty.";
    } else if (!userManager.userExists(usernameInput)) {
        loginMessage = "User not found.";
    } else {
        // Logic to reset password (e.g., send email)
        loginMessage = "Password reset link sent to your email.";
    }
}
public void handleLogout() {
    usernameInput = "";
    passwordInput = "";
    confirmPasswordInput = "";
    loginMessage = "";
    ui.titleScreenState  = 0;
}

// ******************************************************************
 

// ******************************************************************




}
