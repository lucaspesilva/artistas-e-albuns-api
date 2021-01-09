package br.com.dekagames.artistasealbunsapi.controller;

import br.com.dekagames.artistasealbunsapi.dto.ArtistaDTO;
import br.com.dekagames.artistasealbunsapi.repository.ArtistaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artista")
public class ArtistaController
{
    private final ArtistaRepository artistaRepository;

    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @GetMapping("/get")
    public List<ArtistaDTO> findAll()
    {
        var artistas = artistaRepository.findAll();
        return artistas.stream().map(ArtistaDTO::getArtistaDTO).collect(Collectors.toList());
    }
}
