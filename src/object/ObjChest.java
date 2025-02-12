  package object;


//importing classes from other folders
import entity.Entity;
import main.GamePanel;

//the class itself
public class ObjChest extends Entity{  //extends = using parts of the Entity class
	//intitializing variables
	GamePanel gp;
	public static final String objName = "Chest";
	public ObjChest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_obstacle; //types are defined in entity
		name = objName;
		image = setup("/objects/chest", gp.tileSize, gp.tileSize);
		//open chest image
		image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
		
		down1 = image;
		collision = true;
		
		//The dimensions of the hitbox
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;	
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] ="You open the chest and find a " + loot.name + "!" +"\n...But you cannot carry any more!";
		dialogues[1][0] ="You open the chest and find a " + loot.name + "!" + "\n You obtained the " + loot.name + "!";
		dialogues[2][0] ="It's Empty";
	}
	public void interact() {
		if(!opened) {
			gp.playSE(3);
			
			if(!gp.player.canObtainItem(loot)) {
				startDialogue(this,0);
			}
			else {
				startDialogue(this,1);
				down1 = image2;
				opened = true;
			}
		} 
		else {
			startDialogue(this,2);
		}
	}
}
