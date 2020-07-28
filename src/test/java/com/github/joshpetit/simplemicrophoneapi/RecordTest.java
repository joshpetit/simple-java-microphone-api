package com.github.joshpetit.simplemicrophoneapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
    Record record;


    @BeforeEach
    void init() {
        record = new RecordBlob(Record.Formats.DEFAULT, AudioFileFormat.Type.WAVE);
        record();
    }

     void record(){
        record.startRecord();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        record.stopRecord();
    }

    @DisplayName("Test Save")
    @Test
    void testSave() {
        File file = new File("test.wav");
        file.deleteOnExit();
        record.saveFile(file.getPath());
        Files.exists(Paths.get(file.getPath()));
    }

    @DisplayName("Test Record")
    @Test
    void testRecord() {
        assertNotEquals(0, record.getAudioFile().getTotalSpace());
    }

   @DisplayName("Test Delete")
   @Test
    void testDelete() {
        record.deleteRecording();
        assertFalse(record.getAudioFile().exists());
    }

    @DisplayName("Test Play and Pause")
    @Test
    void testPause() throws InterruptedException {
        record.play();
        Thread.sleep(100);
        record.pause();
        Thread.sleep(200);
        Clip audioClip = record.getAudioClip();
        long microsecondPosition = audioClip.getMicrosecondPosition();
        assertTrue(microsecondPosition < audioClip.getMicrosecondLength() && microsecondPosition > 0);
    }

    @DisplayName("Test Loop")
    @Test
    void testLoop() throws InterruptedException {
       record.loop();
       Thread.sleep(650);
       assertTrue(record.getAudioClip().isActive());
    }

}