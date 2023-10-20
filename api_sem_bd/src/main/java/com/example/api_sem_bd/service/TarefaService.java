package com.example.api_sem_bd.service;

import com.example.api_sem_bd.entity.Tarefa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private List<Tarefa> tarefas;
    public TarefaService(){
        tarefas = new ArrayList<>();
    }

    public List<Tarefa> buscarTarefas(){
        return tarefas;
    }

    public Tarefa buscarTarefa(Long id) throws Exception {
        Optional<Tarefa> tarefa = tarefas.stream().filter(e -> e.getId() == id).findFirst();
        if(tarefa.isPresent()){
            return tarefa.get();
        } else {
            throw new Exception("A tarefa não foi localizada!");
        }
    }

    public Tarefa criarTarefa(Tarefa tarefa) throws Exception {
        tarefas.add(tarefa);
        return tarefa;
    }

    public Tarefa alterarTarefa(Long id, Tarefa tarefa) throws Exception {
        Optional<Tarefa> produtoDB = tarefas.stream().filter(e -> e.getId() == id).findFirst();
        if(produtoDB.isPresent()){
            produtoDB.get().setNome(tarefa.getNome());
            produtoDB.get().setStatus(tarefa.getStatus());
            produtoDB.get().setDescricao(tarefa.getDescricao());
            return tarefa;
        } else {
            throw new Exception("A tarefa não foi localizada!");
        }
    }

    public Tarefa alterarStatus(Long id, Tarefa tarefa) throws Exception {
        Optional<Tarefa> produtoDB = tarefas.stream().filter(e -> e.getId() == id).findFirst();
        if(produtoDB.isPresent()){
            produtoDB.get().setStatus(tarefa.getStatus());
            return tarefa;
        } else {
            throw new Exception("A tarefa não foi localizada!");
        }
    }

    public void removerTarefa(Long id) {
        tarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

}
