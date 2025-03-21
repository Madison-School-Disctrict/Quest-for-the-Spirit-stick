package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.ObjAxe;
import object.ObjCoinsBronze;
import object.ObjHeart;
import object.ObjManaCrystal;
import object.ObjPickaxe;

	public class Monster extends Entity{
		GamePanel gp;
		
	 public Monster(GamePanel gp) {
		super(gp);
		this.gp = gp;
		//projectile = new ObjRock(gp);
		
	}
		public void getImage() {
			
			
		}
		public void setAction() {
			
	    }
		public void damageReaction() {
						
		}
		
		public void checkDrop() {
			//random number for drop
			int i = new Random().nextInt(100)+1;
			
			//set the monster drop 
			if(i < 40) {
				dropItem(new ObjCoinsBronze(gp));
			}
			if(i >= 40 && i < 55) {
				dropItem(new ObjHeart(gp));
			}
			if(i >= 55 && i < 70) {
				dropItem(new ObjManaCrystal(gp));
			}
			if(i >= 70 && i < 80) {
				dropItem(new ObjPickaxe(gp));
			}
			if(i >= 80 && i < 85) {
				dropItem(new ObjAxe(gp));
			}
			if(i >= 85 && i < 90) {
				dropItem(new ObjAxe(gp));
			}
			if(i >= 90 && i < 100) {
				dropItem(new ObjManaCrystal(gp));
			}
			
		}
		
}
