package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PostWallType {

    USER("Your posts"),
    FRIENDS("Your friends posts"),
    NETWORK("Your network posts");

    private String title;

    PostWallType(String title) {
        this.title = title;
    }

    @JsonValue
    public String getTitle() {
        return title;
    }
}
