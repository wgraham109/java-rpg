package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class ObjKey extends Entity {

    GamePanel gp;

    public ObjKey(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("objects/key");
        vertCollisionOn = true;
        horizCollisionOn = true;
        solidArea = new Rectangle(0, 0, 48, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
