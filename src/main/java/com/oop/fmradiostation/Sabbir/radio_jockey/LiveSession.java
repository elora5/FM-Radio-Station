package com.oop.fmradiostation.Sabbir.radio_jockey;

public class LiveSession {
    private String sessionId;
    private String programName;
    private String status;

    public LiveSession(String sessionId, String programName, String status) {
        this.sessionId = sessionId;
        this.programName = programName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LiveSession{" +
                "sessionId='" + sessionId + '\'' +
                ", programName='" + programName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
