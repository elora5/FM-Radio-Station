package com.oop.fmradiostation.Sabbir.radio_jockey;

public class RadioJockeyDashboardscenecontroller {
    private String rjId;
    private String rjName;
    private String currentProgram;
    private String currentSong;
    private String showStatus;
    private String shift;
    private int totalShows;
    private int totalSongsPlayed;
    private int totalListeners;
    private int totalRequests;
    private String timingStatus;

    public RadioJockeyDashboardscenecontroller(int totalListeners, String rjId, String rjName, String currentProgram, String currentSong, String showStatus, String shift, int totalShows, int totalSongsPlayed, int totalRequests, String timingStatus) {
        this.totalListeners = totalListeners;
        this.rjId = rjId;
        this.rjName = rjName;
        this.currentProgram = currentProgram;
        this.currentSong = currentSong;
        this.showStatus = showStatus;
        this.shift = shift;
        this.totalShows = totalShows;
        this.totalSongsPlayed = totalSongsPlayed;
        this.totalRequests = totalRequests;
        this.timingStatus = timingStatus;
    }

    public String getRjId() {
        return rjId;
    }

    public void setRjId(String rjId) {
        this.rjId = rjId;
    }

    public String getRjName() {
        return rjName;
    }

    public void setRjName(String rjName) {
        this.rjName = rjName;
    }

    public String getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(String currentProgram) {
        this.currentProgram = currentProgram;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getTotalShows() {
        return totalShows;
    }

    public void setTotalShows(int totalShows) {
        this.totalShows = totalShows;
    }

    public int getTotalSongsPlayed() {
        return totalSongsPlayed;
    }

    public void setTotalSongsPlayed(int totalSongsPlayed) {
        this.totalSongsPlayed = totalSongsPlayed;
    }

    public int getTotalListeners() {
        return totalListeners;
    }

    public void setTotalListeners(int totalListeners) {
        this.totalListeners = totalListeners;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public String getTimingStatus() {
        return timingStatus;
    }

    public void setTimingStatus(String timingStatus) {
        this.timingStatus = timingStatus;
    }

    @Override
    public String toString() {
        return "RadioJockeyDashboardscenecontroller{" +
                "rjId='" + rjId + '\'' +
                ", rjName='" + rjName + '\'' +
                ", currentProgram='" + currentProgram + '\'' +
                ", currentSong='" + currentSong + '\'' +
                ", showStatus='" + showStatus + '\'' +
                ", shift='" + shift + '\'' +
                ", totalShows=" + totalShows +
                ", totalSongsPlayed=" + totalSongsPlayed +
                ", totalListeners=" + totalListeners +
                ", totalRequests=" + totalRequests +
                ", timingStatus='" + timingStatus + '\'' +
                '}';
    }
}
