package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.AudioFormat;
import java.util.List;

public class SimpleMicrophone extends Microphone {
    @Override
    public Record createRecordingBlob(AudioFormat audioFormat) {
        return null;
    }

    @Override
    public Record createRecordingBlob() {
        return null;
    }

    @Override
    public List<Record> getRecordingBlobs() {
        return null;
    }

}
