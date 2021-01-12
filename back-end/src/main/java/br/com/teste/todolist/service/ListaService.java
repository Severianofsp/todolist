package br.com.teste.todolist.service;

import br.com.teste.todolist.model.Lista;
import br.com.teste.todolist.repository.ListaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

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

    public Lista findById(Long id) throws NotFoundException {
        return listaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar uma lista com id: " + id));
    }

    public void deletaListaPeloId(Long id) throws NotFoundException {
        Lista lista = listaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar uma lista com id: " + id));
        listaRepository.delete(lista);
    }

    public Lista atualizaListaPeloId(Long id,Lista lista) throws NotFoundException {
        Lista atualiza = listaRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Não foi possível atualizar a lista com id: " + id));

        atualiza.setData(lista.getData());
        atualiza.setMensagem(lista.getMensagem());
        atualiza.setTitulo(lista.getTitulo());

        listaRepository.save(atualiza);
        return atualiza;
    }
}
