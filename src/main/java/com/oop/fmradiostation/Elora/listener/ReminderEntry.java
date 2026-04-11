package com.oop.fmradiostation.Elora.listener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderEntry {
    private final String reminderId;
    private final String programName;
    private final LocalDate reminderDate;
    private final String timeSlot;
    private final String repeatMode;
    private final List<String> channels;
    private final String note;
    private final boolean active;

    public ReminderEntry(String reminderId,
                         String programName,
                         LocalDate reminderDate,
                         String timeSlot,
                         String repeatMode,
                         List<String> channels,
                         String note,
                         boolean active) {
        this.reminderId = reminderId;
        this.programName = programName;
        this.reminderDate = reminderDate;
        this.timeSlot = timeSlot;
        this.repeatMode = repeatMode;
        this.channels = new ArrayList<>(channels);
        this.note = note;
        this.active = active;
    }

    public String getReminderId() {
        return reminderId;
    }

    public String getProgramName() {
        return programName;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getRepeatMode() {
        return repeatMode;
    }

    public List<String> getChannels() {
        return new ArrayList<>(channels);
    }

    public String getNote() {
        return note;
    }

    public boolean isActive() {
        return active;
    }

    public ReminderEntry withActive(boolean newActive) {
        return new ReminderEntry(reminderId, programName, reminderDate, timeSlot, repeatMode, channels, note, newActive);
    }
}

