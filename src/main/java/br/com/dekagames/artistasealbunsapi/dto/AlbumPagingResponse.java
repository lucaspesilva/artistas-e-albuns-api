package br.com.dekagames.artistasealbunsapi.dto;

import java.util.List;

public class AlbumPagingResponse
{
    private List<AlbumResponse> albuns;
    private long totalAlbuns;
    private int totalPaginas;
    private int paginaAtual;

    public List<AlbumResponse> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<AlbumResponse> albuns) {
        this.albuns = albuns;
    }

    public long getTotalAlbuns() {
        return totalAlbuns;
    }

    public void setTotalAlbuns(long totalAlbuns) {
        this.totalAlbuns = totalAlbuns;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}
