package br.com.dekagames.artistasealbunsapi.dto;

import br.com.dekagames.artistasealbunsapi.models.Album;

public class AlbumRequest
{
    private String nome;
    private ArtistaResponse artista;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArtistaResponse getArtista() {
        return artista;
    }

    public void setArtista(ArtistaResponse artista) {
        this.artista = artista;
    }
}
