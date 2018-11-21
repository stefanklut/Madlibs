package com.example.stefan.stefanklut_pset2;

import java.io.Serializable;

class StoryInfo implements Serializable {
    private String name;
    private int rawId;
    public StoryInfo(String name, int rawId) {
        this.name = name;
        this.rawId = rawId;
    }

    public String getName() {
        return name;
    }

    public int getRawId() {
        return rawId;
    }
}
