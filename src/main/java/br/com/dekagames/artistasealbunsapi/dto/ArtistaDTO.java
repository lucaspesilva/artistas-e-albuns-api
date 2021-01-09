package br.com.dekagames.artistasealbunsapi.dto;

import br.com.dekagames.artistasealbunsapi.models.Artista;

public class ArtistaDTO {
    private Long artistaUID;
    private String nome;

    public Long getArtistaUID() {
        return artistaUID;
    }

    public void setArtistaUID(Long artistaUID) {
        this.artistaUID = artistaUID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static ArtistaDTO getArtistaDTO(Artista artista)
    {
        var artistaDTO = new ArtistaDTO();
        artistaDTO.setArtistaUID(artista.getArtistaUID());
        artistaDTO.setNome(artista.getNome());

        return artistaDTO;
    }
}
