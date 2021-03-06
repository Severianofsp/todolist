package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.Lista;
import br.com.teste.todolist.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listas")
public class ListaController {
    @Autowired
    private ListaService listaService;

    @GetMapping
    public List<Lista> buscaTodasListas() {
        return listaService.findAll();
    }

    @PostMapping
    public Lista salvaLista(@RequestBody Lista lista){
        return listaService.save(lista);
    }

    @GetMapping("/{id}")
    public Optional<Lista> buscaListaId(@PathVariable(name = "id")Long id){
            return listaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void apagaListaPeloId(@PathVariable(name = "id")Long id){
       listaService.deletaListaPeloId(id);
    }

    @PutMapping("/{id}")
    public Lista atualizaLista(@PathVariable(name = "id")Long id, @RequestBody Lista lista){
        return listaService.atualizaListaPeloId(id, lista);
    }

}
