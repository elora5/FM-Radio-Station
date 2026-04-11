package com.oop.fmradiostation.Sabbir.production_manager;

public class ProgramSegment {
    private String segmentName;
    private String startTime;
    private String endTime;

    public ProgramSegment(String startTime, String segmentName, String endTime) {
        this.startTime = startTime;
        this.segmentName = segmentName;
        this.endTime = endTime;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
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

    @Override
    public String toString() {
        return "ProgramSegment{" +
                "segmentName='" + segmentName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
