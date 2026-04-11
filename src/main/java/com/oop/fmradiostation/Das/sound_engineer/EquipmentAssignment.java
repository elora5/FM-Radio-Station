package com.oop.fmradiostation.Das.sound_engineer;

import java.io.Serializable;
import java.time.LocalDate;

public class EquipmentAssignment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String assignmentId;
    private AudioDevice device;
    private String assignedToName; // Name of the RJ or Reporter
    private LocalDate checkoutDate;
    private String status; // "Checked Out", "Returned"

    public EquipmentAssignment(String assignmentId, AudioDevice device, String assignedToName) {
        this.assignmentId = assignmentId;
        this.device = device;
        this.assignedToName = assignedToName;
        this.checkoutDate = LocalDate.now();
        this.status = "Checked Out";
    }

    public String getAssignmentId() { return assignmentId; }
    public AudioDevice getDevice() { return device; }
    public String getAssignedToName() { return assignedToName; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
