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

    float speed;
    public boolean active;
    float worldX, worldY;
    float velocityX, velocityY;

    float screenX, screenY;
    float mouseX, mouseY;
    public int lifetime;

    public BufferedImage image;

    public PlayerBullet(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        speed = 8.0f;
        getBulletImage();
    }

    // Get the mouse position to use for bullet direction
    public void getCurrentMousePosition() {
        Point p = gp.getMousePosition();   // null if pointer not over the panel
        if (p != null) {
            mouseX = (float) p.x;
            mouseY = (float) p.y;
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
        worldX = player.worldX;
        worldY = player.worldY;
        screenX = player.screenX;
        screenY = player.screenY;
        lifetime = 0;
        getCurrentMousePosition();

        float dx = mouseX - screenX;
        float dy = mouseY - screenY;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        
        if (distance > 0) {
            velocityX = (dx / distance) * speed;
            velocityY = (dy / distance) * speed;
        }
        
    }

    public boolean isOutOfBounds() {
        return worldX < -10.0f || worldX > gp.worldWidth || 
               worldY < -10.0f || worldY > gp.worldHeight;
    }

    public void update() {
    
        worldX += velocityX;
        worldY += velocityY;
        screenX = (worldX - player.worldX + player.screenX);
        screenY = (worldY - player.worldY + player.screenY);

        // Keep track of the projectile lifetime
        lifetime++;
        if (lifetime > 60) {
            active = false;
        }
    }

    public void draw(Graphics2D g2) {
        if (active) {
            g2.drawImage(image, (int) screenX, (int) screenY, gp.tileSize, gp.tileSize, null);
        }
    }


}
