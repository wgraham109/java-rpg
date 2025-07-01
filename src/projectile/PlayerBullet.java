package projectile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class PlayerBullet {

    Player player;
    GamePanel gp;

    int speed = 8;
    int x;
    int y;
    int xDirection;
    int yDirection;
    int angle;
    public BufferedImage image;

    public PlayerBullet(Player player, GamePanel gp) {
        this.player = player;
        this.gp = gp;
        x = player.x + 8;
        y = player.y + 8;
        getBulletImage();
    }

    public void update() {
        x += speed;
    }

    public void getBulletImage() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/bullets/Bullet.png"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // replace with drawing the bullet image
    }


}
