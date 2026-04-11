package com.oop.fmradiostation.Sabbir.radio_jockey;

public class ShowHistory {
    private String programName;
    private String date;

    public ShowHistory(String programName, String date) {
        this.programName = programName;
        this.date = date;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShowHistory{" +
                "programName='" + programName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
