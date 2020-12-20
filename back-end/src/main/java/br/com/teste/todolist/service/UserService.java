package br.com.teste.todolist.service;

import br.com.teste.todolist.model.User;
import br.com.teste.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user){

        User atualiza = userRepository.findById(id).orElseThrow();

        atualiza.setEmail(user.getEmail());
        atualiza.setNome(user.getNome());
        atualiza.setSobrenome(user.getSobrenome());

        userRepository.save(atualiza);

        return atualiza;
    }

    public void deleteUserById(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
