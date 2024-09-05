package com.northcoders.RecordShopApi.Service;

import com.northcoders.RecordShopApi.Controller.AlbumController;
import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Model.Genre;
import com.northcoders.RecordShopApi.Repository.AlbumGenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
}
