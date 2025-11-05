package entity;

import java.awt.Rectangle;

import main.GamePanel;

/**
 * Class of enemy entities
 */
public class Enemy extends Entity {


    public float screenX;
    public float screenY;

    public boolean collision = true;

    /**
     * Constructor
     * @param gp
     */
    public Enemy(GamePanel gp) {
        super(gp);
        screenX = gp.screenWidth/4;
        screenY = gp.screenHeight/4;
        down1 = setup("enemies/bat");
        setDefaultValues();

        solidArea = new Rectangle(8, 16, 32, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /**
     * Default movement speed and starting position
     */
    public void setDefaultValues() {
        worldX = 600.0f;
        worldY = 600.0f;
        speed = 2.0f;
        down = true;
        maxLife = 100;
        life = 100;
    }

    /**
     * Update enemy position and values
     */
    public void update() {

        float playerX = gp.player.worldX;
        float playerY = gp.player.worldY;

        dx = playerX - this.worldX;
        dy = playerY - this.worldY;

        super.update();
    }

}
