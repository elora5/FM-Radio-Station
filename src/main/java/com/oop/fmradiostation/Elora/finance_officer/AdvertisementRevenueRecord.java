package com.oop.fmradiostation.Elora.finance_officer;

public class AdvertisementRevenueRecord {
    private final String adName;
    private final String adType;
    private final double amount;
    private final String date;

    public AdvertisementRevenueRecord(String adName, String adType, double amount, String date) {
        this.adName = adName;
        this.adType = adType;
        this.amount = amount;
        this.date = date;
    }

    public String getAdName() {
        return adName;
    }

    public String getAdType() {
        return adType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

