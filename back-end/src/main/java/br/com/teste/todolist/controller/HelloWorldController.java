package br.com.teste.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public String helloWorld(){
        return "A Rápida Raposa Marrom Pula Sobre o Cão Preguiçoso";
    }
}
