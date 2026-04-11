package com.oop.fmradiostation.Das.studio_coordinator;

import java.io.Serializable;

public class Studio implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studioId;
    private String studioName;
    private String currentStatus; // "Available", "In Use", "Maintenance", "Closed"
    private String assignedProgram;
    private boolean isTechnicallyReady; // Used for Goal-3: Coordinate with Sound Engineer

    public Studio(String studioId, String studioName) {
        this.studioId = studioId;
        this.studioName = studioName;
        this.currentStatus = "Available";
        this.assignedProgram = "None";
        this.isTechnicallyReady = false;
    }

    public String getStudioId() { return studioId; }
    public String getStudioName() { return studioName; }
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
    public String getAssignedProgram() { return assignedProgram; }
    public void setAssignedProgram(String assignedProgram) { this.assignedProgram = assignedProgram; }
    public boolean isTechnicallyReady() { return isTechnicallyReady; }
    public void setTechnicallyReady(boolean technicallyReady) { isTechnicallyReady = technicallyReady; }

    @Override
    public String toString() {
        return studioName + " - " + currentStatus;
    }
}