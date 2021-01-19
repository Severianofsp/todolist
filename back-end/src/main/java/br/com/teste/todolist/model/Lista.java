package br.com.teste.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@ApiModelProperty(value = "Título da tarefa", example = "Compra passagens")
	private String titulo;

	@ApiModelProperty(value = "Descrição da tarefa", example = "Preciso comprar passagens para minha viagem")
	private String mensagem;

	@ApiModelProperty(value = "Data defenida pelo usuário para execução da tarefa", example = "30/01/2020")
	private String data;

	@ManyToOne
	@JoinColumn(name = "id_usr_fk")
	private Users user;

	public Lista() {
	}

	public Lista(Lista lista, Users user) {
		this.titulo = lista.getTitulo();
		this.mensagem = lista.getMensagem();
		this.data = lista.getData();
		this.user = user;
	}
}