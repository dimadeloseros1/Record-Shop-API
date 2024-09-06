package com.northcoders.RecordShopApi.Service;

import com.northcoders.RecordShopApi.Exception.GlobalExceptionHandler;
import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Repository.AlbumGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumGenreRepository albumGenreRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumGenreRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return Optional.ofNullable(albumGenreRepository.findById(id).orElseThrow(RuntimeException::new));
    }


}
