package com.oop.fmradiostation.Sabbir.radio_jockey;

public class RadioJockey {
    private String rjId;
    private String name;
    private String showName;
    private String shift;

    public RadioJockey(String showName, String rjId, String name, String shift) {
        this.showName = showName;
        this.rjId = rjId;
        this.name = name;
        this.shift = shift;
    }

    public String getRjId() {
        return rjId;
    }

    public void setRjId(String rjId) {
        this.rjId = rjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "RadioJockey{" +
                "rjId='" + rjId + '\'' +
                ", name='" + name + '\'' +
                ", showName='" + showName + '\'' +
                ", shift='" + shift + '\'' +
                '}';
    }
}
