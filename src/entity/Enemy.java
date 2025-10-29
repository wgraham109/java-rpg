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

        solidArea = new Rectangle(8, 8, 32, 32);
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
    }

    /**
     * Update enemy position and values
     */
    public void update() {

        float playerX = gp.player.worldX;
        float playerY = gp.player.worldY;

        float dx = playerX - this.worldX;
        float dy = playerY - this.worldY;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        float desiredMoveX = (dx / distance) * speed;
        float desiredMoveY = (dy / distance) * speed;

        boolean xBlocked = gp.collisionChecker.checkTileCollisionX(this, desiredMoveX);
        boolean yBlocked = gp.collisionChecker.checkTileCollisionY(this, desiredMoveY);

        float finalMoveX = xBlocked ? 0 : desiredMoveX;
        float finalMoveY = yBlocked ? 0 : desiredMoveY;

        worldX += finalMoveX;
        worldY += finalMoveY;

    }

}
