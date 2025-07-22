package main;

import object.ObjKey;

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
        gp.obj[1].worldX = 3 * gp.tileSize;
        gp.obj[1].worldY = 3 * gp.tileSize;

    }

}
