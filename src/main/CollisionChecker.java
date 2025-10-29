package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Checks if a tile at given coordinates is solid
     */
    private boolean isTileSolid(int worldX, int worldY) {
        int col = worldX / gp.tileSize;
        int row = worldY / gp.tileSize;
        
        if (col < 0 || row < 0 || 
            col >= gp.tileM.mapTileNum.length || 
            row >= gp.tileM.mapTileNum[0].length) {
            return true;
        }

        int tileNum = gp.tileM.mapTileNum[col][row];
        return gp.tileM.tile[tileNum].collision;
    }

    /**
     * Checks if the current move would result in a collision
     * @param entity
     * @param dx
     * @param dy
     * @return
     */
    private boolean wouldCollideWithTile(Entity entity, float dx, float dy) {
        int left = (int)Math.floor(entity.worldX + entity.solidArea.x + dx);
        int right = (int)Math.floor(entity.worldX + entity.solidArea.x + dx + entity.solidArea.width - 1);
        int top = (int)Math.floor(entity.worldY + entity.solidArea.y + dy);
        int bottom = (int)Math.floor(entity.worldY + entity.solidArea.y + dy + entity.solidArea.height - 1);
        
        // Check all four corners of the entity for collision
        return isTileSolid(left, top) ||
               isTileSolid(right, top) ||
               isTileSolid(left, bottom) ||
               isTileSolid(right, bottom); 
    }

    /**
     * Checks if the entity would collide in the horizontal direction
     * @param entity
     * @param dx
     * @return
     */
    public boolean checkTileCollisionX(Entity entity, float dx) {
        if (dx == 0) {
            return false;
        }
        return wouldCollideWithTile(entity, dx, 0);
    }

    /**
     * Checks if the entity would collide in the vertical direction
     * @param entity
     * @param dy
     * @return
     */
    public boolean checkTileCollisionY(Entity entity, float dy) {
        if (dy == 0) {
            return false;
        }
        return wouldCollideWithTile(entity, 0, dy);
    }

    /**
     * Checks for collision with an object
     * Work in progress
     * @param entity
     * @param player
     * @return
     */
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //Get entities solidArea position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                gp.obj[i].solidArea.x += gp.obj[i].worldX;
                gp.obj[i].solidArea.y += gp.obj[i].worldY;

                if (entity.up) {
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        System.out.println("up collision");
                        if (gp.obj[i].vertCollisionOn == true) {
                            entity.vertCollisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }
                    
                if (entity.down) {
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].vertCollisionOn == true) {
                            entity.vertCollisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }
                    
                if (entity.left) {
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].vertCollisionOn == true) {
                            entity.vertCollisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }

                if (entity.right) {
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].vertCollisionOn == true) {
                            entity.vertCollisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }
  
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
