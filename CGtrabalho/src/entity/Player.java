package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import view.GamePanel;
import view.KeyHandler;

public class Player extends Entity{
//Atributos para a classe
    GamePanel gp;
    KeyHandler tecla;
    public int screenX = 0;
    public int screenY = 0;
    
//Criação do construtor
    public Player(GamePanel gp, KeyHandler tecla) {
        this.gp = gp;
        this.tecla = tecla;
        setDefaultValues();
        getImagemPlayer();
        screenX = gp.telaLargura/2 - (gp.tileTamanho/2);
        screenY = gp.telaAltura/2 - (gp.tileTamanho/2);
    }

//Função para carregar os valores padrões dos atributos a serem usados
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 0;
        direction = "right";
    }

    //Função que carrega as imagens que serão usadas pelo player
    public void getImagemPlayer() {
        try{
            right = ImageIO.read(getClass().getResourceAsStream("/player/Car_Right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/Car_Left.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/player/Car_Up.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/Car_Down.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

//Função que atualiza as variaveis para gerar a movimentação
    public void update(){
        //Caso alguma tecla é pressionada, a movimentação do veiculo acontece
            if(tecla.rightPressed == true){
                direction = "right";
                if(speed < 10){
                    speed++;
                }
                x += speed;
            }else if(tecla.leftPressed == true){
                direction = "left";
                if(speed < 10){
                    speed++;
                }
                x -= speed;
            }else if(tecla.upPressed == true){
                direction = "up";
                if(speed < 10){
                    speed++;
                }
                y -= speed;
            }else if(tecla.downPressed == true){
                direction = "down";
                if(speed < 10){
                    speed++;
                }
                y += speed;
                
        //Essa parte do código diminui a velocidade do carro para 0 caso deixe de pressionar alguma tecla
            }else if(tecla.rightPressed == false){
                speed = 0;
            }else if(tecla.leftPressed == false){
                speed = 0;
            }else if(tecla.rightPressed == false){
                speed = 0;
            }else if(tecla.downPressed == false){
                speed = 0;
            }
            
            collisionOn = false;
            gp.cChecker.checkTile(this);      
    }

//Funçaõ para carregar as imagens na tela do usuário
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction){
            case "right":
                    image = right;
                break;
            case "left":
                    image = left;
                break;
            case "up":
                    image = up;
                break;
            case "down":
                    image = down;
                break;
        }

        g2.drawImage(image, x, y, gp.tileTamanho, gp.tileTamanho, null);
    }
}
