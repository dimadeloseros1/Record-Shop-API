package com.northcoders.RecordShopApi.Model;

public enum Genre {
    TRANCE("Trance"),
    HOUSE("House"),
    ROCK("Rock"),
    HIPHOP("Hip Hop"),
    POP("Pop"),
    METAL("Metal"),
    DNB("Drum & Bass");

    public final String message;

    Genre(String message) {
        this.message = message;
    }
}
