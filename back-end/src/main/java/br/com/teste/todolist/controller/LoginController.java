package br.com.teste.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login-error")
    public ResponseEntity<String> loginError(){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Ocorreu um erro ao Logar");
    }
}
