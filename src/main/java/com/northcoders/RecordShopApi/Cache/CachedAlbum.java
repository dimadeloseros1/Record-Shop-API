package com.northcoders.RecordShopApi.Cache;

import com.northcoders.RecordShopApi.Model.Album;
import lombok.Getter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class CachedAlbum {
    @Getter
    private final Album album;
    private final Instant insertionTime;

    public CachedAlbum(Album album) {
        this.album = album;
        this.insertionTime = Instant.now();
    }

    public Boolean hasExpired() {
        return this.insertionTime.plus(10L, ChronoUnit.MINUTES).isBefore(Instant.now());
    }
}
