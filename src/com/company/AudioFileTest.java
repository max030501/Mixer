package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AudioFileTest {
    private AudioFile af;

    @Before
    public void setUp() throws UnsupportedAudioFileException, IOException {
        af = new AudioFile("test1.wav");
    }

    @Test
    public void timeDuration() {
        Assert.assertEquals(af.getLength(),130);
    }

    @Test
    public void cropAudio() throws Exception {
        af.crop(10,15);
        AudioFile naf = new AudioFile("test1_crop.wav");
        Assert.assertEquals(naf.getLength(),5);
    }

    @Test(expected = Exception.class)
    public void cropAudio_incorrectInput() throws Exception {
        af.crop(10,10000);
    }
}