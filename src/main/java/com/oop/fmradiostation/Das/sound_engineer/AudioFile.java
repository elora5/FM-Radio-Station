package com.oop.fmradiostation.Das.sound_engineer;

import java.io.Serializable;

public class AudioFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileId;
    private String fileName;
    private String fileFormat; // e.g., ".mp3", ".wav"
    private double durationInMinutes;
    private boolean isReadyForBroadcast; // Sound engineer approves it

    public AudioFile(String fileId, String fileName, String fileFormat, double durationInMinutes) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.durationInMinutes = durationInMinutes;
        this.isReadyForBroadcast = false; // Default is false until reviewed
    }

    public String getFileId() { return fileId; }
    public String getFileName() { return fileName; }
    public String getFileFormat() { return fileFormat; }
    public double getDurationInMinutes() { return durationInMinutes; }
    public boolean isReadyForBroadcast() { return isReadyForBroadcast; }
    public void setReadyForBroadcast(boolean readyForBroadcast) { isReadyForBroadcast = readyForBroadcast; }

    @Override
    public String toString() {
        return fileName + fileFormat + " (" + durationInMinutes + " mins)";
    }
}