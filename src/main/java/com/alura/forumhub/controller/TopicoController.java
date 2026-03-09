package com.alura.forumhub.controller;

import com.alura.forumhub.domain.topico.*;
import com.alura.forumhub.domain.curso.CursoRepository;
import com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // 1. CREATE - Criar novo tópico (POST)
    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid DadosCadastroTopico dados,
                                   UriComponentsBuilder uriBuilder) {
        // Verificar se título já existe
        if (topicoRepository.existsByTitulo(dados.titulo())) {
            return ResponseEntity.badRequest().body("Título já existe");
        }

        // Verificar se mensagem já existe
        if (topicoRepository.existsByMensagem(dados.mensagem())) {
            return ResponseEntity.badRequest().body("Mensagem já existe");
        }

        // Buscar autor e curso
        var autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Criar e salvar tópico
        var topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        topicoRepository.save(topico);

        // Retornar resposta
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemTopico(topico));
    }

    // 2. READ ALL - Listar todos (GET)
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao)
                .map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    // 3. READ ONE - Detalhar um tópico (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DadosListagemTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // 4. UPDATE - Atualizar tópico (PUT)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            var t = topico.get();
            t.atualizar(dados.titulo(), dados.mensagem(), dados.estado());
            return ResponseEntity.ok(new DadosListagemTopico(t));
        }
        return ResponseEntity.notFound().build();
    }

    // 5. DELETE - Excluir tópico (DELETE)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 6. FILTRO - Buscar por curso (GET)
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<DadosListagemTopico>> buscarPorCurso(@PathVariable Long cursoId) {
        var topicos = topicoRepository.findByCursoId(cursoId)
                .stream().map(DadosListagemTopico::new).toList();
        return ResponseEntity.ok(topicos);
    }

    // 7. FILTRO - Buscar por autor (GET)
    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<DadosListagemTopico>> buscarPorAutor(@PathVariable Long autorId) {
        var topicos = topicoRepository.findByAutorId(autorId)
                .stream().map(DadosListagemTopico::new).toList();
        return ResponseEntity.ok(topicos);
    }
}