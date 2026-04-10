package com.oop.fmradiostation.Elora.listener;

public class BroadcastInfo {
    private final String currentProgram;
    private final String rjName;
    private final String broadcastStatus;
    private final String message;

    public BroadcastInfo(String currentProgram, String rjName, String broadcastStatus, String message) {
        this.currentProgram = currentProgram;
        this.rjName = rjName;
        this.broadcastStatus = broadcastStatus;
        this.message = message;
    }

    public String getCurrentProgram() {
        return currentProgram;
    }

    public String getRjName() {
        return rjName;
    }

    public String getBroadcastStatus() {
        return broadcastStatus;
    }

    public String getMessage() {
        return message;
    }
}

