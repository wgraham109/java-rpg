package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    Font arial40, arial80b;

    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80b = new Font("Arial", Font.BOLD, 80);

    }

    public void draw(Graphics2D g2) {
        
        this.g2 = g2;
        g2.setFont(arial40);
        g2.setColor(Color.black);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        else if (gp.gameState == gp.playState) {
            drawPlayerHealthBar();
            if (gp.enemy.life > 0) {
                drawEnemyHealthBar();
            }
        }
        else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawTitleScreen() {

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "RPG";
        int x = getCenteredTextX(text);
        int y = gp.screenHeight/2;

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //Title
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        //Menu
        text = "New Game";
        x = getCenteredTextX(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Load Game";
        x = getCenteredTextX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getCenteredTextX(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getCenteredTextX(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public void drawPlayerHealthBar() {

        double scale = (double)gp.tileSize * 2 / gp.player.maxLife;
        double hpBarValue = scale * gp.player.life;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(654, 15, (gp.tileSize * 2) + 1, gp.tileSize/2 + 2);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(655, 16, (int)hpBarValue, gp.tileSize/2);
    }
    public void drawEnemyHealthBar() {

        double scale = (double)gp.tileSize * 2 / gp.enemy.maxLife;
        double hpBarValue = scale * gp.enemy.life;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(100, 15, (gp.tileSize * 2) + 1, gp.tileSize/2 + 2);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(101, 16, (int)hpBarValue, gp.tileSize/2);
    }

    public int getCenteredTextX(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
