package entity;


import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity {

    GamePanel gp;

    public float screenX;
    public float screenY;

    public boolean collision = true;

    public Enemy(GamePanel gp) {
        this.gp = gp;
        screenX = gp.screenWidth/4;
        screenY = gp.screenHeight/4;
        getEnemyImage();
        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = 300.0f;
        worldY = 300.0f;
        speed = 2.0f;
    }

    public void getEnemyImage() {
        try {
            
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/enemies/bat.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        //Position of player
        double playerX = gp.player.worldX;
        double playerY = gp.player.worldY;

        // Calculate the relative change in worldX and worldY position over the bullets lifetime
        double xDiff = Math.abs(playerX - this.worldX);
        if (playerX < this.worldX) {
            xDiff = -xDiff;
        }
        double yDiff = Math.abs(playerY - this.worldY);
        if (playerY < this.worldY) {
            yDiff = -yDiff;
        }

        // Calculate the ratio of x movement vs worldY movement
        double sum = Math.abs(yDiff) + Math.abs(xDiff);
        double xPortion = xDiff/sum;
        double yPortion = yDiff/sum;

        // Calculate the x and worldY change needed to have the bullet travel the correct distance
        double hypotenuse = Math.pow(speed,2);
        double xDistance = hypotenuse * xPortion;
        double yDistance = hypotenuse * yPortion;

        // Apply the bullet movement of 1 frame
        if (xDistance < 0) {
            worldX -= (int)Math.sqrt(Math.abs(xDistance));
        }
        else {
            worldX += (int)Math.sqrt(xDistance);
        }
        if (yDistance < 0) {
            worldY -= (int)Math.sqrt(Math.abs(yDistance));
        }
        else {
            worldY += (int)Math.sqrt(yDistance);
        }

        screenX = (worldX - gp.player.worldX + gp.player.screenX);
        screenY = (worldY - gp.player.worldY + gp.player.screenY);
    }

    public void draw(Graphics2D g2) {

        
        g2.drawImage(down1, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
    }

}
