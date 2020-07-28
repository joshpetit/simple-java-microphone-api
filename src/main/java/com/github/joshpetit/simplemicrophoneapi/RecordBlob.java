package com.github.joshpetit.simplemicrophoneapi;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public class RecordBlob extends Record{
    private TargetDataLine targetDataLine;
    private final AudioFileFormat.Type fileFormat;
    private File audioFile;
    private final AudioFormat format;
    private Clip audioClip;

    /**
     *
     * @param format Formats available in Record.Format.FORMAT
     * @param fileFormat File formats available in AudioFileFormat.Type.FORMAT
     */
    public RecordBlob(AudioFormat format, AudioFileFormat.Type fileFormat) {
        this.format = format;
        this.fileFormat = fileFormat;
    }

    /**
     *
     * @param format Formats available in Record.Format.FORMAT
     */
    public RecordBlob(AudioFormat format) {
        this.format = format;
        this.fileFormat = AudioFileFormat.Type.WAVE;
    }

    public RecordBlob() {
        this.format = Record.Formats.DEFAULT;
        this.fileFormat = AudioFileFormat.Type.WAVE;
    }


    @Override
    public void startRecord() {
        try {
            targetDataLine = AudioSystem.getTargetDataLine(format);
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
        if (!audioFile.delete()) {
           System.err.println("Error Deleting Audio File: " + audioFile.getAbsolutePath());
        }
    }

    @Override
    public File getAudioFile() {
        return this.audioFile;
    }

    @Override
    public void saveFile(Path path) {
        try {
            Files.copy(Paths.get(audioFile.getPath()), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String path) {
        try {
            Files.copy(Paths.get(audioFile.getPath()), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Clip getAudioClip() {
        return this.audioClip;
    }

    @Override
    public void play() {
        if (audioClip != null && audioFile != null) {
            if (audioClip.getMicrosecondPosition() >= audioClip.getMicrosecondLength()) {
                audioClip.setMicrosecondPosition(0);
            }
            audioClip.start();
        } else {
            System.err.println("Error: Must record first");
        }
    }

    public void replay() {
        if (audioClip != null && audioFile != null) {
            audioClip.setMicrosecondPosition(0);
            audioClip.start();
        } else {
            System.err.println("Error: Must record first");
        }
    }

    public void loop() {
        if (audioClip != null && audioFile != null) {
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } else {
            System.err.println("Error: Must record first");
        }
    }

    @Override
    public void pause() {
        if (audioClip != null && audioClip.isActive()) {
          audioClip.stop();
        }
    }
}
