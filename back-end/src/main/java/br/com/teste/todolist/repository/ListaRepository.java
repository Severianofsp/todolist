package br.com.teste.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.todolist.model.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista,Long>{

}