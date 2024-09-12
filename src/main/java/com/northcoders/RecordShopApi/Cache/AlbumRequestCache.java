package com.northcoders.RecordShopApi.Cache;

import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Repository.AlbumGenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;


@Component
public class AlbumRequestCache {

    Logger logger = LoggerFactory.getLogger(AlbumRequestCache.class);
    private AlbumGenreRepository albumGenreRepository;

    private HashMap<Long, CachedAlbum> cache = new HashMap<>();

    @Autowired
    public AlbumRequestCache(AlbumGenreRepository repository) {
        this.albumGenreRepository = repository;

        ArrayList<Album> firstTenAlbums = new ArrayList<>();


    }
}
