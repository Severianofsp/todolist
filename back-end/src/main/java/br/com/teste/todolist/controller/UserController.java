package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.User;
import br.com.teste.todolist.service.UserService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Busca todos os usuários")
    public ResponseEntity<List<User>> buscaTodos(){
        return ResponseEntity
                .ok()
                .body(userService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um usuário pelo ID")
    public ResponseEntity<User> buscaUsuarioPelaId(@PathVariable(name = "id")Long id){
        try {
            return ResponseEntity
                    .ok()
                    .body(userService.findById(id));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Salva um novo usuário")
    public ResponseEntity<User> salvaUsuario(@RequestBody User user){
        User novoUser = userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um usuário pelo ID")
    public ResponseEntity atualizaUsuarioPelaId(@PathVariable(name = "id")Long id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return ResponseEntity
                    .ok()
                    .body("Usuário atualizado");
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);

        }
    }
        @DeleteMapping("/{id}")
        @ApiOperation(value = "Apaga um usuário pelo ID")
        public ResponseEntity deletaUsuarioPelaId (@PathVariable(name = "id") Long id){
            try {
                userService.deleteUserById(id);
                return ResponseEntity
                        .ok()
                        .body("User Deletado");
            } catch (NotFoundException e) {
                log.error(e.getMessage());
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        }
    }