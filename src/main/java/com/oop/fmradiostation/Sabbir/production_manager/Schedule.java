package com.oop.fmradiostation.Sabbir.production_manager;

public class Schedule {
    private String scheduleId;
    private String date;
    private ArrayList<Program> programList;

    public Schedule(String scheduleId, String date, ArrayList<Program> programList) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.programList = programList;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(ArrayList<Program> programList) {
        this.programList = programList;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId='" + scheduleId + '\'' +
                ", date='" + date + '\'' +
                ", programList=" + programList +
                '}';
    }
}
