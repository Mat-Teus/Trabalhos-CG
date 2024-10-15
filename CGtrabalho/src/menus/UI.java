package menus;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import view.GamePanel;

public class UI extends JPanel{
    GamePanel gp;
    
    public UI(){
        this.setPreferredSize(new Dimension(gp.telaLargura, gp.telaAltura));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       
        this.setFocusable(true);
    }
    
    public void Menu(){
        System.out.print("Uopa");
    }
}
