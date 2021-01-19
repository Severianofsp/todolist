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

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void save(UserDto user) throws SQLDataException {

        Users userDb = new Users();
        userDb.setNome(user.getNome());
        userDb.setSobrenome(user.getSobrenome());
        userDb.setPassword(new BCryptPasswordEncoder().encode(user.getPassword().toLowerCase()));
        userDb.setEmail(user.getEmail().toLowerCase());
        userDb.setDateCreation(getTimeNow());
        userDb.setProfile(TypeProfileEnum.USARIO.getCod());
        if(isRegisterValid(user.getEmail())){
        Users save = userRepository.save(userDb);
        }else {
            throw new SQLDataException("O Usuário já existe no banco");
        }
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

    public Users updateUser(Long id, Users user) throws NotFoundException {

        Users atualiza = userRepository.findById(id).orElse(null);
        if(atualiza != null) {
            atualiza.setEmail(user.getEmail());
            atualiza.setNome(user.getNome());
            atualiza.setSobrenome(user.getSobrenome());

            log.info("Usuario Atualizado com ID: " + id);
            return userRepository.save(atualiza);
        }
        throw new NotFoundException("Usuario com Id: " + id + "não foi encontado no banco");
    }

    public void deleteUserById(Long id) throws NotFoundException {
        Users user = userRepository.findById(id).orElse(null);

        if(user != null) {
            userRepository.delete(user);
            log.info("Usuario Deletado com ID: " + id);
        }
        throw new NotFoundException("Usuario com Id: " + id + "não foi encontado no banco");
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

    public Boolean isRegisterValid(String email){
        return !userRepository.existsByEmail(email);
    }
}
