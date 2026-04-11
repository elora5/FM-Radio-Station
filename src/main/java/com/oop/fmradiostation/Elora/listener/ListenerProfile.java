package com.oop.fmradiostation.Elora.listener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListenerProfile {
    private final String profileId;
    private final String fullName;
    private final String email;
    private final String phone;
    private final LocalDate dob;
    private final String gender;
    private final String address;
    private final String favoriteGenre;
    private final String listenerType;
    private final List<String> interests;

    public ListenerProfile(String profileId,
                           String fullName,
                           String email,
                           String phone,
                           LocalDate dob,
                           String gender,
                           String address,
                           String favoriteGenre,
                           String listenerType,
                           List<String> interests) {
        this.profileId = profileId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.favoriteGenre = favoriteGenre;
        this.listenerType = listenerType;
        this.interests = new ArrayList<>(interests);
    }

    public String getProfileId() {
        return profileId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public String getListenerType() {
        return listenerType;
    }

    public List<String> getInterests() {
        return new ArrayList<>(interests);
    }
}

