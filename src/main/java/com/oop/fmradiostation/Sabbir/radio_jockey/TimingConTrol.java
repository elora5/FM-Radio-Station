package com.oop.fmradiostation.Sabbir.radio_jockey;

public class TimingConTrol {
    private String programName;
    private String currentTime;

    public TimingConTrol(String programName, String currentTime) {
        this.programName = programName;
        this.currentTime = currentTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "TimingConTrol{" +
                "programName='" + programName + '\'' +
                ", currentTime='" + currentTime + '\'' +
                '}';
    }
}
