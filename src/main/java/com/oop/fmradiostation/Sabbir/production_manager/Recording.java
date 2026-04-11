package com.oop.fmradiostation.Sabbir.production_manager;

public class Recording {
    private String sessionId;
    private String programName;
    private String date;
    private int duration;
    private String status;

    public Recording(String sessionId, String programName, String date, int duration, String status) {
        this.sessionId = sessionId;
        this.programName = programName;
        this.date = date;
        this.duration = duration;
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "sessionId='" + sessionId + '\'' +
                ", programName='" + programName + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
