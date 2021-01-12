package br.com.dekagames.artistasealbunsapi.controller;

import br.com.dekagames.artistasealbunsapi.dto.AlbumRequest;
import br.com.dekagames.artistasealbunsapi.dto.AlbumResponse;
import br.com.dekagames.artistasealbunsapi.dto.ArtistaRequest;
import br.com.dekagames.artistasealbunsapi.dto.ArtistaResponse;
import br.com.dekagames.artistasealbunsapi.models.Album;
import br.com.dekagames.artistasealbunsapi.models.Artista;
import br.com.dekagames.artistasealbunsapi.repository.AlbumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {this.albumRepository = albumRepository;}

    @GetMapping("/")
    public List<AlbumResponse> findAll()
    {
        var albuns = albumRepository.findAll();
        return albuns.stream().map(AlbumResponse::getAlbumDTO).collect(Collectors.toList());
    }

    @GetMapping("/{albumUID}")
    public AlbumResponse findById(@PathVariable("albumUID") Long albumUID)
    {
        var album = albumRepository.findById(albumUID);
        if(album.isPresent()) {
            return AlbumResponse.getAlbumDTO(album.get());
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Álbum não encontrado"
            );
        }
    }

    @PostMapping("/")
    public void newAlbum(@RequestBody AlbumRequest albumRequest)
    {
        var album = new Album();
        album.setNome(albumRequest.getNome());

        var artista = new Artista();
        artista.setArtistaUID(albumRequest.getArtista().getArtistaUID());

        album.setArtista(artista);
        albumRepository.save(album);
    }

    @PutMapping("/{albumUID}")
    public void updateAlbum(@PathVariable("albumUID") Long albumUID, @RequestBody AlbumRequest albumRequest)
    {
        var album = albumRepository.findById(albumUID);
        if(album.isPresent()) {
            var updateAlbum = album.get();
            updateAlbum.setNome(albumRequest.getNome());

            var artista = new Artista();
            artista.setArtistaUID(albumRequest.getArtista().getArtistaUID());

            updateAlbum.setArtista(artista);
            albumRepository.save(updateAlbum);
        }
        else
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Álbum não encontrado"
            );
        }
    }

}
