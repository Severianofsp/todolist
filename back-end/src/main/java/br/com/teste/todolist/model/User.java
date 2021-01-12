package br.com.teste.todolist.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(value = "O nome do usuário",example = "Felipe")
    private String nome;
    @ApiModelProperty(value = "O sobrenome do usuário",example = "Simões")
    private String sobrenome;
    @ApiModelProperty(value = "O email do usuário",example = "ssimoes.felipe@gmail.com")
    private String email;
    @ApiModelProperty(value = "A lista de tarefas do usuário",example = "Compras")

    @OneToMany(targetEntity = Lista.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "list_fk", referencedColumnName = "id")
    private List<Lista> lista;
}
