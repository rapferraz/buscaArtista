package com.desafioBuscaArtista.BuscaArtista.Repository;

import com.desafioBuscaArtista.BuscaArtista.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

}
