package com.oop.fmradiostation.Das.sound_engineer;

import java.io.Serializable;

public class AudioDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String deviceName;
    private String deviceType; // "Microphone", "Mixer", "Output Channel"
    private double currentVolume;
    private boolean isFunctioningProperly; // Used for Equipment Test goal

    public AudioDevice(String deviceId, String deviceName, String deviceType) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.currentVolume = 50.0;
        this.isFunctioningProperly = true;
    }

    public String getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getDeviceType() { return deviceType; }
    public double getCurrentVolume() { return currentVolume; }
    public void setCurrentVolume(double currentVolume) { this.currentVolume = currentVolume; }
    public boolean isFunctioningProperly() { return isFunctioningProperly; }
    public void setFunctioningProperly(boolean functioningProperly) { isFunctioningProperly = functioningProperly; }

    @Override
    public String toString() {
        return deviceName + " (" + deviceType + ") - Status: " + (isFunctioningProperly ? "OK" : "Error");
    }
}