package com.oop.fmradiostation.Elora.listener;

public class PollsOrContestSubmission {
    private final String submissionId;
    private final String title;
    private final String description;
    private final String response;
    private final String submittedAt;

    public PollsOrContestSubmission(String submissionId, String title, String description, String response, String submittedAt) {
        this.submissionId = submissionId;
        this.title = title;
        this.description = description;
        this.response = response;
        this.submittedAt = submittedAt;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getResponse() {
        return response;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }
}

