package com.oop.fmradiostation.Sabbir.production_manager;

public class RJAssignment {
    private String assignmentId;
    private String rjName;
    private String programName;

    public RJAssignment(String rjName, String assignmentId, String programName) {
        this.rjName = rjName;
        this.assignmentId = assignmentId;
        this.programName = programName;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getRjName() {
        return rjName;
    }

    public void setRjName(String rjName) {
        this.rjName = rjName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public String toString() {
        return "RJAssignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", rjName='" + rjName + '\'' +
                ", programName='" + programName + '\'' +
                '}';
    }
}
