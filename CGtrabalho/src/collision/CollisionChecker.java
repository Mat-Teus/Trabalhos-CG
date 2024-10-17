package collision;

import entity.Entity;
import entity.Player;
import entity.Player2;
import musica.Música;
import tile.TileManager;
import view.GamePanel;

public class CollisionChecker {

    private GamePanel gp;
    private TileManager tileM;
    private Player player;
    public Música musica = new Música();
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(Player entity) throws InterruptedException{
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileTamanho;
        int entityRightCol = entityRightWorldX / gp.tileTamanho;
        int entityTopRow = entityTopWorldY / gp.tileTamanho;
        int entityBottomRow = entityBottomWorldY / gp.tileTamanho;
        
        int tileNum1, tileNum2;
        
        
        
        switch (entity.direction) {
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileTamanho;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileTamanho;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileTamanho;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileTamanho;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }else if(gp.tileM.tile[tileNum1].linhachegada || gp.tileM.tile[tileNum2].linhachegada){
                    entity.voltas++;
                    if(entity.voltas == 20){
                        gp.gameState = 4;
                    }
            }
            break;
        }
    }
    
    public void checkTile2(Player2 entity) throws InterruptedException{
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX / gp.tileTamanho;
        int entityRightCol = entityRightWorldX / gp.tileTamanho;
        int entityTopRow = entityTopWorldY / gp.tileTamanho;
        int entityBottomRow = entityBottomWorldY / gp.tileTamanho;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileTamanho;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileTamanho;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                } 
                
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileTamanho;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileTamanho;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }else if(gp.tileM.tile[tileNum1].linhachegada || gp.tileM.tile[tileNum2].linhachegada){
                    entity.voltas++;
                    if(entity.voltas == 20){
                        gp.gameState = 5;
                    }
                } 
                break;
        }
    }
}

