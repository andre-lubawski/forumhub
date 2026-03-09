-- Inserir usuários (senha: 123456 - será criptografada depois)
INSERT INTO usuarios (nome, email, senha) VALUES
                                              ('Admin', 'admin@email.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeeLHY/5YfJ/lfF7pLQyRkPqXQvZqKJpS'),
                                              ('João Silva', 'joao@email.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeeLHY/5YfJ/lfF7pLQyRkPqXQvZqKJpS');

-- Inserir cursos
INSERT INTO cursos (nome, categoria) VALUES
                                         ('Java', 'PROGRAMACAO'),
                                         ('Spring Boot', 'PROGRAMACAO'),
                                         ('Banco de Dados', 'BANCO_DE_DADOS');

-- Inserir tópicos de exemplo
INSERT INTO topicos (titulo, mensagem, data_criacao, estado, autor_id, curso_id) VALUES
                                                                                     ('Dúvida sobre Spring', 'Como criar uma API REST?', NOW(), 'NAO_RESPONDIDO', 1, 2),
                                                                                     ('Problema com JPA', 'Erro ao salvar no banco', NOW(), 'NAO_SOLUCIONADO', 2, 2),
                                                                                     ('Java 17 vs Java 11', 'Quais as principais diferenças?', NOW(), 'SOLUCIONADO', 1, 1);