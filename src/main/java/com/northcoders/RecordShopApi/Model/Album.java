package com.northcoders.RecordShopApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    int stock;

    @Column
    String artist;

    @Column
    int releaseYear;

    @Column
    Genre genre;

    @Column
    String albumName;
}
