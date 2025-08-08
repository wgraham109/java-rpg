package projectile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import entity.Player;
import main.GamePanel;

public class PlayerBullet {

    Player player;
    GamePanel gp;

    int speed = 8;

    // Starting point of the bullet
    int worldX;
    int worldY;

    int screenX;
    int screenY;

    // Mouse position at the time the bullet is created/fired
    int mouseX;
    int mouseY;

    public int lifetime;
    public BufferedImage image;

    public PlayerBullet(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        worldX = (int) player.worldX + gp.tileSize/4;
        worldY = (int) player.worldY + gp.tileSize/4;
        screenX = (int) player.worldX + gp.tileSize/4;
        screenY = (int) player.worldY + gp.tileSize/4;
        lifetime = 0;
        getBulletImage();
        getCurrentMousePosition();
    }

    // Get the mouse position to use for bullet direction
    public void getCurrentMousePosition() {
        Point p = gp.getMousePosition();   // null if pointer not over the panel
        if (p != null) {
            mouseX = p.x;
            mouseY = p.y;
        }
    }

    public void getBulletImage() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/bullets/Bullet.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Reformat this and potentially change to an angle based calculation

    // FIX AIMING
    public void update() {
    
        int currentX = player.screenX;
        int currentY = player.screenY;

        // Calculate the relative change in x and y position over the bullets lifetime
        double xDiff = Math.abs(mouseX - currentX);
        if (mouseX < currentX) {
            xDiff = -xDiff;
        }
        double yDiff = Math.abs(mouseY - currentY);
        if (mouseY < currentY) {
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

        screenX = (int) (worldX - player.worldX + gp.player.screenX);
        screenY = (int) (worldY - player.worldY + gp.player.screenY);

        // Keep track of the projectile lifetime
        lifetime++;
        // if (lifetime > 60) {
        //     player.bullets.remove(this);
        // }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // replace with drawing the bullet image

    }


}
