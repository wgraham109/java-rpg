package main.java.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.java.game.GamePanel;
import main.java.game.KeyHandler;
import main.java.projectile.BulletManager;


public class Player extends Entity {
    
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int keys = 0;
    int bulletCounter = 0;

    /**
     * Constructor
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        this.bulletM = new BulletManager(gp, this);
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /**
     * Default player values
     */
    public void setDefaultValues() {
        worldX = (float) gp.tileSize * 16;
        worldY = (float) gp.tileSize * 16;
        speed = 4.0f;
        down = true;

        // Player Status
        maxLife = 100;
        life = 100;

        dx = 0;
        dy = 0;

    }

    // Add sprite for player standing still

    /**
     * Categorize player images
     */
    public void getPlayerImage() {
        down1 = setup("characters/CharacterDown1");
        down2 = setup("characters/characterDown2");
        down3 = setup("characters/characterDown3");
        down4 = setup("characters/characterDown4");
        up1 = setup("characters/characterUp1");
        up2 = setup("characters/characterUp2");
        up3 = setup("characters/characterUp3");
        up4 = setup("characters/characterUp4");
        right1 = setup("characters/characterRight1");
        right2 = setup("characters/characterRight2");
        right3 = setup("characters/characterRight3");
        right4 = setup("characters/characterRight4");
        left1 = setup("characters/characterLeft1");
        left2 = setup("characters/characterLeft2");
        left3 = setup("characters/characterLeft3");
        left4 = setup("characters/characterLeft4");
    }


    /**
     * Interaction with objects
     * @param i
     */
    public void interactWithObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    keys++;
                    gp.obj[i] = null;
                    System.out.println(keys + " keys");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Game loop update for player
     */
    public void update() {

        if (keyH.upPressed || keyH.downPressed || 
            keyH.leftPressed || keyH.rightPressed) {

            up = keyH.upPressed;
            down = keyH.downPressed;
            left = keyH.leftPressed;
            right = keyH.rightPressed;

            dx = 0;
            dy = 0;
            
            if (up) dy -= 1.0f;
            if (down) dy += 1.0f;
            if (left) dx -= 1.0f;
            if (right) dx += 1.0f;

            super.update();
            
            //Check object collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            interactWithObject(objIndex);

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteNum == 4) {
                    spriteNum = 1;
                }
                else {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }

        // Check tile event
        gp.eHandler.checkEvent();

        if (keyH.spacePressed) {
            if (bulletCounter == 5) {
                bulletM.spawnBullet();
                bulletCounter = 1;
            }
            else {
                bulletCounter++;
            }
        }
        
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (right) {
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
        }
        else if (left) {
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
        }
        if (down) {
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
        }
        else if (up) {
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
        }

        g2.drawImage(image, screenX, screenY, null);


    }

}
