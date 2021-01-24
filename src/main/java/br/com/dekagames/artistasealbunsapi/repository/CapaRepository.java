package br.com.dekagames.artistasealbunsapi.repository;


import br.com.dekagames.artistasealbunsapi.models.Capa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CapaRepository extends JpaRepository<Capa, Long> {
    @Query(value = "SELECT capa FROM Capa capa WHERE capa.album.albumUID = ?1")
    List<Capa> findByAlbum(Long albumUID);
}
