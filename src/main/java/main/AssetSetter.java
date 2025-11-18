package main.java.main;

import main.java.object.ObjKey;

public class AssetSetter {

    public GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
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
