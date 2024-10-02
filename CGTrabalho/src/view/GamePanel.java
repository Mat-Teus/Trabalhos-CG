package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    //configurações da tela
    final int tileTamanhoOriginal = 16; //16x16 tile
    final int escala = 3;
    
    final int tileTamanho = tileTamanhoOriginal * escala; //48x48 tile
    
    //tela 4x3
    final int maxTelaColuna = 16; 
    final int maxTelaLinha = 12;
    final int telaLargura = tileTamanho * maxTelaColuna; //769 pixels
    final int telaAltura = tileTamanho * maxTelaLinha; //576 pixels
    
    //FPS
    int FPS = 60;
    
    Thread gameThread;
    
    KeyHandler tecla = new KeyHandler();
    
    //Setar local padrão do jogador
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(telaLargura, telaAltura));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
        this.addKeyListener(tecla);
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0,016 taxa de atualização
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        
        while(gameThread != null){   
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
            //Atualizar informações, posição do jogador
            update();
            //Desenhar a tela com a informação atualizada
            repaint();
            delta--;
            drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
        
       
    }
    
    public void update(){
        if(tecla.upPressed == true){
            playerY -= playerSpeed;
        } else if(tecla.downPressed == true){
            playerY += playerSpeed;
        } else if(tecla.leftPressed == true){
            playerX -= playerSpeed;
        } else if(tecla.rightPressed == true){
            playerX += playerSpeed;
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        //metodo do JPanel
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileTamanho, tileTamanho);
        g2.dispose();
    }
    
}
