package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.Utility;
import projectile.PlayerBulletManager;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public PlayerBulletManager bulletM;
    public int maxBullets = 60;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.bulletM = new PlayerBulletManager(gp, this);
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDefaultValues() {
        worldX = (float) gp.tileSize * 16;
        worldY = (float) gp.tileSize * 16;
        speed = 4.0f;
        direction = "down";
    }

    // Add sprite for player standing still

    public void getPlayerImage() {
        down1 = setup("CharacterDown1");
        down2 = setup("characterDown2");
        down3 = setup("characterDown3");
        down4 = setup("characterDown4");
        up1 = setup("characterUp1");
        up2 = setup("characterUp2");
        up3 = setup("characterUp3");
        up4 = setup("characterUp4");
        right1 = setup("characterRight1");
        right2 = setup("characterRight2");
        right3 = setup("characterRight3");
        right4 = setup("characterRight4");
        left1 = setup("characterLeft1");
        left2 = setup("characterLeft2");
        left3 = setup("characterLeft3");
        left4 = setup("characterLeft4");
    }

    public BufferedImage setup(String imageName) {
        Utility uTool = new Utility();
        BufferedImage image = null;

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/characters/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }    

    public void update() {

        if (keyH.upPressed || keyH.downPressed || 
            keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {

            boolean up = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;

            float dx = 0;
            float dy = 0;

            if (keyH.upPressed) {
                direction = "up";
                up = true;
            }
            if (keyH.downPressed) {
                direction = "down";
                down = true;
            }
            if (keyH.leftPressed) {
                direction = "left";
                left = true;
            }
            if (keyH.rightPressed) {
                direction = "right";
                right = true;
            }

            // Check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // If collision is false, player can move
            if (!collisionOn) {
                // switch (direction) {
                //     case "up":
                //         worldY -= speed;
                //         break;
                //     case "down":
                //         worldY += speed;
                //         break;
                //     case "right":
                //         worldX += speed;
                //         break;
                //     case "left":
                //         worldX -= speed;
                //         break;
                // }
                if (up) dy -= 1.0f;
                if (down) dy += 1.0f;
                if (left) dx -= 1.0f;
                if (right) dx += 1.0f;

                float length = (float) Math.sqrt(dx * dx + dy * dy);
                if (length != 0) {
                    worldX += (dx / length) * speed;
                    worldY += (dy / length) * speed;
                }
            }

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

            if (keyH.spacePressed) {
                bulletM.spawnBullet();
            }
        }
        
        bulletM.update();
    }

    public void interactWithObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println(hasKey + "keys");
                    break;
                default:
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        
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
        bulletM.draw(g2);
    }

}
