package com.northcoders.RecordShopApi.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.RecordShopApi.Model.Album;
import com.northcoders.RecordShopApi.Model.Genre;
import com.northcoders.RecordShopApi.Service.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class AlbumControllerTests {


    @Mock
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET all albums & returns all albums")
    void getAllAlbumsTest() throws Exception {
        List<Album> albums = new ArrayList<>(List.of(
                new Album(1L, 1, "Ferry Corsten", 1997, Genre.TRANCE, "Rank 1"),
                new Album(2L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics"),
                new Album(3L, 1, "Tiesto", 1997, Genre.TRANCE, "Golden Age Of Trance")
        ));

        when(mockAlbumServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Ferry Corsten"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value("Armin Van Buuren"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].artist").value("Tiesto"));
    }

    @Test
    @DisplayName("GET album by id")
    public void getAlbumByIdTest() throws Exception {
        List<Album> albums = new ArrayList<>(List.of(
                new Album(1L, 1, "Ferry Corsten", 1997, Genre.TRANCE, "Rank 1"),
                new Album(2L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics"),
                new Album(3L, 1, "Tiesto", 1997, Genre.TRANCE, "Gold Age Of Trance"),
                new Album(4L, 1, "Tiesto", 1997, Genre.TRANCE, "Gold Age Of Trance")
        ));

//        Album album = new Album(1L, 1, "Ferry Corsten", 1997, Genre.TRANCE, "Rank 1")

        when(mockAlbumServiceImpl.getAlbumById(1L)).thenReturn(Optional.ofNullable(albums.getFirst()));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".albumName").value("Rank 1"));
    }

    @Test
    @DisplayName("GET album by id returns 404")
    public void getAlbumByIdReturn404Test() throws Exception {
        List<Album> album = new ArrayList<>();
        album.add(new Album(1L, 2, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics"));

        when(mockAlbumServiceImpl.getAlbumById(1L)).thenReturn(Optional.empty());

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/albums/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("POST album successfully & verify that the album is stored in the H2 & database")
    public void postAlbumTest() throws Exception {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");


        when(mockAlbumServiceImpl.insertAlbum(album)).thenReturn(album);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/albums")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockAlbumServiceImpl, times(1)).insertAlbum(album);
    }

    @Test
    @DisplayName("Update album by id")
    public void updateAlbumByIdTest() throws Exception {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");
        var album1 = new Album(1L, 2, "Boswell", 2003, Genre.HOUSE, "House Classics");

        when(mockAlbumServiceImpl.updateAlbum(1L, album1)).thenReturn(Optional.of(album1));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/albums/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value(2003))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Boswell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("HOUSE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value("House Classics"));

        verify(mockAlbumServiceImpl, times(1)).updateAlbum(1L, album1);

    }

    @Test
    @DisplayName("Update album, return 404 if is empty")
    public void updateAlbumByIdTest404() throws Exception {
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");

        when(mockAlbumServiceImpl.updateAlbum(1L, album)).thenReturn(Optional.empty());

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/albums/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(mockAlbumServiceImpl, times(1)).updateAlbum(1L, album);
    }

    @Test
    @DisplayName("Deleted album by id correctly")
    public void deleteAlbumByIdTest() throws Exception{
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");

        when(mockAlbumServiceImpl.deleteAlbum(album.getId())).thenReturn(Optional.of(album));

        this.mockMvcController.perform(
                MockMvcRequestBuilders.delete("/api/v1/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(mockAlbumServiceImpl, times(1)).deleteAlbum(1L);
    }

    @Test
    @DisplayName("Deleted album by id 404")
    public void deleteAlbumByIdTest404() throws Exception{
        var album = new Album(1L, 1, "Armin Van Buuren", 1999, Genre.TRANCE, "Trance Classics");

        when(mockAlbumServiceImpl.deleteAlbum(album.getId())).thenReturn(Optional.empty());

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/albums/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(mockAlbumServiceImpl, times(1)).deleteAlbum(1L);
    }
}


