package br.com.teste.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Lista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
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