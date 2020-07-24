package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class RecordBlob extends Record{
    private TargetDataLine targetDataLine;
    private AudioFileFormat.Type fileFormat;
    private File audioFile;
    private AudioFormat format;
    private Clip audioClip;

    RecordBlob(AudioFormat format, AudioFileFormat.Type fileFormat) {
        this.format = format;
        this.fileFormat = fileFormat;
    }

    @Override
    public void startRecord() {
        try {
            targetDataLine = AudioSystem.getTargetDataLine(format);
            targetDataLine.open();
            targetDataLine.start();
            targetDataLine.open();
            targetDataLine.start();
            AudioInputStream ais = new AudioInputStream(targetDataLine);
            Thread thread = new Thread(() -> {
                try {
                    audioFile = File.createTempFile(UUID.randomUUID().toString(),fileFormat.getExtension());
                    audioFile.deleteOnExit();
                    AudioSystem.write(ais, fileFormat, audioFile);
                } catch (IOException e) {
                    System.err.println("Error recording from microphone");
                    e.printStackTrace();
                }
            });
            thread.start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopRecord() {
        if (targetDataLine.isActive()) {
            targetDataLine.stop();
            targetDataLine.close();
            try {
                audioClip = AudioSystem.getClip();
                audioClip.open(AudioSystem.getAudioInputStream(audioFile));
            } catch (LineUnavailableException e) {
                System.err.println("Unable to create clip");
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                System.err.println("Audio File Not supported");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void deleteRecording() {

    }

    @Override
    public File getAudioFile() {
        return this.audioFile;
    }

    @Override
    public void saveFile(Path path) {

    }

    @Override
    public Clip getAudioClip() {
        return null;
    }

    @Override
    public void play() {
        if (audioClip == null || audioFile == null) {
            System.err.println("Error: Must record first");
            return;
        }
        audioClip.start();
    }

    @Override
    public void pause() {

    }
}
