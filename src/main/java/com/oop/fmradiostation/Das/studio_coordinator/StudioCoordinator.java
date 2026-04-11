package com.oop.fmradiostation.Das.studio_coordinator;

import java.io.Serializable;

public class StudioCoordinator extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public StudioCoordinator(String userId, String password, String name) {
        super(userId, password, name, "Studio Coordinator");
    }
}
