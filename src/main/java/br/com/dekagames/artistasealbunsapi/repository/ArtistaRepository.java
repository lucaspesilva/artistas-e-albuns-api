package br.com.dekagames.artistasealbunsapi.repository;

import br.com.dekagames.artistasealbunsapi.models.Artista;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends PagingAndSortingRepository<Artista, Long> {
    List<Artista> findByNomeContains(String nome, Sort sort);
}
