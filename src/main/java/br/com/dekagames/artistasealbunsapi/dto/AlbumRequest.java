package br.com.dekagames.artistasealbunsapi.dto;

import br.com.dekagames.artistasealbunsapi.models.Album;

public class AlbumRequest
{
    private Long albumUID;
    private ArtistaRequest artista;
    private String nome;

    public Long getAlbumUID() {
        return albumUID;
    }

    public void setAlbumUID(Long albumUID) {
        this.albumUID = albumUID;
    }

    public ArtistaRequest getArtista() {
        return artista;
    }

    public void setArtista(ArtistaRequest artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static AlbumRequest getAlbumDTO(Album album)
    {
        var artista = ArtistaRequest.getArtistaDTO(album.getArtista());

        var albumDTO = new AlbumRequest();
        albumDTO.setAlbumUID(album.getAlbumUID());
        albumDTO.setNome(album.getNome());
        albumDTO.setArtista(artista);

        return albumDTO;
    }
}
