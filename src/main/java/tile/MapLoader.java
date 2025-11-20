package main.java.tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import main.java.game.GamePanel;

public class MapLoader {

    GamePanel gp;

    public Map<String, main.java.tile.Map> layouts = new HashMap<>();


    public MapLoader(GamePanel gp) {
        this.gp = gp;
    }

    public main.java.tile.Map loadMap(String filePath) {

        int[][] tiles = parseLayout(filePath);

        return new main.java.tile.Map(tiles, null, null, gp);
        
    }

    public int[][] parseLayout(String filePath) {
        
        int[][] layout = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        try {
            
            InputStream is = getClass().getResourceAsStream("/main/res/maps/" + filePath + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    layout[col][row] = num;
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

        return layout;
    }

    
}
