package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    Font arial40, arial80b;

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
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        text = "Load Game";
        x = getCenteredTextX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        text = "Quit";
        x = getCenteredTextX(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
    }

    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getCenteredTextX(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }

    public int getCenteredTextX(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
