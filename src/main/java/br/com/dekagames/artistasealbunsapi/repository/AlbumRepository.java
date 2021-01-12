package br.com.dekagames.artistasealbunsapi.repository;

import br.com.dekagames.artistasealbunsapi.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
