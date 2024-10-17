package musica;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Música {
    Clip clip;
    URL soundURL[] = new URL[10];
    
    public Música(){
        soundURL[0] = getClass().getResource("/sons/BennyHill.wav");
        soundURL[1] = getClass().getResource("/sons/LifeIsHighway.wav");
        soundURL[2] = getClass().getResource("/sons/CarIdle.wav");
        soundURL[3] = getClass().getResource("/sons/Aceleração.wav");
        soundURL[4] = getClass().getResource("/sons/Batida.wav");
        soundURL[5] = getClass().getResource("/sons/sound1.wav");
    }
    
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        }catch(Exception e){
            
        }
    }
    
    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
}
