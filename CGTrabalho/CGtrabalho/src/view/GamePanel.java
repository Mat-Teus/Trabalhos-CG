package view;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
//configurações da tela
    public final int tileTamanhoOriginal = 16; //16x16 tile
    public final int escala = 3;    
    public final int tileTamanho = tileTamanhoOriginal * escala; //48x48 tile
    
//tela 4x3
    public final int maxTelaColuna = 16; 
    public final int maxTelaLinha = 12;
    public final int telaLargura = tileTamanho * maxTelaColuna; //769 pixels
    public final int telaAltura = tileTamanho * maxTelaLinha; //576 pixels
    
//FPS
    public int FPS = 60; 
    
//Variaveis para composição com o game panel
    Thread gameThread;   
    KeyHandler tecla = new KeyHandler();   
    Player player = new Player(this, tecla);

//Função que carrega a posição do jogador no game panel
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

//Construtor do game panel
    public GamePanel(){
        this.setPreferredSize(new Dimension(telaLargura, telaAltura));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       
        this.addKeyListener(tecla);
        this.setFocusable(true);
        
    }
 
//Função para começar a game thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
//Funçaõ do runnable para manter o loop de atualização da tela
    public void run() {      
        double drawInterval = 1000000000/FPS; //60 FPS
        double delta = 0;
        long ultimoTempo = System.nanoTime();
        long tempoAtual;
        long timer = 0;
        long drawCount = 0;
        
        while(gameThread != null){   
            tempoAtual = System.nanoTime();
            delta += (tempoAtual - ultimoTempo)/drawInterval;
            timer += (tempoAtual - ultimoTempo);
            ultimoTempo = tempoAtual;
            
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

//Atualiza a pósição do player
    public void update(){
        player.update();
    }

    
    @Override
    public void paintComponent(Graphics g){
    //Código para que se apareça as imagens no game panel
        super.paintComponent(g);       
        Graphics2D g2 = (Graphics2D) g;      
        player.draw(g2);       
        g2.dispose();
    }
}
