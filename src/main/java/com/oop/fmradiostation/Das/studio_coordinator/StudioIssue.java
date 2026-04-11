package com.oop.fmradiostation.Das.studio_coordinator;

import java.io.Serializable;
import java.time.LocalDate;

public class StudioIssue implements Serializable {
    private static final long serialVersionUID = 1L;

    private String issueId;
    private String relatedStudio;
    private String issueDescription;
    private LocalDate reportedDate;
    private String status; // "Pending", "Resolved"

    public StudioIssue(String issueId, String relatedStudio, String issueDescription) {
        this.issueId = issueId;
        this.relatedStudio = relatedStudio;
        this.issueDescription = issueDescription;
        this.reportedDate = LocalDate.now();
        this.status = "Pending";
    }

    public String getIssueId() { return issueId; }
    public String getRelatedStudio() { return relatedStudio; }
    public String getIssueDescription() { return issueDescription; }
    public LocalDate getReportedDate() { return reportedDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
