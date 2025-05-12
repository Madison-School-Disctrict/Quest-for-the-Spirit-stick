package data;

import java.io.Serializable;
import java.util.ArrayList;


public class DataStorage implements Serializable{
		/**
	 *  the program said the below long was needed
	 */
	private static final long serialVersionUID = -2719804315385259224L;
		//Player stats
		int level;
		int maxLife;
		int life;
		int maxMana;
		int mana;
		int strength;
		int dexterity;
		int exp;
		int nextLevelExp;
		int coin;
		//Player location
		int currentMap;
		int currentWorldX;
		int currentWorldY;
		
	//Player Inventory
		ArrayList<String> itemNames = new ArrayList<>();
		ArrayList<Integer> itemAmounts = new ArrayList<>();
		int currentWeaponSlot;
		int currentShieldSlot;
		int currentMagicSlot;
		//Look into try to save the entire entity
		//ArrayList<Entity> Player = new ArrayList();
		
		//Objects on maps
		
		String mapObjectNames[][];
		int mapObjectWorldX[][];
		int mapObjectWorldY[][];
		String mapObjectLootNames[][];
		boolean mapObjectOpened[][];
		
}
