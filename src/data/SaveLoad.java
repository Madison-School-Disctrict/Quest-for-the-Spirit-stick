package data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import main.GamePanel;

public class SaveLoad {
	GamePanel gp;
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
		
	}
	
	public void save(String name) {
		try {
			String fileName = getSavePath(name);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			DataStorage ds = new DataStorage();
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.maxMana = gp.player.maxMana;
			ds.mana = gp.player.mana;
			ds.strength = gp.player.strength;
			ds.dexterity = gp.player.dexterity;
			ds.exp = gp.player.exp;
			ds.nextLevelExp = gp.player.nextLevelExp;
			ds.coin = gp.player.coin;	

			//Player location
			ds.currentMap = gp.currentMap;
			ds.currentWorldX = gp.player.worldX;
			ds.currentWorldY = gp.player.worldY;
			
			
			
			//Player Inventory
			for(int i = 0 ; i < gp.player.inventory.size(); i++) {
				ds.itemNames.add(gp.player.inventory.get(i).name);
				ds.itemAmounts.add(gp.player.inventory.get(i).amount);
			}
			
			//Player Equipment
			ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
			ds.currentMagicSlot = gp.player.getCurrentMagicSlot();
			
			//Objects on Map
			
			ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			
			for(int mapNum = 0 ; mapNum < gp.maxMap; mapNum++) {
				for (int i = 0 ; i < gp.obj[1].length;i++) {
					if(gp.obj[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] ="NA";
					}
					else {
						ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}
			
			//Write the DataStorage object to the oos
			oos.writeObject(ds);
			oos.close();
			System.out.println("Save Complete!");
		}  catch (Exception e) {
			System.out.println("Save Exception!");
			e.printStackTrace();
		}
	}
	public void load(String name) {
		try {
			String fileName = getSavePath(name);
			Path filePath = Paths.get(fileName);

			if (!Files.exists(filePath)) {
				System.out.println("Load failed: Save file does not exist.");
				return;
			}

			ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath));


			//Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			gp.player.level = ds.level;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.maxMana = ds.maxMana;
			gp.player.mana = ds.mana;
			gp.player.strength = ds.strength;
			gp.player.dexterity = ds.dexterity;
			gp.player.exp = ds.exp;
			gp.player.nextLevelExp = ds.nextLevelExp;
			gp.player.coin = ds.coin;

			//Player location
			gp.currentMap = ds.currentMap;
			gp.player.worldX = ds.currentWorldX;
			gp.player.worldY = ds.currentWorldY;
			
			//Player Inventory
			gp.player.inventory.clear();
			for (int i = 0; i< ds.itemNames.size(); i++ ) {
				gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
				gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);

			}
			//Player Equipment
			gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
			gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
			gp.player.currentMagic = gp.player.inventory.get(ds.currentMagicSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getAttackImage();
			
			// Objects on map
			

			
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for(int i = 0 ; i < gp.obj[1].length; i++) {
					if(ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i] = null;
					} else {
						gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if(ds.mapObjectLootNames[mapNum][i] != null) {
							gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}	
			ois.close();
			System.out.println("Load Complete!");
		}catch (NullPointerException e) {
			System.out.println("Load Exception.  It did not load correctly");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("Load Exception.  It did not load correctly");
			e.printStackTrace();
		}catch (java.io.EOFException e) {
			System.out.println("Load Exception.  It did not load correctly");
			e.printStackTrace();		
		}catch(Exception e) {
			System.out.println("Load Exception.  It did not load correctly");
			e.printStackTrace();
			
		}
		
		
	
	}

	// public void deleteSave(String username) {
	// 	load(gp.usernameInput);
	// 	File file = new File( "src/data/saves/" + username + "_save.dat");
	// 	if (file.exists()) {
	// 		if (file.delete()) {
	// 			gp.loginMessage = ("Save data for " + username + " deleted.");
	// 		} else {
	// 			gp.loginMessage = ("Failed to delete save data for " + username + ".");
	// 		}
	// 	}
	// }


	public void deleteSave(String username) {
		String fileName = getSavePath(username);
		Path path = Paths.get(fileName);

		try {
			if (Files.exists(path)) {
				Files.delete(path);
				gp.loginMessage = "Save data for " + username + " deleted.";
			} else {
				gp.loginMessage = "No save data found for " + username + ".";
			}
		} catch (Exception e) {
			gp.loginMessage = "Failed to delete save data for " + username + ".";
			e.printStackTrace();
		}
	}




	public static String getSavePath(String name) {
		Path dir = Paths.get(System.getProperty("user.home"), "MyGameSaves");
		try {
			Files.createDirectories(dir); // creates the directory if it doesn't exist
		} catch (Exception e) {
			System.out.println("Failed to create save directory.");
			e.printStackTrace();
		}
		return dir.resolve(name + "_save.dat").toString();
	}


	public static boolean saveExists(String name) {
		Path filePath = Paths.get(getSavePath(name));
		return Files.exists(filePath);
	}


}





