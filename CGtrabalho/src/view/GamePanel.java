package view;

import collision.CollisionChecker;
import entity.Player;
import entity.Player2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import musica.Música;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
//variavel que controla as telas
    public int gameState = 1;
//Mais reboco
    public int controle = 0;
    
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
    
//Contador de pistas
    public int pista = 0;
    
    public int menuSelect = 0;
    public int menuSelect2 = 0;
    
    public boolean Confirma = false;
    
//Variaveis para composição com o game panel
    public Thread gameThread;   
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    
    public BufferedImage image;

//Dados que carregam a posição do jogador no game panel
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

//Carregando variável para criação do cenário
    public TileManager tileM = new TileManager(this);
    public TileManager tileM2 = new TileManager(this, cChecker);
    public KeyHandler tecla = new KeyHandler(this);
    public Música musica = new Música();
    public Player player = new Player(this, tecla);
    public Player2 player2 = new Player2(this, tecla);
    public int selecMus;
    

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
                drawCount = 0;
                timer = 0;
            }
        }    
    }

//Atualiza a pósição do player
    public void update(){
        try{
            player.update();
            player2.update();
        }catch (InterruptedException ex){
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//Função que desenha as coisas na telaa    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);       
        Graphics2D g2 = (Graphics2D) g; 
            if(gameState == 1){
            //Tela 1 do menu
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
                String Texto = "Jogo de corrida super legal";
                int x = telaAltura/32;
                int y = 100;
                g2.setColor(Color.red);
                //Desenha na tela o texto nas coordenadas x y
                g2.drawString(Texto, x, y);
                
                int z = 50;
                int w = tileTamanho*8;
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/menus/Emoji dando joinha"));
            }catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
                //Coloca o PNG falso na tela (FUI ENGANADO)
                g2.drawImage(image, z, w, tileTamanho*2, tileTamanho*2,null);
                
                //Coloca a palavra jogar na tela
                String text2 = "Jogar";
                g2.drawString(text2, 300, 300);
                if(menuSelect == 0){
                    g2.drawString(">", 250, 300);
                }
                
                //Coloca a palavra sair na tela
                String text3 = "Sair";              
                g2.drawString(text3, 310, 350);
                if(menuSelect == 1){
                    g2.drawString(">", 250, 350);
                }
            }else if(gameState == 2){
                //Tela 2

                g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
                String Texto = "Selecione a pista";
                int x = telaAltura/4;
                int y = 100;
                g2.setColor(Color.red);
                
                //Coloca a palavra selecione a pista na tela
                g2.drawString(Texto, x, y);
                
                //Coloca a palavra pista 1 na tela
                String text2 = "Pista 1";
                g2.drawString(text2, 300, 300);
                if(menuSelect2 == 0){
                    g2.drawString(">", 250, 300);
                }
                
                //Coloca a palavra pista 2 na tela
                String text3 = "Pista 2";              
                g2.drawString(text3,300,350);
                if(menuSelect2 == 1){
                    g2.drawString(">",250,350);
                }
                
            //Reboco
                for(int i=0; i<1000000000; i++){
                    controle++;
                }
                
            }else if(gameState == 3){
            //Código para que se apareça as imagens no game panel
                if(pista == 1){
                    tileM.draw(g2);
                    player.draw(g2);
                    player2.draw(g2);
                    
                }else if(pista == 2){
                    tileM2.draw(g2);
                    player.draw(g2);
                    player2.draw(g2);
                }
                g2.dispose();
            }else if(gameState == 4){
                //Tela de vitória player 1
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
                String Texto = "player 1 venceu a corrida";
                int x = telaAltura/16;
                int y = 300;
                g2.setColor(Color.red);
                g2.drawString(Texto, x, y);
            }else if(gameState == 5){
                //Tela de vitória player 2
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
                String Texto = "player 2 venceu a corrida";
                int x = telaAltura/16;
                int y = 300;
                g2.setColor(Color.red);
                g2.drawString(Texto, x, y);
                
            }
    }

//Funções que controlam a música
    public void playMusic(int i){
        musica.setFile(i);
        musica.play();
        musica.loop();
    }
    
    public void stopMusic(){
        musica.stop();
    }
    
    public void playSE(int i){
        musica.setFile(i);
        musica.play();
    }
}

