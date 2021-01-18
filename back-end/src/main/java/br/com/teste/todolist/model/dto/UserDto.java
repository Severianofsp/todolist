package br.com.teste.todolist.model.dto;

import br.com.teste.todolist.model.Users;
import lombok.Data;

@Data
public class UserDto {

    private String nome;
    private String sobrenome;
    private String email;
    private String password;

    public UserDto(){}

    public UserDto(Users user){
        this.nome = user.getNome();
        this.sobrenome = user.getSobrenome();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


}
