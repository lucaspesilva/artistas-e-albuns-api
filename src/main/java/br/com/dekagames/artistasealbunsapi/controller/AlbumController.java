package br.com.dekagames.artistasealbunsapi.controller;

import br.com.dekagames.artistasealbunsapi.dto.*;
import br.com.dekagames.artistasealbunsapi.models.Album;
import br.com.dekagames.artistasealbunsapi.models.Artista;
import br.com.dekagames.artistasealbunsapi.repository.AlbumRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public AlbumPagingResponse findAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        var albuns = albumRepository.findAll(pageable);
        AlbumPagingResponse albunsPaging = new AlbumPagingResponse();
        albunsPaging.setAlbuns(albuns.stream().map(AlbumResponse::getAlbumDTO).collect(Collectors.toList()));
        albunsPaging.setTotalAlbuns(albuns.getTotalElements());
        albunsPaging.setTotalPaginas(albuns.getTotalPages());
        albunsPaging.setPaginaAtual(page);
        return albunsPaging;
    }

    @GetMapping("/artista/{artistaUID}")
    public AlbumPagingResponse findByArtista(@PathVariable("artistaUID") Long artistaUID, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        var albuns = albumRepository.findByArtista(artistaUID, pageable);
        AlbumPagingResponse albunsPaging = new AlbumPagingResponse();
        albunsPaging.setAlbuns(albuns.stream().map(AlbumResponse::getAlbumDTO).collect(Collectors.toList()));
        albunsPaging.setTotalAlbuns(albuns.getTotalElements());
        albunsPaging.setTotalPaginas(albuns.getTotalPages());
        albunsPaging.setPaginaAtual(page);
        return albunsPaging;
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
