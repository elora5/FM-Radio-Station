package com.oop.fmradiostation.Das.studio_coordinator;

import java.io.Serializable;
import java.time.LocalTime;

public class Program implements Serializable {
    private static final long serialVersionUID = 1L;

    private String programId;
    private String programName;
    private String hostName; // The Radio Jockey
    private LocalTime startTime;
    private LocalTime endTime;
    private int delayInMinutes; // Tracks delays handled in Goal 5

    public Program(String programId, String programName, String hostName, LocalTime startTime, LocalTime endTime) {
        this.programId = programId;
        this.programName = programName;
        this.hostName = hostName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.delayInMinutes = 0;
    }

    public String getProgramId() { return programId; }
    public String getProgramName() { return programName; }
    public String getHostName() { return hostName; }
    public LocalTime getStartTime() { return startTime.plusMinutes(delayInMinutes); } // Calculates delayed start
    public LocalTime getEndTime() { return endTime.plusMinutes(delayInMinutes); }     // Calculates delayed end
    public int getDelayInMinutes() { return delayInMinutes; }

    // Method for the Coordinator to add a delay (Goal 5)
    public void addDelay(int minutes) {
        this.delayInMinutes += minutes;
    }

    @Override
    public String toString() {
        return programName + " (Host: " + hostName + ")";
    }
}
