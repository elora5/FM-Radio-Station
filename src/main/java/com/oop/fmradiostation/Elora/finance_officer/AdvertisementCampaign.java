package com.oop.fmradiostation.Elora.finance_officer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementCampaign {
    private final String advertisementId;
    private final String companyName;
    private final String advertisementTitle;
    private final int durationSeconds;
    private final double budget;
    private final String adType;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String timeSlot;
    private final String status;
    private final List<String> broadcastDays;
    private final String notes;

    public AdvertisementCampaign(String advertisementId,
                                 String companyName,
                                 String advertisementTitle,
                                 int durationSeconds,
                                 double budget,
                                 String adType,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 String timeSlot,
                                 String status,
                                 List<String> broadcastDays,
                                 String notes) {
        this.advertisementId = advertisementId;
        this.companyName = companyName;
        this.advertisementTitle = advertisementTitle;
        this.durationSeconds = durationSeconds;
        this.budget = budget;
        this.adType = adType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.broadcastDays = new ArrayList<>(broadcastDays);
        this.notes = notes;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public double getBudget() {
        return budget;
    }

    public String getAdType() {
        return adType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getBroadcastDays() {
        return new ArrayList<>(broadcastDays);
    }

    public String getNotes() {
        return notes;
    }
}

