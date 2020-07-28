# Simple Java Microphone API
An easy to use project I made to help with a few other projects that require
simple audio functions. The artifact is not published anywhere yet.

## Usage
### Initialize

` record = new RecordBlob(Record.Formats.DEFAULT, AudioFileFormat.Type.WAVE);`

### Record
```
record.startRecord();
try {
    Thread.sleep(500);
} catch (InterruptedException e) {
    e.printStackTrace();
}
record.stopRecord();
```

### Play
```
record.play();
Thread.sleep(500);
```

### Saving Files
```
File file = new File("test.wav");
record.saveFile(Paths.get(file.getPath())); //Path or
```