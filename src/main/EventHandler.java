package main;

import java.awt.Rectangle;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {

        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

    }

    //test for now
    public void checkEvent() {
        if (hit(11, 11, "left")) {
            damagePit();
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = (int) gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = (int) gp.player.worldY + gp.player.solidArea.y;
        eventRect.x += eventCol * gp.tileSize;
        eventRect.y += eventRow * gp.tileSize;

        if (gp.player.solidArea.intersects(eventRect)) {
            
            hit = true;
            
            // if (gp.player.direction.contentEquals(reqDirection) || 
            //     reqDirection.contentEquals("left")) {
            //         
            //     }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit() {
        gp.player.life -= 1;
    }
}
