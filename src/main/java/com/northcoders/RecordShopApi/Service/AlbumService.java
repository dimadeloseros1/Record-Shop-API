package com.northcoders.RecordShopApi.Service;

import com.northcoders.RecordShopApi.Model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
    Album insertAlbum(Album album);
}
