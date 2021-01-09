package br.com.dekagames.artistasealbunsapi.repository;

import br.com.dekagames.artistasealbunsapi.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
