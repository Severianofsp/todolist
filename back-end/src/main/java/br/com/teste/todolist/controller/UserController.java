package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.Users;
import br.com.teste.todolist.model.dto.UserDto;
import br.com.teste.todolist.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> buscaTodos(){
        List<UserDto> allUsers = userService.findAll();
        return ResponseEntity
                .ok()
                .body(allUsers);
    }

    @PostMapping
    public ResponseEntity<String> salvaUsuario(@RequestBody UserDto user){
        try {
            userService.save(user);
            return ResponseEntity
                    .ok()
                    .body("Cadastrado com sucesso");
        } catch (SQLDataException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao Cadastrar Usuário");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> buscaUsuarioPelaId(@PathVariable(name = "id")Long id) {
        try {
            UserDto byId = userService.findById(id);
            return ResponseEntity
                    .ok()
                    .body(byId);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> atualizaUsuarioPelaId(@PathVariable(name = "id")Long id, @RequestBody Users user){
        try {
            userService.updateUser(id,user);
            return ResponseEntity
                    .ok()
                    .body(null);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity deletaUsuarioPelaId(@PathVariable(name = "id")Long id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity
                    .ok()
                    .body(null);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }
}
