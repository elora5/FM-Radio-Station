package com.oop.fmradiostation.Elora.listener;

public class Flight {
    private  String operatedBy;
    private String from;
    private String to;
    private String depart;
    private String arrive;
    private String checkIn;
    private String baseFare;
    private String tax;

    //
    // Constructor, getters, and setters...

    public Flight(String from, String to, String depart, String arrive, String checkIn, String baseFare, String tax,String operatedBy) {
        this.from = from;
        this.to = to;
        this.depart = depart;
        this.arrive = arrive;
        this.checkIn = checkIn;
        this.baseFare = baseFare;
        this.tax = tax;
        this.operatedBy = operatedBy;
    }
    public Flight() {
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getOperatedBy() {
        return operatedBy;
    }

    public void setOperatedBy(String operatedBy) {
        this.operatedBy = operatedBy;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "operatedBy='" + operatedBy + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", depart='" + depart + '\'' +
                ", arrive='" + arrive + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", baseFare='" + baseFare + '\'' +
                ", tax='" + tax + '\'' +
                '}';
    }
}