package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import view.GamePanel;

public class TileManager {
    public GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int [gp.maxTelaColuna][gp.maxTelaLinha]; 
        getTileImage();
        loadMap("/mapa/mapa01.txt");
    }
    
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Fundo/grass.png"));
            tile[0].collision = true;
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Fundo/sand.png"));
            tile[1].collision = false;
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Fundo/quadriculada.png"));
            tile[2].collision = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            while(col < gp.maxTelaColuna && row < gp.maxTelaLinha){
                String line = br.readLine();
                while(col < gp.maxTelaColuna){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxTelaColuna){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }
    
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while(col<gp.maxTelaColuna && row < gp.maxTelaLinha){
            
            int tileNum = mapTileNum[col][row];
            
            g2.drawImage(tile[tileNum].image, x, y, gp.tileTamanho, gp.tileTamanho, null);
            col++;
            x += gp.tileTamanho;
            if(col == gp.maxTelaColuna){
                col = 0;
                x = 0;
                row++;
                y+= gp.tileTamanho;
            }
        }
             
    }
}
