package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.Utility;
import projectile.BulletManager;

public class Entity {

    GamePanel gp;
    KeyHandler keyH;
    BulletManager bulletM;
    
    public float worldX, worldY;
    public float speed;

    public BufferedImage down1, down2, down3, down4, up1, up2, up3, up4, right1, right2, right3, right4, left1, left2, left3, left4;
    public String direction;

    // Used for keeping track of the current sprite (walking animations)
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // The collision area of the entity
    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;

    // Whether or not the entity is colliding with a tile
    public boolean collisionOn = false;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Load player images
     * @param imagePath
     * @return
     */
    public BufferedImage setup(String imagePath) {
        Utility uTool = new Utility();
        BufferedImage image = null;

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {}

    public void draw(Graphics2D g2) {
        
        //Player is always at the center of the screen, objects are drawn to the screen relative to the player world position
        int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
        int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

        //Check if the object would appear on screen before rendering
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image = null;

            switch (direction) {
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3; 
                }
                if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                if (spriteNum == 4) {
                    image = up4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                if (spriteNum == 4) {
                    image = left4;
                }
                break;
            }
            
            g2.drawImage(image, screenX, screenY, null);

        }
    }


}
