package entity;

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
    }

    /**
     * Default movement speed and starting position
     */
    public void setDefaultValues() {
        worldX = 300.0f;
        worldY = 300.0f;
        speed = 2.0f;
        down = true;
    }

    /**
     * Update enemy position and values
     */
    public void update() {

        //Position of player
        float playerX = gp.player.worldX;
        float playerY = gp.player.worldY;

        // Calculate the relative change in worldX and worldY position over the bullets lifetime
        float dx = playerX - this.worldX;
        float dy = playerY - this.worldY;

        // Calculate the distance the enemy travels
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            worldX += (dx / distance) * speed;
            worldY += (dy / distance) * speed;
        }

    }

}
