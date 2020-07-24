package com.github.joshpetit.simplemicrophoneapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFileFormat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
    Record record;
    static Microphone microphone;

    @BeforeAll
    public static void create() {
        microphone = new SimpleMicrophone();
    }

    @BeforeEach
    void init() {
//        record = microphone.createRecordingBlob(Microphone.Formats.DEFAULT);
        record = new RecordBlob(Microphone.Formats.DEFAULT, AudioFileFormat.Type.WAVE);
    }

    @DisplayName("Test Record")
    @Test
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
        record();
        File file = new File("test.wav");
        file.deleteOnExit();
        record.saveFile(Paths.get(file.getPath()));
        Files.exists(Paths.get(file.getPath()));
    }

    @DisplayName("Test Record")
    @Test
    void testRecord() {
        record();
        assertNotEquals(0, record.getAudioFile().getTotalSpace());
    }
}