package main;

import javax.swing.JPanel;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {
    
    //Tile settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //size of one tile
    
    //Screen settings
    public final int maxScreenCol = 16; // columns in game view
    public final int maxScreenRow = 12; //rows in game view
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels total
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels total

    //World map settings
    public final int maxWorldCol = 56;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public Player player = new Player(this, keyH);
    
    TileManager tileM = new TileManager(this);
    public ArrayList<Entity> enemies = new ArrayList<>();
    //ArrayList<Entity> objects = new ArrayList<>();
    public Entity enemy = new Enemy(this);
    public Entity obj[] = new Entity[10];

    // GAMESTATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    /**
     * Constructor
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Start the thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop
     * - 60 FPS, delta time strategy used
     */
    @Override
    public void run() {

        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            
            currentTime = System.nanoTime();

            timer += currentTime - lastTime;
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // Update game information
                update();
                // Draw to the screen with the updated information
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Initial game settings
     */
    public void setupGame() {
        enemies.add(enemy);
        aSetter.setObject();
        gameState = titleState;
    }

    /**
     * Update method to update game state every frame
     */
    public void update() {

        if (gameState == playState) {
            
            for (Entity e : enemies) {
                e.update();
            }

            player.update();

            player.bulletM.update();
        }
        // if (gameState == pauseState) {

        // }

    }

    /**
     * Draw method to render updated game for each frame
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        drawStart += System.nanoTime();

        //Title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        else {

            //Tile
            tileM.draw(g2);

            //Objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2);
                }
            }

            //Player
            player.draw(g2);

            //Enemies
            for (Entity e : enemies) {
                e.draw(g2);
            }

            player.bulletM.draw(g2);

            //UI
            ui.draw(g2);
        }
        
        long drawEnd = System.nanoTime();
        long drawTime = drawEnd - drawStart;

        if (keyH.debugKey == true) {
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.BLACK);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col" + (int)(player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row" + (int)(player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw Time:" + drawTime, x, y);
        }

        g2.dispose();
    }

}
