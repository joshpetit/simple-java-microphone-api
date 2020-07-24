package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.AudioFormat;
import java.util.List;

public abstract class Microphone {
    /**
     * Use the available fields in the Formats subclass for ease of use
     * @param audioFormat - The format wanted to record in
     * @return Record object cable of recording and playing
     */
    public abstract Record createRecordingBlob(AudioFormat audioFormat);
    public abstract Record createRecordingBlob();
    public abstract List<Record> getRecordingBlobs();

    public static class Formats {
        public static AudioFormat DEFAULT = new AudioFormat
                (AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
    }
}
