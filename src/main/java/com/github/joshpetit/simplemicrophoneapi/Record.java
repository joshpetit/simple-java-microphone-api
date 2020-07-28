package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Record {
    private File file;
    private AudioFormat audioFormat;

    public abstract void startRecord();
    public abstract void stopRecord();
    public abstract void deleteRecording();
    public abstract File getAudioFile();
    public abstract void saveFile(Path path);
    public void saveFile(String path) {
        saveFile(Paths.get(path));
    }
    public abstract Clip getAudioClip();
    public abstract void play();
    public abstract void pause();
    public abstract void loop();
    public abstract void replay();

    public static class Formats {
        public static AudioFormat DEFAULT = new AudioFormat
                (AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

    }

}
