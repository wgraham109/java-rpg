package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;
import projectile.BulletManager;


public class Player extends Entity {
    
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int hasKey = 0;
    int bulletCounter = 0;

    /**
     * Constructor
     * @param gp
     * @param keyH
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        this.bulletM = new BulletManager(gp, this);
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /**
     * Default player values
     */
    public void setDefaultValues() {
        worldX = (float) gp.tileSize * 16;
        worldY = (float) gp.tileSize * 16;
        speed = 4.0f;
        down = true;

        // Player Status
        maxLife = 100;
        life = 100;

    }

    // Add sprite for player standing still

    /**
     * Categorize player images
     */
    public void getPlayerImage() {
        down1 = setup("characters/CharacterDown1");
        down2 = setup("characters/characterDown2");
        down3 = setup("characters/characterDown3");
        down4 = setup("characters/characterDown4");
        up1 = setup("characters/characterUp1");
        up2 = setup("characters/characterUp2");
        up3 = setup("characters/characterUp3");
        up4 = setup("characters/characterUp4");
        right1 = setup("characters/characterRight1");
        right2 = setup("characters/characterRight2");
        right3 = setup("characters/characterRight3");
        right4 = setup("characters/characterRight4");
        left1 = setup("characters/characterLeft1");
        left2 = setup("characters/characterLeft2");
        left3 = setup("characters/characterLeft3");
        left4 = setup("characters/characterLeft4");
    }


    /**
     * Interaction with objects
     * @param i
     */
    public void interactWithObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println(hasKey + "keys");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Game loop update for player
     */
    public void update() {

        if (keyH.upPressed || keyH.downPressed || 
            keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {

            float dx = 0;
            float dy = 0;

            up = keyH.upPressed;
            down = keyH.downPressed;
            left = keyH.leftPressed;
            right = keyH.rightPressed;

            // Check event
            gp.eHandler.checkEvent();

            if (up) dy -= 1.0f;
            if (down) dy += 1.0f;
            if (left) dx -= 1.0f;
            if (right) dx += 1.0f;

            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            
            float desiredMoveX = (dx / distance) * speed;
            float desiredMoveY = (dy / distance) * speed;

            boolean xBlocked = gp.collisionChecker.checkTileCollisionX(this, desiredMoveX);
            boolean yBlocked = gp.collisionChecker.checkTileCollisionY(this, desiredMoveY);

            float finalMoveX = xBlocked ? 0 : desiredMoveX;  // Only move if NOT blocked
            float finalMoveY = yBlocked ? 0 : desiredMoveY;

            worldX += finalMoveX;
            worldY += finalMoveY;
            
            //Check object collision
            int objIndex = gp.collisionChecker.checkObject(this, true);

            interactWithObject(objIndex);

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

            if (keyH.spacePressed) {
                if (bulletCounter == 5) {
                    bulletM.spawnBullet();
                    bulletCounter = 1;
                }
                else {
                    bulletCounter++;
                }
            }
        }
        
    }

    public void draw(Graphics2D g2) {
        
        super.draw(g2);

    }

}
