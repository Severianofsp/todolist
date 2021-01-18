package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.Users;
import br.com.teste.todolist.model.dto.UserDto;
import br.com.teste.todolist.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<String> salvaUsuario(@RequestBody UserDto user){
        userService.save(user);
        return ResponseEntity
                .ok()
                .body("Cadastrado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> buscaUsuarioPelaId(@PathVariable(name = "id")Long id) throws NotFoundException {
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
    public Users atualizaUsuarioPelaId(@PathVariable(name = "id")Long id, @RequestBody Users user){
        return userService.updateUser(id,user);

    }

    @DeleteMapping("{id}")
    public void deletaUsuarioPelaId(@PathVariable(name = "id")Long id){
        userService.deleteUserById(id);
    }
}
