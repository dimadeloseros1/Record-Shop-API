package com.northcoders.RecordShopApi.Controller;

import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/albums")
@CacheConfig(cacheNames = "AlbumsCache")
@Slf4j
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        log.info("Getting album with id {} from db", id);
        Optional<Album> albumFound = albumService.getAlbumById(id);

        if (albumFound.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(albumFound.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Album> postAlbum(@RequestBody Album album) {
        Album album1 = albumService.insertAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("albums", "/api/v1/albums/" + album1.getId().toString());
        return new ResponseEntity<>(album1, httpHeaders, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Optional<Album> updateAlbums = albumService.updateAlbum(id, album);

        if (updateAlbums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("albums", "/api/v1/albums" + updateAlbums.get().getId().toString());
        return new ResponseEntity<>(updateAlbums.get(), httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {

        Optional<Album> deleteAlbum = albumService.deleteAlbum(id);

        if (deleteAlbum.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.remove("album");
        return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
    }
}
