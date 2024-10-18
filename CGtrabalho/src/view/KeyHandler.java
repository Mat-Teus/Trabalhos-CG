package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tile.TileManager;

public class KeyHandler implements KeyListener{
//Atributos que serão usados
    public boolean upPressed, downPressed, leftPressed, rightPressed,supPressed, sdownPressed, sleftPressed, srightPressed;
    public GamePanel gp;
   
 
//Construtor
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e){

    }

//Funções do key listener que ldeem as teclas
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        
        //Mexer a setinha
        if(gp.gameState == 1){
            if(code == KeyEvent.VK_DOWN){
                gp.menuSelect = 1;
            }
            
            if(code == KeyEvent.VK_UP){
                gp.menuSelect = 0;
            }
            
            //Selecionar opção
            if(code == KeyEvent.VK_ENTER && gp.menuSelect == 1){
                System.exit(1);
            }else if(code == KeyEvent.VK_ENTER && gp.menuSelect == 0){
                gp.gameState = 2;
                gp.playSE(5);
            }            
        } 
        
        if(gp.gameState == 2){
            //mexer a setinha
            if(code == KeyEvent.VK_DOWN){
                gp.menuSelect2 = 1;
            } 
            
            if(code == KeyEvent.VK_UP){
                gp.menuSelect2 = 0;
            }
            
            //Selecionar a opção com reboco
            if(code == KeyEvent.VK_ENTER && gp.menuSelect2 == 0 && gp.controle > 1000000000){
                gp.playSE(5);
                gp.pista = 1;
                gp.gameState = 3;
            }else if(code == KeyEvent.VK_ENTER && gp.menuSelect2 == 1 && gp.controle > 1000000000){
                gp.playSE(5);
                gp.pista = 2;
                gp.gameState = 3;
            }
        }
        
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_UP){
            supPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            sdownPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            srightPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            sleftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_UP){
            supPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            sdownPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            srightPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            sleftPressed = false;
        }

    }
}
