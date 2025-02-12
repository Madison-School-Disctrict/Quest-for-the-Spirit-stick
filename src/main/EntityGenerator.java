package main;

import entity.Entity;
import object.ObjAxe;
import object.ObjBoots;
import object.ObjChest;
import object.ObjCoinsBronze;
import object.ObjCoinsGold;
import object.ObjCoinsSilver;
import object.ObjDoor;
import object.ObjDoorIron;
import object.ObjFireBall;
import object.ObjFireBallBlue;
import object.ObjFireBallWhite;
import object.ObjHeart;
import object.ObjKey;
import object.ObjLantern;
import object.ObjManaCrystal;
import object.ObjPickaxe;
import object.ObjPotionRed;
import object.ObjShieldBlue;
import object.ObjShieldWood;
import object.ObjSpiritStick;
import object.ObjSwordNormal;
import object.ObjTent;

public class EntityGenerator {
	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	public Entity getObject(String itemName) {
		Entity obj = null;
		switch(itemName) {
		case ObjAxe.objName: obj = new ObjAxe(gp); break;  // Change all the  like the following and add all object to list
		case ObjBoots.objName: obj = new ObjBoots(gp); break;
		case ObjChest.objName: obj = new ObjChest(gp); break;
		case ObjCoinsBronze.objName: obj = new ObjCoinsBronze(gp); break;
		case ObjCoinsGold.objName: obj = new ObjCoinsGold(gp); break;
		case ObjCoinsSilver.objName: obj = new ObjCoinsSilver(gp); break;
		case ObjDoor.objName: obj = new ObjDoor(gp); break;
		case ObjDoorIron.objName: obj = new ObjDoorIron(gp); break;
		case ObjFireBall.objName: obj = new ObjFireBall(gp); break;
		case ObjFireBallBlue.objName: obj = new ObjFireBallBlue(gp); break;
		case ObjFireBallWhite.objName: obj = new ObjFireBallWhite(gp); break;
		case ObjHeart.objName: obj = new ObjHeart(gp); break;
		case ObjKey.objName: obj = new ObjKey(gp); break;
		case ObjLantern.objName: obj = new ObjLantern(gp); break;
		case ObjManaCrystal.objName: obj  = new ObjManaCrystal(gp); break;
		case ObjPickaxe.objName: obj = new ObjPickaxe(gp); break;
		case ObjPotionRed.objName: obj = new ObjPotionRed(gp); break;
		case ObjShieldBlue.objName: obj = new ObjShieldBlue(gp); break;
		case ObjShieldWood.objName: obj = new ObjShieldWood(gp); break;
		case ObjSwordNormal.objName: obj = new ObjSwordNormal(gp); break;
		case ObjTent.objName: obj = new ObjTent(gp); break;
		case ObjSpiritStick.objName: obj = new ObjSpiritStick(gp); break;
		}
		//System.out.println("it update the following object." +  obj + "   while looking for  " + itemName );
		return obj;

		
		}
}
