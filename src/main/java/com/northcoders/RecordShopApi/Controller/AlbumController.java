package com.northcoders.RecordShopApi.Controller;

import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
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
}
