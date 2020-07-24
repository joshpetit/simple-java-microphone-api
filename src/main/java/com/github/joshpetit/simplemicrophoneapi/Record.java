package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Path;

public abstract class Record {
    private File file;

    public abstract void startRecord();
    public abstract void stopRecord();
    public abstract void deleteRecording();
    public abstract File getAudioFile();
    public abstract void saveFile(Path path);
    public abstract Clip getAudioClip();
    public abstract void play();
    public abstract void pause();
}
