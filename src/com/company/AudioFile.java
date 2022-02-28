package com.company;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioFile {
    private int length;
    private File audio;
    AudioFile(String name) throws IOException, UnsupportedAudioFileException {
        audio = new File(name);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        length = (int) ((frames+0.0) / format.getFrameRate());
    }
    public int getLength() {
        return length;
    }
}
