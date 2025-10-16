package main;

import entity.Entity;
import java.awt.Rectangle;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Calculates the current world bounds of an entity
     */
    private Rectangle calculateEntityBounds(Entity entity) {
        return new Rectangle(
            (int)entity.worldX + entity.solidArea.x,
            (int)entity.worldY + entity.solidArea.y,
            entity.solidArea.width,
            entity.solidArea.height
        );
    }

    /**
     * Calculates where the entity bounds will be after moving
     */
    private Rectangle calculateProjectedBounds(Entity entity, Rectangle currentBounds) {
        Rectangle projected = new Rectangle(currentBounds);
        int speed = (int)entity.speed;
        
        if (entity.up) projected.y -= speed;
        if (entity.down) projected.y += speed;
        if (entity.left) projected.x -= speed;
        if (entity.right) projected.x += speed;
        
        return projected;
    }

    private CollisionTiles findCollisionTiles(Entity entity, Rectangle bounds) {
        if (entity.up) {
            return new CollisionTiles(bounds.x/gp.tileSize, bounds.y/gp.tileSize, 
                                        (bounds.x+bounds.width-1)/gp.tileSize, bounds.y/gp.tileSize);
        }
        if (entity.down) {
            return new CollisionTiles(bounds.x/gp.tileSize, (bounds.y+bounds.height-1)/gp.tileSize, 
                                        (bounds.x+bounds.width-1)/gp.tileSize, (bounds.y+bounds.height-1)/gp.tileSize);
        }
        if (entity.right) {
            return new CollisionTiles((bounds.x+bounds.width-1)/gp.tileSize, bounds.y/gp.tileSize, 
                                        (bounds.x+bounds.width-1)/gp.tileSize, (bounds.y+bounds.height-1)/gp.tileSize);
        }
        if (entity.left) {
            return new CollisionTiles(bounds.x/gp.tileSize, bounds.y/gp.tileSize, 
                                        bounds.x/gp.tileSize, (bounds.y+bounds.height-1)/gp.tileSize);
        }
        else throw new IllegalArgumentException("Invalid direction");
    }

    /**
     * Checks if a tile at given coordinates is solid
     */
    private boolean isTileSolid(int row, int col) {
        int tileNum = gp.tileM.mapTileNum[col][row];
        return gp.tileM.tile[tileNum].collision;
    }

    public void checkTileCollision(Entity entity) {

        Rectangle entityBounds = calculateEntityBounds(entity);
        Rectangle projectedBounds = calculateProjectedBounds(entity, entityBounds);
        
        CollisionTiles tiles = findCollisionTiles(entity, projectedBounds);

        System.out.println(isTileSolid(tiles.row1, tiles.col1));
        System.out.println(isTileSolid(tiles.row2, tiles.col2));

        if (isTileSolid(tiles.row1, tiles.col1) || isTileSolid(tiles.row2, tiles.col2)) {
            entity.collisionOn = true;
        }
        

    }

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
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }
                    
                if (entity.down) {
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }
                    
                if (entity.left) {
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if (player == true) {
                            index = i;
                        }
                    }
                }

                if (entity.right) {
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if (gp.obj[i].collision == true) {
                            entity.collisionOn = true;
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

    private static class CollisionTiles {
        final int col1, row1, col2, row2;
        public CollisionTiles(int col1, int row1, int col2, int row2) {
            this.col1 = col1;
            this.row1 = row1;
            this.col2 = col2;
            this.row2 = row2;
        }
    }

}
