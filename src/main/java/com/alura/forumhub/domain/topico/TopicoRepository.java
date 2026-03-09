package com.alura.forumhub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByMensagem(String mensagem);
    List<Topico> findByCursoId(Long cursoId);
    List<Topico> findByAutorId(Long autorId);
}