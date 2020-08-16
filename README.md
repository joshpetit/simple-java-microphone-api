# Simple Java Microphone API
An easy to use project I made to help with a few other projects that require
simple audio functions. Likely won't publish artifact until my use for this is 
exacerbated and I stop updating it.

## Usage
### Initialize

`RecordBlob record = new RecordBlob(Record.Formats.DEFAULT, AudioFileFormat.Type.WAVE);`

### Record
```
record.startRecord();
try {
    Thread.sleep(5000); //Records for 5 seconds
} catch (InterruptedException e) {
    e.printStackTrace();
}
record.stopRecord();
```

### Play
```
record.play();
Thread.sleep(5000); //Allows the audio to play for 5 seconds
```

### Saving Files
```
File file = new File("test.wav");
record.saveFile(file.getPath()); //Path or String argument
```

### Useful methods
```
getAudioFile();
replay();
getAudioClip();
loop();
pause();
deleteRecording();
```
