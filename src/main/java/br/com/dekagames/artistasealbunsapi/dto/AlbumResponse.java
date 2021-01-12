package br.com.dekagames.artistasealbunsapi.dto;

import br.com.dekagames.artistasealbunsapi.models.Album;

public class AlbumResponse
{
    private Long albumUID;
    private ArtistaResponse artista;
    private String nome;

    public Long getAlbumUID() {
        return albumUID;
    }

    public void setAlbumUID(Long albumUID) {
        this.albumUID = albumUID;
    }

    public ArtistaResponse getArtista() {
        return artista;
    }

    public void setArtista(ArtistaResponse artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static AlbumResponse getAlbumDTO(Album album)
    {
        var artista = ArtistaResponse.getArtistaDTO(album.getArtista());

        var albumDTO = new AlbumResponse();
        albumDTO.setAlbumUID(album.getAlbumUID());
        albumDTO.setNome(album.getNome());
        albumDTO.setArtista(artista);

        return albumDTO;
    }
}
