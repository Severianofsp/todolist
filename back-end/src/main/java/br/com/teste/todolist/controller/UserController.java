package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.User;
import br.com.teste.todolist.repository.UserRepository;
import br.com.teste.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> buscaTodos(){
        return userService.findAll();
    }

    @PostMapping
    public User salvaUsuario(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> buscaUsuarioPelaId(@PathVariable(name = "id")Long id){
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User atualizaUsuarioPelaId(@PathVariable(name = "id")Long id, @RequestBody User user){
        return userService.updateUser(id,user);

    }

    @DeleteMapping("{id}")
    public void deletaUsuarioPelaId(@PathVariable(name = "id")Long id){
        userService.deleteUserById(id);
    }
}
