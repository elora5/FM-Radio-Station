package com.oop.fmradiostation.Sabbir.radio_jockey;

public class IssueReport {
    private String issueId;
    private String issueType;
    private String solution;

    public IssueReport(String issueId, String issueType, String solution) {
        this.issueId = issueId;
        this.issueType = issueType;
        this.solution = solution;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "IssueReport{" +
                "issueId='" + issueId + '\'' +
                ", issueType='" + issueType + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
