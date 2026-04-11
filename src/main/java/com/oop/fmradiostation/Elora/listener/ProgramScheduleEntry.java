package com.oop.fmradiostation.Elora.listener;

public class ProgramScheduleEntry {
    private final String programName;
    private final String rjName;
    private final String startTime;
    private final String endTime;
    private final String category;

    public ProgramScheduleEntry(String programName, String rjName, String startTime, String endTime, String category) {
        this.programName = programName;
        this.rjName = rjName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    public String getProgramName() {
        return programName;
    }

    public String getRjName() {
        return rjName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCategory() {
        return category;
    }
}

