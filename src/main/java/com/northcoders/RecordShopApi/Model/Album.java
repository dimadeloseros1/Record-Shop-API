package com.northcoders.RecordShopApi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue
    long id;

    @Column
    int stock;

    @Column
    String artist;

    @Column
    String releaseYear;

    @Column
    Genre genre;

    @Column
    String albumName;
}
