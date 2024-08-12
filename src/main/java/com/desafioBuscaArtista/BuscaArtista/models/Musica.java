package com.desafioBuscaArtista.BuscaArtista.models;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomeMusica;
    private Integer duracaoEmSegundos;
    private String genero;

    @ManyToOne
    private Artista artista;

    public Musica(String nomeMusica, Integer duracaoEmSegundos, String genero, Artista artista) {
        this.nomeMusica = nomeMusica;
        this.duracaoEmSegundos = duracaoEmSegundos;
        this.genero = genero;
        this.artista = artista;
    }

    public Musica() {}

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Integer getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(Integer duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
