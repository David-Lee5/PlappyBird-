package Game;

import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;

public class Sound {
    public static void playSound(String soundFileName) {
        try {
            URL soundURL = Sound.class.getResource(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } 
    }
}
