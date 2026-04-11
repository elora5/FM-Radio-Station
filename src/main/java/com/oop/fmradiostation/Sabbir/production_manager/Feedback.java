package com.oop.fmradiostation.Sabbir.production_manager;

public class Feedback {
    private String listenerName;
    private int rating;
    private String comment;

    public Feedback(int rating, String listenerName, String comment) {
        this.rating = rating;
        this.listenerName = listenerName;
        this.comment = comment;
    }

    public String getListenerName() {
        return listenerName;
    }

    public void setListenerName(String listenerName) {
        this.listenerName = listenerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "listenerName='" + listenerName + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
