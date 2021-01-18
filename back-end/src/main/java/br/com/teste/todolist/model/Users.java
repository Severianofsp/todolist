package br.com.teste.todolist.model;

import br.com.teste.todolist.enuns.TypeProfileEnum;
import br.com.teste.todolist.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_user")
    private Long id;

    @Column(name = "cd_profile")
    private byte profile;

    @Column(name="nm_first_name")
    private String nome;

    @Column(name="nm_last_name")
    private String sobrenome;

    @Column(name = "nm_email", nullable = false, unique = true)
    private String email;

    @Column(name = "nm_passord", nullable = false)
    private String password;

    @Column(name = "dt_creation")
    private String dateCreation;

    @OneToMany
    @JoinColumn(name = "list_fk")
    private List<Lista> lista;


    public List<TypeProfileEnum> getAuthorities(){
        return new ArrayList<>(this.profile);
    }

    public String[] getAuthoritiesString(){
        TypeProfileEnum tpe = TypeProfileEnum.toEnum(this.profile);
        String str = tpe.getAuthority();
        return new String[] {str};
    }
}
