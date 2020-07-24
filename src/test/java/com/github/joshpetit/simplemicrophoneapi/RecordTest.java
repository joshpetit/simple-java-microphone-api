package com.github.joshpetit.simplemicrophoneapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
    Record record;
    Microphone microphone;

    @BeforeAll
    public void create() {
        microphone = new SimpleMicrophone();
    }

    @BeforeEach
    void init() {
        record = microphone.createRecordingBlob();
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