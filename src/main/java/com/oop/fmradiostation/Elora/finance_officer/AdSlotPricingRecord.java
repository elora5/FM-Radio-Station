package com.oop.fmradiostation.Elora.finance_officer;

import java.time.LocalDate;

public class AdSlotPricingRecord {
    private final String slotId;
    private final String slotType;
    private final String timeSlot;
    private final int durationSeconds;
    private final double basePrice;
    private final LocalDate effectiveDate;
    private final String status;
    private final String note;

    public AdSlotPricingRecord(String slotId,
                               String slotType,
                               String timeSlot,
                               int durationSeconds,
                               double basePrice,
                               LocalDate effectiveDate,
                               String status,
                               String note) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.timeSlot = timeSlot;
        this.durationSeconds = durationSeconds;
        this.basePrice = basePrice;
        this.effectiveDate = effectiveDate;
        this.status = status;
        this.note = note;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getSlotType() {
        return slotType;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }
}

