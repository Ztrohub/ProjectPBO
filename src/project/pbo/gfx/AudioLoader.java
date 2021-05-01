package project.pbo.gfx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioLoader {

    public static Clip loadAudio(String path) {

        AudioInputStream ais = null;

        try {
            ais = AudioSystem.getAudioInputStream(new File(path));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert clip != null;
            clip.open(ais);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        return clip;
    }
}
