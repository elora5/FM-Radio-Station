package com.oop.fmradiostation.Sabbir.production_manager;

public class Studio {
    private String studioId;
    private String studioName;
    private String location;

    public Studio(String studioId, String studioName, String location) {
        this.studioId = studioId;
        this.studioName = studioName;
        this.location = location;
    }

    public String getStudioId() {
        return studioId;
    }

    public void setStudioId(String studioId) {
        this.studioId = studioId;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioId='" + studioId + '\'' +
                ", studioName='" + studioName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
