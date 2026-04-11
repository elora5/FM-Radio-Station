package com.oop.fmradiostation.Sabbir.radio_jockey;

public class MusicControl {
    private String currentSong;
    private String status;

    public MusicControl(String currentSong, String status) {
        this.currentSong = currentSong;
        this.status = status;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MusicControl{" +
                "currentSong='" + currentSong + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
