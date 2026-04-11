package com.oop.fmradiostation.Sabbir.production_manager;

public class Program {
    private String programId;
    private String programName;
    private String category;
    private String rjName;
    private String startTime;
    private String endTime;
    private boolean isLive;

    public Program(String programId, String programName, String category, String rjName, String startTime, String endTime, boolean isLive) {
        this.programId = programId;
        this.programName = programName;
        this.category = category;
        this.rjName = rjName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isLive = isLive;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRjName() {
        return rjName;
    }

    public void setRjName(String rjName) {
        this.rjName = rjName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", category='" + category + '\'' +
                ", rjName='" + rjName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isLive=" + isLive +
                '}';
    }
}
