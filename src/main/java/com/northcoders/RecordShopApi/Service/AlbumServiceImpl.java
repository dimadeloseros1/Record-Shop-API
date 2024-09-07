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

    @Override
    public Album insertAlbum(Album album) {
        return albumGenreRepository.save(album);
    }

    @Override
    public Optional<Album> updateAlbum(Long id, Album album) {
        Optional<Album> updateAlbum = albumGenreRepository.findById(id);

        if (updateAlbum.isPresent()) {
            updateAlbum.get().setAlbumName(album.getAlbumName());
            updateAlbum.get().setStock(album.getStock());
            updateAlbum.get().setArtist(album.getArtist());
            updateAlbum.get().setGenre(album.getGenre());
            updateAlbum.get().setReleaseYear(album.getReleaseYear());
            albumGenreRepository.save(updateAlbum.get());
        }

        return updateAlbum;
    }

    @Override
    public Optional<Album> deleteAlbum(Long id) {
        Optional<Album> album = albumGenreRepository.findById(id);

        if (album.isPresent()) {
            albumGenreRepository.deleteById(album.get().getId());
        }

        return album;
    }


}
