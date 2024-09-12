package com.northcoders.RecordShopApi.Service;

import com.northcoders.RecordShopApi.Controller.AlbumController;
import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Model.Genre;
import com.northcoders.RecordShopApi.Repository.AlbumGenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AlbumServiceTests {

    @InjectMocks
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @Mock
    private AlbumGenreRepository albumGenreRepository;

    @Test
    @DisplayName("GET all albums responds with the size of the list")
    public void getAllAlbumsTest() {
        List<Album> albums = new ArrayList<>(List.of(
                new Album(1L, 1, "Ferry Corsten", 1997, Genre.TRANCE, "Rank 1"),
                new Album(2L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics"),
                new Album(3L, 1, "Tiesto", 1997, Genre.TRANCE, "Gold Age Of Trance")
        ));

        when(albumGenreRepository.findAll()).thenReturn(albums);

        List<Album> actualAlbums = mockAlbumServiceImpl.getAllAlbums();

        assertNotNull(albums);
        assertEquals(3, actualAlbums.size());
        assertEquals(albums, actualAlbums);
    }

    @Test
    @DisplayName("GET album by id, returns album by specific id")
    public void getAlbumByIdTest() {
        List<Album> albums = new ArrayList<>(List.of(
                new Album(1L, 1, "Ferry Corsten", 1997, Genre.TRANCE, "Rank 1"),
                new Album(2L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics"),
                new Album(3L, 1, "Tiesto", 1997, Genre.TRANCE, "Gold Age Of Trance")
        ));

        when(albumGenreRepository.findById(2L)).thenReturn(Optional.of(albums.get(2)));

        Optional<Album> album = mockAlbumServiceImpl.getAlbumById(2L);

        assertEquals(albums.get(2).getId(), album.get().getId());
    }

    @Test
    @DisplayName("POST album, returns the appropriate album")
    public void postAlbumTest() {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");

        when(albumGenreRepository.save(album)).thenReturn(album);
        Album actualResult = mockAlbumServiceImpl.insertAlbum(album);

        assertEquals(actualResult, album);
    }

    @Test
    @DisplayName("Update album, returns appropriate updated album")
    public void updateAlbumTest() {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");
        var updatedAlbum = new Album(1L, 2, "Chicane", 1997, Genre.TRANCE, "Trance Classics");

        when(albumGenreRepository.findById(1L)).thenReturn(Optional.of(album));
        when(albumGenreRepository.save(album)).thenReturn(updatedAlbum);

        Optional<Album> actualResult = mockAlbumServiceImpl.updateAlbum(1L, updatedAlbum);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getGenre()).isEqualTo(updatedAlbum.getGenre());
        assertThat(actualResult.get().getStock()).isEqualTo(updatedAlbum.getStock());
        assertThat(actualResult.get().getArtist()).isEqualTo(updatedAlbum.getArtist());
        assertThat(actualResult.get().getReleaseYear()).isEqualTo(updatedAlbum.getReleaseYear());
        assertThat(actualResult.get().getAlbumName()).isEqualTo(updatedAlbum.getAlbumName());

        verify(albumGenreRepository, times(1)).findById(1L);
        verify(albumGenreRepository, times(1)).save(album);
    }

    @Test
    @DisplayName("Deletes album by id successfully")
    public void deleteAlbumTest() {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");

        when(albumGenreRepository.findById(1L)).thenReturn(Optional.of(album));
        var deletedAlbums = mockAlbumServiceImpl.deleteAlbum(album.getId());

        verify(albumGenreRepository, times(1)).deleteById(album.getId());
    }
}
