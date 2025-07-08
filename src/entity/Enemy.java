package entity;


import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity {

    GamePanel gp;

    public Enemy(GamePanel gp) {
        this.gp = gp;
        getEnemyImage();
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 300;
        y = 300;
        speed = 2;
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
        int playerX = gp.player.x;
        int playerY = gp.player.y;

        // Calculate the relative change in x and y position over the bullets lifetime
        double xDiff = Math.abs(playerX - this.x);
        if (playerX < this.x) {
            xDiff = -xDiff;
        }
        double yDiff = Math.abs(playerY - this.y);
        if (playerY < this.y) {
            yDiff = -yDiff;
        }

        // Calculate the ratio of x movement vs y movement
        double sum = Math.abs(yDiff) + Math.abs(xDiff);
        double xPortion = xDiff/sum;
        double yPortion = yDiff/sum;

        // Calculate the x and y change needed to have the bullet travel the correct distance
        double hypotenuse = Math.pow(speed,2);
        double xDistance = hypotenuse * xPortion;
        double yDistance = hypotenuse * yPortion;

        // Apply the bullet movement of 1 frame
        if (xDistance < 0) {
            x -= (int)Math.sqrt(Math.abs(xDistance));
        }
        else {
            x += (int)Math.sqrt(xDistance);
        }
        if (yDistance < 0) {
            y -= (int)Math.sqrt(Math.abs(yDistance));
        }
        else {
            y += (int)Math.sqrt(yDistance);
        }

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(down1, x, y, gp.tileSize, gp.tileSize, null);
    }

}
