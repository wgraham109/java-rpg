package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

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
    
}
