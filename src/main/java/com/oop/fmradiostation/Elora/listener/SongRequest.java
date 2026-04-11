package com.oop.fmradiostation.Elora.listener;

public class SongRequest {
    private final String requestId;
    private final String listenerName;
    private final String songName;
    private final String message;
    private final String submittedAt;

    public SongRequest(String requestId, String listenerName, String songName, String message, String submittedAt) {
        this.requestId = requestId;
        this.listenerName = listenerName;
        this.songName = songName;
        this.message = message;
        this.submittedAt = submittedAt;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getListenerName() {
        return listenerName;
    }

    public String getSongName() {
        return songName;
    }

    public String getMessage() {
        return message;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }
}

