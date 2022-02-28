package com.company;

import org.junit.Assert;
import org.junit.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AudioFileTest {
    @Test
    public void timeDuration() throws UnsupportedAudioFileException, IOException {
        AudioFile af = new AudioFile("test1.wav");
        Assert.assertEquals(af.getLength(),130);
    }
}