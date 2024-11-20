package com.globalsolution2.fiap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "<html>" +
               "<body>" +
               "<p>Bem-vindo à API do projeto!</p>" +
               "<p>Aqui estão as rotas disponíveis:</p>" +
               "<ul>" +
               "<li>/usuarios - Gerencia os usuários (GET, POST, PUT, DELETE)</li>" +
               "<li>/respostas - Gerencia as respostas (GET, POST, PUT, DELETE)</li>" +
               "<li>/perguntas - Gerencia as perguntas (GET, POST, PUT, DELETE)</li>" +
               "<li>/usuario-respostas - Gerencia as respostas dos usuários (GET, POST, PUT, DELETE)</li>" +
               "</ul>" +
               "<p>Para mais detalhes sobre cada rota, acesse as documentações específicas dos controllers!</p>" +
               "</body>" +
               "</html>";
    }
}
