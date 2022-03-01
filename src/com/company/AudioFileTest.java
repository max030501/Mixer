package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AudioFileTest {
    private AudioFile test1;
    private AudioFile test2;
    private AudioFile test3;


    @Before
    public void setUp() throws UnsupportedAudioFileException, IOException {
        test1 = new AudioFile("test1.wav");
        test2 = new AudioFile("test2.wav");
        test3 = new AudioFile("test3.wav");
    }

    @Test
    public void timeDuration() {
        Assert.assertEquals(test1.getLength(),130);
    }

    @Test
    public void cropAudio() throws Exception {
        test1.crop(10,15);
        AudioFile naf = new AudioFile("test1_crop.wav");
        Assert.assertEquals(naf.getLength(),5);
    }

    @Test(expected = Exception.class)
    public void cropAudio_incorrectInput() throws Exception {
        test1.crop(10,10000);
    }

    @Test
    public void concatAudio() throws UnsupportedAudioFileException, IOException {
        AudioFile.concat(test1,test2,test3);
        AudioFile con = new AudioFile("test1-test2-test3.wav");
        Assert.assertEquals(con.getLength(),488);
    }



}