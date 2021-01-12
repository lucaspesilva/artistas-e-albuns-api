package br.com.dekagames.artistasealbunsapi.models;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumUID")
    private Long albumUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="artistaUID", nullable = false)
    private Artista artista;

    @Column(name = "nome", nullable = false)
    private String nome;

    public Long getAlbumUID() {
        return albumUID;
    }

    public void setAlbumUID(Long albumUID) {
        this.albumUID = albumUID;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
