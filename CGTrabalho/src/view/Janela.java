package view;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Janela extends JFrame{
    JFrame janela = new JFrame();
    
    public Janela(){
    //Definições de janela
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Jogo de Corrida");
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        
    //Criação do GamePanel
        GamePanel gamePanel = new GamePanel();
    
    //Adição do game panel na janela
        janela.add(gamePanel);
        
        janela.pack();
    
    //Loop do game panel
        gamePanel.startGameThread();
    }
}
