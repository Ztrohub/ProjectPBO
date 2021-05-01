package project.pbo.gfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioLoader {

    public static AudioInputStream loadAudio(String path) {

        try {
            return AudioSystem.getAudioInputStream(new File(path));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
