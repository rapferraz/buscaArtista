package com.desafioBuscaArtista.BuscaArtista.Principal;

import com.desafioBuscaArtista.BuscaArtista.Repository.ArtistaRepository;
import com.desafioBuscaArtista.BuscaArtista.models.Artista;
import com.desafioBuscaArtista.BuscaArtista.models.Musica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private ArtistaRepository repositorio;

    private Scanner input = new Scanner(System.in);
    private List<Artista> listaDeArtistas = new ArrayList<>();
    private Optional<Artista> artistaBusca;

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    *** Bem Vindo ***
                    
                    1 - Registrar um artista
                    2 - Registrar uma música
                    3 - Listar artistas
                    4 - Listar músicas de um artista
                    
                    0 - Sair""";

            System.out.println(menu);
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1: registrarArtista();
                    break;
                case 2: registrarMusica();
                    break;
                case 3: listarArtistas();
                    break;
                case 4: listarMusicasPorArtista();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public void registrarArtista() {
        System.out.println("Digite o nome do artista: ");
        String nome = input.nextLine();

        System.out.println("Qual o tipo de carreira desse artista (solo/dupla/banda): ");
        String tipoCarreira = input.nextLine();

        System.out.println("Qual a idade desse artista: ");
        Integer idade = input.nextInt();

        Artista artista = new Artista(nome, tipoCarreira, idade);
        //listaDeArtistas.add(artista);
        repositorio.save(artista);
        System.out.println("Artista registrado com sucesso!\n");
    }

    private void registrarMusica() {
        System.out.println("Digite o nome do artista: ");
        String nome = input.nextLine();

        artistaBusca = repositorio.findByNomeContainingIgnoreCase(nome);

        if (artistaBusca.isPresent()) {
            var artistaEncontrado = artistaBusca.get();
            System.out.println("Digite o nome da música: ");
            String nomeMusica = input.nextLine();

            System.out.println("Digite o gênero da música: ");
            String genero = input.nextLine();

            System.out.println("Digite a duração da músicas (em segundos): ");
            Integer duracaoEmSegundos = input.nextInt();

            Musica musica = new Musica(nomeMusica, duracaoEmSegundos, genero, artistaEncontrado);
            artistaEncontrado.adicionarMusicas(musica);
            repositorio.save(artistaEncontrado);
        } else {
            System.out.println("Artista não registrado!");
        }
    }

    public void listarArtistas() {
        listaDeArtistas = repositorio.findAll();
        System.out.println(listaDeArtistas);
    }

    private void listarMusicasPorArtista() {
        System.out.println("Digite o nome do artista: ");
        String nome = input.nextLine();
        artistaBusca = repositorio.findByNomeContainingIgnoreCase(nome);

        if(artistaBusca.isPresent()) {
            Artista artista = artistaBusca.get();
            artista.exibirMusicas();
        } else {
            System.out.println("Artista não encontrado!");
        }
    }
}
