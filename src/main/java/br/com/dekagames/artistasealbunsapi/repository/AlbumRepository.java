package br.com.dekagames.artistasealbunsapi.repository;

import br.com.dekagames.artistasealbunsapi.models.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    @Query(value = "SELECT album FROM Album album WHERE album.artista.artistaUID = ?1")
    Page<Album> findByArtista(Long artistaUID, Pageable pageable);

}
