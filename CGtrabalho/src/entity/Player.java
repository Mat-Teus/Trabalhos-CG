package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import musica.Música;
import view.GamePanel;
import view.KeyHandler;

public class Player extends Entity{
//Atributos para a classe
    GamePanel gp;
    KeyHandler tecla;
    public int screenX = 0;
    public int screenY = 0;
    public int voltas = 0;
    public Música musica;
    
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
        solidArea = new Rectangle(8, 8, 32, 32); // Ajuste os valores conforme necessário
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
    public void update() throws InterruptedException{
        if(tecla.rightPressed || tecla.leftPressed || tecla.upPressed || tecla.downPressed) {
        // Ajustar a direção e aumentar a velocidade
        if(tecla.rightPressed) {
            direction = "right";
            if(speed < 10){
                speed++;
            }
        }else if(tecla.leftPressed) {
            direction = "left";
            if(speed<10){ 
                speed++;
            }
        }else if(tecla.upPressed){
            direction = "up";
            if(speed<10){
                speed++;
            }
        }else if(tecla.downPressed){
            direction = "down";
            if(speed<10){
                speed++;
            }
        }
        
        // Verificar colisão
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        // Movimentar se não houver colisão
        if(collisionOn == false){
            switch(direction) {
                case "right": 
                    x += speed; 
                    break;
                case "left":
                    x -= speed; 
                    break;
                case "up": 
                    y -= speed; 
                    break;
                case "down":
                    y += speed; 
                    
                    break;
            }
        }else{
            gp.playSE(4);
            speed = 0; // Reduzir a velocidade se houver colisão
        }      
    }
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
