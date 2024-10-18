package musica;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Música{
//Atributos que serão usados
    Clip clip;
    URL soundURL[] = new URL[10];

//Criação do construtor    
    public Música(){
        soundURL[0] = getClass().getResource("/sons/BennyHill.wav");
        soundURL[1] = getClass().getResource("/sons/LifeIsHighway.wav");
        soundURL[2] = getClass().getResource("/sons/CarIdle.wav");
        soundURL[3] = getClass().getResource("/sons/Aceleração.wav");
        soundURL[4] = getClass().getResource("/sons/Batida.wav");
        soundURL[5] = getClass().getResource("/sons/sound1.wav");
    }

//Função que carrega o arquivo que será usado na música    
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        }catch(Exception e){
            
        }
    }

//Função para tocar a música    
    public void play(){
        clip.start();
    }

//Função que dá um loop na música    
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

//Função que para a música    
    public void stop(){
        clip.stop();
    }
}
