package main.java.tile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.java.game.GamePanel;

public class Map {

    GamePanel gp;

    public int[][] tiles;
    public Point playerSpawn;
    public List<Point> enemySpawns;
    public int width, height;

    public Map(int[][] tiles, Point playerSpawn, ArrayList<Point> enemySpawns, GamePanel gp) {
        this.gp = gp;
        this.tiles = tiles;
        this.playerSpawn = playerSpawn;
        this.enemySpawns = enemySpawns;
        this.width = tiles[0].length;
        this.height = tiles.length;
    }




}
