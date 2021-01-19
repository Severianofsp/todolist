package br.com.teste.todolist.model.dto;

import br.com.teste.todolist.model.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto {

    @ApiModelProperty(value = "Nome do Usuario",example = "João")
    private String nome;

    @ApiModelProperty(value = "Sobrenome do Usuario",example = "Augusto")
    private String sobrenome;

    @ApiModelProperty(value = "Email do Usuario",example = "joaoaugusto@gmail.com")
    private String email;

    @ApiModelProperty(value = "Senha do Usuario", example = "cachorrinhoDoVizinho")
    private String password;

    public UserDto(){}

    public UserDto(Users user){
        this.nome = user.getNome();
        this.sobrenome = user.getSobrenome();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


}
