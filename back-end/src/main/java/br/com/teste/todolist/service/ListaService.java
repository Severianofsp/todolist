package br.com.teste.todolist.service;

import br.com.teste.todolist.model.Lista;
import br.com.teste.todolist.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    public List<Lista> findAll() {
        return listaRepository.findAll();
    }

    public Lista save(Lista lista) {
        return listaRepository.save(lista);
    }

    public Optional<Lista> findById(Long id) {
        return listaRepository.findById(id);
    }

    public void deletaListaPeloId(Long id) {
        Lista lista = listaRepository.findById(id).orElseThrow();
        listaRepository.delete(lista);
    }

    public Lista atualizaListaPeloId(Long id,Lista lista) {
        Lista atualiza = listaRepository.findById(id).orElseThrow();

        atualiza.setData(lista.getData());
        atualiza.setMensagem(lista.getMensagem());
        atualiza.setTitulo(lista.getTitulo());

        listaRepository.save(atualiza);
        return atualiza;
    }
}
