package com.alura.forumhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumhubAndreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumhubAndreApplication.class, args);
        System.out.println("🚀 API FórumHub rodando em: http://localhost:8080");
        System.out.println("📚 Teste: http://localhost:8080/topicos");
    }
}