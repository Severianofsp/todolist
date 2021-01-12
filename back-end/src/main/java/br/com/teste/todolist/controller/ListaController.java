package br.com.teste.todolist.controller;

import br.com.teste.todolist.model.Lista;
import br.com.teste.todolist.service.ListaService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/listas")
public class ListaController {
    @Autowired
    private ListaService listaService;

    @GetMapping
    @ApiOperation(value = "Busca todas as listas")
    public ResponseEntity<List<Lista>> buscaTodasListas() {
        return ResponseEntity
                .ok()
                .body(listaService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca todas as listas pelo ID")
    public ResponseEntity<Lista> buscaListaId(@PathVariable(name = "id")Long id){
        try {
            return ResponseEntity
                    .ok()
                    .body(listaService.findById(id));
            } catch (NotFoundException e) {
                 log.error(e.getMessage());
                 return ResponseEntity
                         .status(HttpStatus.NOT_FOUND)
                         .body(null);
            }
    }

    @PostMapping
    @ApiOperation(value = "Salva uma nova lista")
    public ResponseEntity<Lista> salvaLista(@RequestBody Lista lista){
        Lista novaLista = listaService.save(lista);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Apaga a lista pelo ID")
    public ResponseEntity apagaListaPeloId(@PathVariable(name = "id")Long id){
       try {
           listaService.deletaListaPeloId(id);
           return ResponseEntity
                   .ok()
                   .body("Lista apagada");
       } catch (NotFoundException e) {
           log.error(e.getMessage());
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(null);
       }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza a lista pelo ID")
    public ResponseEntity atualizaLista(@PathVariable(name = "id")Long id, @RequestBody Lista lista){
        try {
            listaService.atualizaListaPeloId(id, lista);
            return ResponseEntity
                    .ok()
                    .body("Lista Atualizada");
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }

}
