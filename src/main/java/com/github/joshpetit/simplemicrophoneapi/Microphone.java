package com.github.joshpetit.simplemicrophoneapi;

import java.util.List;

public interface Microphone {
    public Record createRecordingBlob();
    public List<Record> getRecordingBlobs();

}
