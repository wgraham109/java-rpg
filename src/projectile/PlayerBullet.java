package projectile;

import java.awt.Graphics2D;
// import java.awt.MouseInfo;
import java.awt.Point;
// import java.awt.PointerInfo;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



import entity.Player;
import main.GamePanel;

public class PlayerBullet {

    Player player;
    GamePanel gp;

    int speed = 4;

    // Starting point of the bullet
    int x;
    int y;

    // Mouse position at the time the bullet is created/fired
    int mouseX;
    int mouseY;

    public int lifetime;
    public BufferedImage image;

    public PlayerBullet(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        x = player.x + gp.tileSize/2;
        y = player.y + gp.tileSize/2;
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
    public void update() {
    
        int currentX = player.x + gp.tileSize/2;
        int currentY = player.y + gp.tileSize/2;

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
        x += (int)xDistance;
        y += (int)yDistance;

        // Keep track of the projectile lifetime
        lifetime++;
        // if (lifetime > 60) {
        //     player.bullets.remove(this);
        // }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // replace with drawing the bullet image

    }


}
