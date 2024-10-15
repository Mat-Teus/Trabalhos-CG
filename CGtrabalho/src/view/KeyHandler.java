package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed,supPressed, sdownPressed, sleftPressed, srightPressed;
    public GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(gp.gameState == 1){
            if(code == KeyEvent.VK_DOWN){
                gp.menuSelect = 1;
            }
            
            if(code == KeyEvent.VK_UP){
                gp.menuSelect = 0;
            }
            
            if(code == KeyEvent.VK_ENTER && gp.menuSelect == 1){
                System.exit(1);
            }else if(code == KeyEvent.VK_ENTER && gp.menuSelect == 0){
                gp.gameState = 2;
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

    }
}
