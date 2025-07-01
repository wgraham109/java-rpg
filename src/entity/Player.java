package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import projectile.PlayerBullet;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    public PlayerBullet bullet;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down = ImageIO.read(getClass().getResourceAsStream("/res/characters/icons8-genie-48.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || 
            keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true) {

                if (keyH.upPressed == true) {
                    y -= speed;
                    direction = "up";
                }
                else if (keyH.downPressed == true) {
                    y += speed;
                    direction = "down";
                }
                else if (keyH.leftPressed == true) {
                    x -= speed;
                    direction = "left";
                }
                else if (keyH.rightPressed == true) {
                    x += speed;
                    direction = "right";
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

                if (keyH.spacePressed == true) {
                    bullet = new PlayerBullet(this, gp);
                }

            }
    }

    public void draw(Graphics2D g2) {
        
        BufferedImage image = down; // update to include directions

        switch (direction) {
        case "up":
            if (spriteNum == 1) {
                image = down; // change these lines when more images are added
            }
            if (spriteNum == 2) {
                image = down;
            }
            break;
        case "down":
            if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down;
                }
            break;
        case "left":
            if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down;
                }
            break;
        case "right":
            if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down;
                }
            break;
        }


        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        
    }

}
