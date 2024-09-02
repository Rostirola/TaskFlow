package com.example.taskflow.service.impl;

import com.example.taskflow.model.Projeto;
import com.example.taskflow.repository.ProjetoRepository;
import com.example.taskflow.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository projetoRepository;

    @Override
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public List<Projeto> filterByName(String nome){
        return projetoRepository.findAll().stream().filter(projeto -> projeto.getNome().startsWith(nome)).toList();
    }

    @Override
    public void deleteById(Long id) {
        projetoRepository.deleteById(id);
    }

    @Override
    public void save(Projeto projeto) {
        projetoRepository.save(projeto);
    }

    @Override
    public Projeto update(Long id, Projeto atualizado) {
        atualizado.setId(id);
        return projetoRepository.save(atualizado);
    }
}
