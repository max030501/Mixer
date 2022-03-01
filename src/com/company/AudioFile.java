package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudioFile {
    private int length;
    private File audio;
    private String filename;
    private AudioInputStream inputStream;

    AudioFile(String name) throws IOException, UnsupportedAudioFileException {
        audio = new File(name);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        length = (int) ((frames + 0.0) / format.getFrameRate());
        filename = audio.getName().replace(".wav","");
        inputStream = AudioSystem.getAudioInputStream(audio);
    }

    public String getFilename(){
        return filename;
    }
    public int getLength() {
        return length;
    }

    public void crop(int start, int end) throws Exception {
        int duration = end - start;
        if(start >= 0 && end <= length && duration > 0) {
            try {
                AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(audio);
                AudioFormat format = fileFormat.getFormat();
                inputStream = AudioSystem.getAudioInputStream(audio);
                int bytesPerSecond = format.getFrameSize() * (int) format.getFrameRate();
                inputStream.skip(start * bytesPerSecond);
                long framesOfAudioToCopy = duration * (int) format.getFrameRate();
                inputStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
                length = duration;
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            throw new Exception("incorrect input");
        }

    }
    public static void concat(AudioFile ... audioFiles){
        try {
            long length = 0;
            String name = "";
            AudioInputStream clip = null;
            List<AudioInputStream> list = new ArrayList<AudioInputStream>();

            for (AudioFile file:audioFiles ) {
                clip = file.inputStream;
                list.add(clip);
                length += clip.getFrameLength();
                name+=file.filename+"-";
            }
            if(length>0 && list.size()>0 && clip!=null) {
                name += "mix.wav";
                AudioInputStream appendedFiles =
                        new AudioInputStream(
                                new SequenceInputStream(Collections.enumeration(list)),
                                clip.getFormat(),
                                length);
                AudioSystem.write(appendedFiles,
                        AudioFileFormat.Type.WAVE,
                        new File(name));
                appendedFiles.close();
            }
            for (AudioInputStream elem : list){
                elem.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
