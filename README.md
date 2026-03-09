# FórumHub API

API REST para gerenciamento de tópicos em um fórum, desenvolvida como solução para o Challenge Back End da Alura. O projeto implementa um CRUD completo com autenticação JWT, validações e persistência em banco de dados relacional.

- Criar novos tópicos
- Listar todos os tópicos
- Visualizar um tópico específico
- Atualizar tópicos existentes
- Excluir tópicos
- Autenticação de usuários

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem principal
- **Spring Boot 3.2.0** - Framework base
- **Spring Web** - Criação da API REST
- **Spring Data JPA** - Persistência em banco de dados
- **Spring Security** - Autenticação e autorização
- **Flyway Migration** - Controle de versão do banco
- **MySQL** - Banco de dados relacional
- **Lombok** - Redução de código boilerplate
- **JWT (java-jwt)** - Tokens de autenticação
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

### CRUD de Tópicos
- ✅ **CREATE** - Criar novo tópico com título, mensagem, autor e curso
- ✅ **READ** - Listar todos os tópicos ou buscar por ID
- ✅ **UPDATE** - Atualizar título, mensagem ou status do tópico
- ✅ **DELETE** - Remover tópicos do sistema

### Funcionalidades Extras
- ✅ **Autenticação JWT** - Sistema de login seguro
- ✅ **Validações** - Dados validados antes de persistir
- ✅ **Migrations** - Controle de versão do banco com Flyway
- ✅ **Tratamento de erros** - Respostas adequadas para cada situação
- ✅ **Paginação** - Listagem paginada de tópicos
