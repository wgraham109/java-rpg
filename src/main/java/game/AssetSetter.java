package main.java.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.object.ObjKey;
import main.java.tile.Tile;

public class AssetSetter {

    public GamePanel gp;

    public Tile[] tiles;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[4];
        setupTiles();
    }

    public BufferedImage getTileImage(int index) {
        return tiles[index].image;
    }

    public boolean getCollision(int index) {
        return tiles[index].collision;
    }

    public void setupTiles() {
        loadTileImage(0, "grass1", false);
        loadTileImage(1, "grass2", false);
        loadTileImage(2, "grass3", false);
        loadTileImage(3, "bushes", true);
    }

    public void loadTileImage(int index, String imagePath, boolean collision) {

        Utility uTool = new Utility();

        try {
            
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/main/res/tiles/" + imagePath + ".png"));
            tiles[index].image = uTool.scaleImage(tiles[index].image, gp.tileSize, gp.tileSize);
            tiles[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setObject() {
        gp.obj[0] = new ObjKey(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 23 * gp.tileSize;

        gp.obj[1] = new ObjKey(gp);
        gp.obj[1].worldX = 16 * gp.tileSize;
        gp.obj[1].worldY = 16 * gp.tileSize;

    }

}
