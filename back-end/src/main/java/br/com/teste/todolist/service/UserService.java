package br.com.teste.todolist.service;

import br.com.teste.todolist.model.User;
import br.com.teste.todolist.repository.UserRepository;
import javassist.NotFoundException;
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

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não encontrado user com id "+ id));
    }

    public User updateUser(Long id, User user) throws NotFoundException {

        User atualiza = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não encontrado user com id "+ id));

        atualiza.setEmail(user.getEmail());
        atualiza.setNome(user.getNome());
        atualiza.setSobrenome(user.getSobrenome());

        userRepository.save(atualiza);

        return atualiza;
    }

    public void deleteUserById(Long id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não encontrado user com id " + id));
        userRepository.delete(user);
    }
}
