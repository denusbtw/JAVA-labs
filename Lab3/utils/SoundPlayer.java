package src.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class SoundPlayer {
        public static void playSound(String soundFilePath) {
            try {
                File soundFile = new File(soundFilePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();

                clip.open(audioStream);
                clip.start();

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