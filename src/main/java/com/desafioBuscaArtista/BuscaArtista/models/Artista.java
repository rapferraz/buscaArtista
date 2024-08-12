package com.desafioBuscaArtista.BuscaArtista.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;
    private String tipoCarreira;
    private Integer idade;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();

    public Artista(String nome, String tipoCarreira, Integer idade) {
        this.nome = nome;
        this.tipoCarreira = tipoCarreira;
        this.idade = idade;
    }

    public Artista() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoCarreira() {
        return tipoCarreira;
    }

    public void setTipoCarreira(String tipoCarreira) {
        this.tipoCarreira = tipoCarreira;
    }

    public void adicionarMusicas(Musica nomeMusica) {
        musicas.add(nomeMusica);
    }

    public void exibirMusicas() {
        if(musicas.isEmpty()) {
            System.out.println("Esse artista não possui músicas cadastradas.");
        } else {
            System.out.println("Exibindo músicas de " + this.nome + ":");
            musicas.forEach(m ->
                    System.out.println("Nome: " + m.getNomeMusica() + " / Duração em segundos: " + m.getDuracaoEmSegundos() +
                            " / Gênero: " + m.getGenero()));
        }
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return
                "===================================\n" +
                "Nome do artista: " + nome + "\n" +
                        "Idade: " + idade + "\n" +
                        "Tipo de carreira: " + tipoCarreira +
                "\n===================================\n";
    }
}