package com.oop.fmradiostation.Das.studio_coordinator;

import java.io.Serializable;
import java.time.LocalDate;

public class StudioSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scheduleId;
    private Studio assignedStudio;
    private Program scheduledProgram;
    private LocalDate scheduleDate;

    public StudioSchedule(String scheduleId, Studio assignedStudio, Program scheduledProgram, LocalDate scheduleDate) {
        this.scheduleId = scheduleId;
        this.assignedStudio = assignedStudio;
        this.scheduledProgram = scheduledProgram;
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleId() { return scheduleId; }
    public Studio getAssignedStudio() { return assignedStudio; }
    public Program getScheduledProgram() { return scheduledProgram; }
    public LocalDate getScheduleDate() { return scheduleDate; }

    // Changing the studio if there's a conflict or transition
    public void setAssignedStudio(Studio assignedStudio) {
        this.assignedStudio = assignedStudio;
    }
}
