package tileInteractive;

import main.GamePanel;

public class ITMetalPlate extends InteractiveTile{
	GamePanel gp;
	public static final String itName = "Metal Plate";
	public ITMetalPlate(GamePanel gp, int col, int row) {
		super(gp,  col, row);
		this.gp = gp;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		name = itName;
		
		down1 = setup("/interactive_tiles/metalplate", gp.tileSize, gp.tileSize);	
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
