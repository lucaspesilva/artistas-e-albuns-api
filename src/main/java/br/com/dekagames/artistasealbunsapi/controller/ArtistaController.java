package br.com.dekagames.artistasealbunsapi.controller;

import br.com.dekagames.artistasealbunsapi.dto.ArtistaRequest;
import br.com.dekagames.artistasealbunsapi.dto.ArtistaResponse;
import br.com.dekagames.artistasealbunsapi.models.Artista;
import br.com.dekagames.artistasealbunsapi.repository.ArtistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/")
    public List<ArtistaRequest> findAll()
    {
        var artistas = artistaRepository.findAll();
        return artistas.stream().map(ArtistaRequest::getArtistaDTO).collect(Collectors.toList());
    }

    @GetMapping("/{artistaUID}")
    public ArtistaRequest findById(@PathVariable("artistaUID") Long artistaUID)
    {
        var artista = artistaRepository.findById(artistaUID);
        if(artista.isPresent()) {
            return ArtistaRequest.getArtistaDTO(artista.get());
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Artista não encontrado"
            );
        }
    }

    @PostMapping("/")
    public void newArtista(@RequestBody ArtistaResponse artistaResponse)
    {
        var artista = new Artista();
        artista.setNome(artistaResponse.getNome());
        artistaRepository.save(artista);
    }

    @PutMapping("/{artistaUID}")
    public void updateArtista(@PathVariable("artistaUID") Long artistaUID, @RequestBody ArtistaResponse artistaResponse)
    {
        var artista = artistaRepository.findById(artistaUID);
        if(artista.isPresent()) {
            var updateArtista = artista.get();
            updateArtista.setNome(artistaResponse.getNome());
            artistaRepository.save(updateArtista);
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Artista não encontrado"
            );
        }
    }
}
