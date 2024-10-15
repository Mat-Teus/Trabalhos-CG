package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame{
//Criando o conteiner da janela
    JFrame janela = new JFrame();
    
//Construtor da janela
    public Janela(){
    //Configuração dos padrões da janela
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Jogo de corrida");
    
    //Criação do game panel
        GamePanel gamePanel = new GamePanel();
    //Adicionando o game panel na janela
        janela.add(gamePanel);        
        janela.pack();
        
    //Deixando a janela visível e a colocando no meio da tela    
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        
    //Dando ínicio ao jogo
        gamePanel.startGameThread();    
    }
}
