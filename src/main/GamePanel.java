package main;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //size of one tile
    public final int maxScreenCol = 16; // columns in game view
    public final int maxScreenRow = 12; //rows in game view

    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels total
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels total

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    // Constructor
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    // Start the game thread
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            
            // Update game information
            update();

            // Draw to the screen with the updated information
            repaint();

            // Sleep for remaining time until next frame
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        
    }

    // Update game information
    public void update() {
        player.update();
        if (player.bullet != null) {
            player.bullet.update();
        }
    }

    // Draw with updated information
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        if (player.bullet != null) {
            player.bullet.draw(g2);
        }

        g2.dispose();
    }

    

}
