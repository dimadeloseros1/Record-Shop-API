package com.northcoders.RecordShopApi.Repository;

import com.northcoders.RecordShopApi.Model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumGenreRepository extends CrudRepository<Album, Long> {
}
