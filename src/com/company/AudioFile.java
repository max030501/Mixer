package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioFile {
    private int length;
    private File audio;
    private String filename;

    AudioFile(String name) throws IOException, UnsupportedAudioFileException {
        audio = new File(name);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        length = (int) ((frames + 0.0) / format.getFrameRate());
        filename = audio.getName().replace(".wav","");
    }

    public int getLength() {
        return length;
    }

    public void crop(int start, int end) throws Exception {
        int duration = end - start;
        AudioInputStream inputStream = null;
        AudioInputStream shortenedStream = null;
        try {
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(audio);
            AudioFormat format = fileFormat.getFormat();
            inputStream = AudioSystem.getAudioInputStream(audio);
            int bytesPerSecond = format.getFrameSize() * (int) format.getFrameRate();
            inputStream.skip(start * bytesPerSecond);
            long framesOfAudioToCopy = duration * (int) format.getFrameRate();
            shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
            File destinationFile = new File(filename+"_crop.wav");
            AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (inputStream != null) try {
                inputStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (shortenedStream != null) try {
                shortenedStream.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
