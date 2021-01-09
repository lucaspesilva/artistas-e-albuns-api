package br.com.dekagames.artistasealbunsapi.dto;

import br.com.dekagames.artistasealbunsapi.models.Album;
import br.com.dekagames.artistasealbunsapi.models.Artista;

public class AlbumDTO
{
    private Long albumUID;
    private ArtistaDTO artista;
    private String nome;

    public Long getAlbumUID() {
        return albumUID;
    }

    public void setAlbumUID(Long albumUID) {
        this.albumUID = albumUID;
    }

    public ArtistaDTO getArtista() {
        return artista;
    }

    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static AlbumDTO getAlbumDTO(Album album)
    {
        var artista = ArtistaDTO.getArtistaDTO(album.getArtista());

        var albumDTO = new AlbumDTO();
        albumDTO.setAlbumUID(album.getAlbumUID());
        albumDTO.setNome(album.getNome());
        albumDTO.setArtista(artista);

        return albumDTO;
    }
}
