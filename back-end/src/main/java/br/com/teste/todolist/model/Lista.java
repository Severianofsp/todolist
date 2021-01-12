package br.com.teste.todolist.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty(value = "O título da tarefa",example = "Comprar feijão")
	private String titulo;
	@ApiModelProperty(value = "A mensagem da tarefa",example = "1kg de feijão branco")
	private String mensagem;
	@ApiModelProperty(value = "A data da tarefa",example = "12/02/2021")
	private String data;

}