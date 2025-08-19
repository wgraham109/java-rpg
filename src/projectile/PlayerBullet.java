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

    int speed;
    public boolean active;
    double worldX, worldY;
    double velocityX, velocityY;

    int screenX, screenY;
    double mouseX, mouseY;
    public int lifetime;

    public BufferedImage image;

    public PlayerBullet(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        speed = 8;
        getBulletImage();
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

    public void fire() {
        active = true;
        worldX = player.worldX + gp.tileSize/4;
        worldY = player.worldY + gp.tileSize/4;
        screenX = player.screenX + gp.tileSize/4;
        screenY = player.screenX + gp.tileSize/4;
        lifetime = 0;
        getCurrentMousePosition();

        double dx = mouseX - (double) screenX;
        double dy = mouseY - (double) screenY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            velocityX = (dx / distance) * (double) speed;
            velocityY = (dy / distance) * (double) speed;
        }
        
    }

    // Fix
    public boolean isOutOfBounds() {
        return worldX < -10 || worldX > gp.screenWidth + 10 || 
               worldY < -10 || worldY > gp.screenHeight + 10;
    }

    public void update() {
    
        
        
        worldX += velocityX;
        worldY += velocityY;


        
        
        // double currentX = player.screenX;
        // double currentY = player.screenY;

        // // Calculate the relative change in x and y position over the bullets lifetime
        // double xDiff = Math.abs(mouseX - currentX);
        // if (mouseX < currentX) {
        //     xDiff = -xDiff;
        // }
        // double yDiff = Math.abs(mouseY - currentY);
        // if (mouseY < currentY) {
        //     yDiff = -yDiff;
        // }

        // // Calculate the ratio of x movement vs y movement
        // double sum = Math.abs(yDiff) + Math.abs(xDiff);
        // double xPortion = xDiff/sum;
        // double yPortion = yDiff/sum;

        // // Calculate the x and y change needed to have the bullet travel the correct distance
        // double hypotenuse = Math.pow(speed,2);
        // double xDistance = hypotenuse * xPortion;
        // double yDistance = hypotenuse * yPortion;

        // // Apply the bullet movement of 1 frame
        // if (xDistance < 0) {
        //     worldX -= (int)Math.sqrt(Math.abs(xDistance));
        // }
        // else {
        //     worldX += (int)Math.sqrt(xDistance);
        // }
        // if (yDistance < 0) {
        //     worldY -= (int)Math.sqrt(Math.abs(yDistance));
        // }
        // else {
        //     worldY += (int)Math.sqrt(yDistance);
        // }

        screenX = (int) (worldX - player.worldX + (double) player.screenX);
        screenY = (int) (worldY - player.worldY + (double) player.screenY);

        // Keep track of the projectile lifetime
        lifetime++;
        if (lifetime > 60) {
            active = false;
        }
    }

    public void draw(Graphics2D g2) {
        if (active) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


}
