package main.java.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.java.main.GamePanel;
import main.java.main.Utility;

public class TileManager {


    GamePanel gp;
    
    // Set of tiles used
    public Tile[] tile;

    // 2D array respresenting the actual map of tiles to be drawn
    public int mapTileNum[][];


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/main/res/maps/Layout.txt");
    }

    public void getTileImage() {
        setup(0, "grass1", false);
        setup(1, "grass2", false);
        setup(2, "grass3", false);
        setup(3, "bushes", true);
    }

    public void setup(int index, String imagePath, boolean collision) {

        Utility uTool = new Utility();

        try {
            
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Load tile images into the tile array
    public void loadMap(String filePath) {
        try {
            
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Draw tiles on the screen
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            //Player is always at the center of the screen, tiles are drawn to the screen relative to the player world position
            int screenX = (int) (worldX - gp.player.worldX + gp.player.screenX);
            int screenY = (int) (worldY - gp.player.worldY + gp.player.screenY);

            //Check if the tile would appear on screen before rendering
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
    }
}
