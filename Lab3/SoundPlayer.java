package Lab3;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class SoundPlayer {

    // Method to play a sound from a given file path
    public void playSound(String soundFilePath) {
        try {
            // Get the audio input stream from the file
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio stream into the clip and start playing it
            clip.open(audioStream);
            clip.start();

            // Optional: Wait until the sound finishes playing (in a separate thread)
            new Thread(() -> {
                try {
                    Thread.sleep(clip.getMicrosecondLength() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
