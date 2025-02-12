package tileInteractive;

import java.awt.Color;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.ObjAxe;
import object.ObjCoinsBronze;
import object.ObjHeart;
import object.ObjManaCrystal;

public class ITDestructibleWall extends InteractiveTile{
	GamePanel gp;

	public ITDestructibleWall(GamePanel gp, int col, int row) {
		super(gp,  col, row);
		this.gp = gp;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		down1 = setup("/interactive_tiles/destructiblewall", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 3;
				
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_pickaxe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	public void playSE() {
		gp.playSE(20);
	}
//	public InteractiveTile getDestroyedForm() {
//		InteractiveTile tile = new ITTrunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
//		return tile;
//	}
	
	public Color getParticleColor() {
		Color color = new Color(65,65,65);		
		return color;
	}
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
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
			dropItem(new ObjHeart(gp));
		}
		if(i >= 80 && i < 85) {
			dropItem(new ObjManaCrystal(gp));
		}
		if(i >= 85 && i < 90) {
			dropItem(new ObjAxe(gp));
		}
		if(i >= 90 && i < 100) {
			dropItem(new ObjManaCrystal(gp));
		}
		
	}
}
