package com.github.joshpetit.simplemicrophoneapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFileFormat;

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
     void testRecord() throws InterruptedException {
        record.startRecord();
        Thread.sleep(500);
        record.stopRecord();
        assertNotEquals(0, record.getAudioFile().getTotalSpace());
    }

}