package main.java.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.game.GamePanel;
import main.java.game.KeyHandler;
import main.java.game.Utility;
import main.java.projectile.BulletManager;

public class Entity {

    GamePanel gp;
    KeyHandler keyH;
    public BulletManager bulletM;
    Utility uTool;
    
    public float worldX, worldY;
    public float speed;
    float dx;
    float dy;
    public String name;

    public BufferedImage down1, down2, down3, down4, up1, up2, up3, up4, right1, right2, right3, right4, left1, left2, left3, left4;
    public boolean up, down, right, left;

    // Used for keeping track of the current sprite (walking animations)
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // The collision area of the entity
    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;

    // Whether or not the entity is colliding with a tile
    public boolean vertCollisionOn = false;
    public boolean horizCollisionOn = false;

    public int maxLife;
    public int life;
    public int level;
    public int exp;
    public int attack;
    public int defense;


    public Entity(GamePanel gp) {
        this.gp = gp;
        this.uTool = new Utility(); 
    }

    /**
     * Load player images
     * @param imagePath
     * @return
     */
    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/main/res/" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

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

    public void draw(Graphics2D g2) {
        
        //Player is always at the center of the screen, objects are drawn to the screen relative to the player world position
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        //Check if the object would appear on screen before rendering
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image = down1;

            g2.drawImage(image, screenX, screenY, null);

        }
    }


}
