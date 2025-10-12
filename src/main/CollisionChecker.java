package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        
        int entityLeftWorldX = (int) entity.worldX + entity.solidArea.x;
        int entityRightWorldX = (int) entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = (int) entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = (int) entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        // two possible collision tiles set based on the direction of movement
        int tileNum1, tilenum2;

        if (entity.up) {
            entityTopRow = (entityTopWorldY - (int) entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.down) {
            entityBottomRow = (entityBottomWorldY + (int) entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.right) {
            entityRightCol = (entityRightWorldX + (int) entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collisionOn = true;
            }
        }
        if (entity.left) {
            entityLeftCol = (entityLeftWorldX - (int) entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collisionOn = true;
            }
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

}
