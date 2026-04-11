package com.oop.fmradiostation.Sabbir.production_manager;

public class QualityReport {
    private String reportId;
    private String programName;
    private int rating;
    private String remarks;

    public QualityReport(int rating, String reportId, String programName, String remarks) {
        this.rating = rating;
        this.reportId = reportId;
        this.programName = programName;
        this.remarks = remarks;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "QualityReport{" +
                "reportId='" + reportId + '\'' +
                ", programName='" + programName + '\'' +
                ", rating=" + rating +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
