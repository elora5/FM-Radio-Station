package com.oop.fmradiostation.Das.sound_engineer;

import java.io.Serializable;

public class SoundEngineer extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public SoundEngineer(String userId, String password, String name) {
        super(userId, password, name, "Sound Engineer");
    }}
