package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjKey extends SuperObject{

    GamePanel gp;

    public ObjKey(GamePanel gp) {
        
        this.gp = gp;
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
