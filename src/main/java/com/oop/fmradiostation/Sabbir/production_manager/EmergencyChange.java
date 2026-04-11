package com.oop.fmradiostation.Sabbir.production_manager;

public class EmergencyChange {
    private String changeId;
    private String programName;
    private String changeType;
    private String description;

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmergencyChange(String changeId, String programName, String changeType, String description) {
        this.changeId = changeId;
        this.programName = programName;
        this.changeType = changeType;
        this.description = description;

    }

    @Override
    public String toString() {
        return "EmergencyChange{" +
                "changeId='" + changeId + '\'' +
                ", programName='" + programName + '\'' +
                ", changeType='" + changeType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
