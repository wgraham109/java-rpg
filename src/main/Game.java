package main;

import javax.swing.JFrame;

//https://icons8.com/icon/4VVzU1o5qv3P/genie
// SciGho grass https://ninjikin.itch.io/grass
// https://0-laxerglaxer.itch.io/game


public class Game {

    //private Player player;

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

        gamePanel.startGameThread();

    }
}

