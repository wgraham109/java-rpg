package projectile;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;

import entity.Player;
import main.GamePanel;

public class BulletManager {

    GamePanel gp;
    Player player;
    ArrayList<Bullet> activeBullets;
    ArrayDeque<Bullet> inactiveBullets;
    ArrayList<Bullet> bulletsToRemove;

    public BulletManager(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.activeBullets = new ArrayList<>();
        this.inactiveBullets = new ArrayDeque<>();
        this.bulletsToRemove = new ArrayList<>();
        // change hard coded bullet count
        for (int i = 0; i < 65; i++) {
            inactiveBullets.add(new Bullet(player, gp));
        }
    }

    public boolean spawnBullet() {
        Bullet bullet = inactiveBullets.poll();
        if (bullet == null) {
            return false;
        }
        bullet.fire();
        activeBullets.add(bullet);
        return true;
    }

    public void update() {
        for (Bullet bullet : activeBullets) {
            bullet.update();
            
            // Mark bullets for removal instead of removing immediately
            if (!bullet.active || bullet.isOutOfBounds()) {
                bulletsToRemove.add(bullet);
            }
        }
        for (Bullet bullet : bulletsToRemove) {
            inactiveBullets.offer(bullet);
            activeBullets.remove(bullet);
        }
        bulletsToRemove.clear();
    }

    public void draw(Graphics2D g2) {
        for (Bullet bullet : activeBullets) {
            bullet.draw(g2);
        }
    }
}
