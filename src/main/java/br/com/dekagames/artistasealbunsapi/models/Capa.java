package br.com.dekagames.artistasealbunsapi.models;

import javax.persistence.*;

@Entity
@Table(name = "capa")
public class Capa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capaUID")
    private Long capaUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="albumUID", nullable = false)
    private Album album;

    @Column(name = "objeto", nullable = false)
    private String objeto;

    public Long getCapaUID() {
        return capaUID;
    }

    public void setCapaUID(Long capaUID) {
        this.capaUID = capaUID;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }
}
