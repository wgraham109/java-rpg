package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import projectile.PlayerBullet;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public HashMap<PlayerBullet, Integer> bullets;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.bullets = new HashMap<>();
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 16;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterDown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterDown3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterDown4.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterUp2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterUp3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterUp4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterRight3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterRight4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterLeft3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/characters/CharacterLeft4.png"));
        } 
       
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true) {

                if (keyH.upPressed == true) {
                    worldY -= speed;
                    direction = "up";
                }
                else if (keyH.downPressed == true) {
                    worldY += speed;
                    direction = "down";
                }
                else if (keyH.leftPressed == true) {
                    worldX -= speed;
                    direction = "left";
                }
                else if (keyH.rightPressed == true) {
                    worldX += speed;
                    direction = "right";
                }

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

                if (keyH.spacePressed == true) {
                    bullets.put(new PlayerBullet(this, gp), 0);
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
    }

}
