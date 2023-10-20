package com.example.api_sem_bd.controller;

import com.example.api_sem_bd.entity.Tarefa;
import com.example.api_sem_bd.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("todos")
    public ResponseEntity<?> buscarTarefas(){
        try {
            List<Tarefa> lista = tarefaService.buscarTarefas();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Error", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarTarefa(@PathVariable("tarefa") Long id){
        try {
            Tarefa tarefa = tarefaService.buscarTarefa(id);
            return new ResponseEntity(tarefa, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa){
        try{
            tarefa = tarefaService.criarTarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<?> alterarTarefa(@RequestBody Tarefa tarefa, @PathVariable("id") Long id){
        try{
            tarefa = tarefaService.alterarTarefa(id, tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("alterarStatus/{id}")
    public ResponseEntity<?> alterarStatus(@RequestBody Tarefa tarefa, @PathVariable("id") Long id){
        try{
            tarefa = tarefaService.alterarStatus(id, tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<?> removerTarefa(@PathVariable("id") Long id){
        try {
            tarefaService.removerTarefa(id);
            return new ResponseEntity("A tarefa foi excluida", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
