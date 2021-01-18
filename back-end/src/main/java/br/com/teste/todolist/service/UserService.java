package br.com.teste.todolist.service;

import br.com.teste.todolist.enuns.TypeProfileEnum;
import br.com.teste.todolist.model.Users;
import br.com.teste.todolist.model.dto.UserDto;
import br.com.teste.todolist.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void save(UserDto user){

        Users userDb = new Users();
        userDb.setNome(user.getNome());
        userDb.setSobrenome(user.getSobrenome());
        userDb.setPassword(new BCryptPasswordEncoder().encode(user.getPassword().toLowerCase()));
        userDb.setEmail(user.getEmail().toLowerCase());
        userDb.setDateCreation(getTimeNow());
        userDb.setProfile(TypeProfileEnum.USARIO.getCod());
        userRepository.save(userDb);
        log.info("User created with Email: " + user.getEmail());
    }

    public List<UserDto> findAll(){
        List<Users> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public UserDto findById(Long id) throws NotFoundException {

        Users userToFind = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não Localizado"));
        return convertToDto(userToFind);
    }

    public Users updateUser(Long id, Users user){

        Users atualiza = userRepository.findById(id).orElseThrow();

        atualiza.setEmail(user.getEmail());
        atualiza.setNome(user.getNome());
        atualiza.setSobrenome(user.getSobrenome());

        userRepository.save(atualiza);
        log.info("Usuario Atualizado com ID: " + id);

        return atualiza;
    }

    public void deleteUserById(Long id){
        Users user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        log.info("Usuario Deletado com ID: " + id);
    }

    private UserDto convertToDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setNome(user.getNome());
        userDto.setSobrenome(user.getSobrenome());
        return userDto;
    }

    public String getTimeNow(){

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDateTime.format(formater);
    }
}
