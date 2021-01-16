package br.com.dekagames.artistasealbunsapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artistaUID")
    private Long artistaUID;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(
            mappedBy = "artista",
            cascade = CascadeType.ALL
    )
    private List<Album> albuns = new ArrayList<>();

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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
