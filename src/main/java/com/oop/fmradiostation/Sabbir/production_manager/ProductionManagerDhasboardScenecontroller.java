package com.oop.fmradiostation.Sabbir.production_manager;

public class ProductionManagerDhasboardScenecontroller { private String managerId;
    private String managerName;
    private String role;
    private String shift;
    private int totalPrograms;
    private int totalSchedules;
    private int totalRJs;
    private int totalRecordings;
    private int totalFeedbacks;
    private int emergencyCount;
    private String currentStatus;

    public ProductionManagerDhasboardScenecontroller(String managerId, String managerName, String role, String shift, int totalPrograms, int totalSchedules, int totalRJs, int totalRecordings, int totalFeedbacks, int emergencyCount, String currentStatus) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.role = role;
        this.shift = shift;
        this.totalPrograms = totalPrograms;
        this.totalSchedules = totalSchedules;
        this.totalRJs = totalRJs;
        this.totalRecordings = totalRecordings;
        this.totalFeedbacks = totalFeedbacks;
        this.emergencyCount = emergencyCount;
        this.currentStatus = currentStatus;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public int getTotalPrograms() {
        return totalPrograms;
    }

    public void setTotalPrograms(int totalPrograms) {
        this.totalPrograms = totalPrograms;
    }

    public int getTotalSchedules() {
        return totalSchedules;
    }

    public void setTotalSchedules(int totalSchedules) {
        this.totalSchedules = totalSchedules;
    }

    public int getTotalRJs() {
        return totalRJs;
    }

    public void setTotalRJs(int totalRJs) {
        this.totalRJs = totalRJs;
    }

    public int getTotalRecordings() {
        return totalRecordings;
    }

    public void setTotalRecordings(int totalRecordings) {
        this.totalRecordings = totalRecordings;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public int getEmergencyCount() {
        return emergencyCount;
    }

    public void setEmergencyCount(int emergencyCount) {
        this.emergencyCount = emergencyCount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public String toString() {
        return "ProductionManagerDhasboardScenecontroller{" +
                "managerId='" + managerId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", role='" + role + '\'' +
                ", shift='" + shift + '\'' +
                ", totalPrograms=" + totalPrograms +
                ", totalSchedules=" + totalSchedules +
                ", totalRJs=" + totalRJs +
                ", totalRecordings=" + totalRecordings +
                ", totalFeedbacks=" + totalFeedbacks +
                ", emergencyCount=" + emergencyCount +
                ", currentStatus='" + currentStatus + '\'' +
                '}';
    }
}
