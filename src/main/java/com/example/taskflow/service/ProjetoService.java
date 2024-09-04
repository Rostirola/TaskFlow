package com.example.taskflow.service;

import com.example.taskflow.filters.ProjetoFilters;
import com.example.taskflow.model.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    List<Projeto> getAll();
    Optional<Projeto> findById(Long id);
    List<Projeto> filterByName(String nome);
    void deleteById(Long id);
    void save(Projeto projeto);
    Projeto update(Long id, Projeto atualizado);
    List<Projeto> findWithFilters(ProjetoFilters filters);
}
