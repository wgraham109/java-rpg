package main;

import javax.swing.JFrame;

//https://icons8.com/icon/4VVzU1o5qv3P/genie
// SciGho grass https://ninjikin.itch.io/grass
// https://0-laxerglaxer.itch.io/game
// https://greatdocbrown.itch.io/coins-gems-etc/download/eyJpZCI6NzA3MTg4LCJleHBpcmVzIjoxNzUyNjg2MzIxfQ%3d%3d.LbfqSygOX%2f%2bWrtY%2fWH03AbcNOTo%3d


/**
 * Open Issues
 * - Refactor UI, KeyHandler, Player, and GamePanel
 *      for more logical organization of key handling for movement, bullet firing and UI inputs
 * - Add bullet collisions
 * - add javadoc
 * - Fix player collisions not being overriden by diagonal movement (diagonal diretion strings rather than just up, down, right, left)
 * - Refactor enemy movement
 * - add more game events
 * - add more robust map system
 * - add more levels
 * - refactor entity to contain super render and draw functions
 */
public class Game {

    /**
     * Application entry point
     * 
     * @param args
     */
    public static void main(String args[]) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}

