package com.example.taskflow.service.impl;

import com.example.taskflow.filters.ProjetoFilters;
import com.example.taskflow.model.Projeto;
import com.example.taskflow.repository.ProjetoRepository;
import com.example.taskflow.service.ProjetoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository projetoRepository;
    private final EntityManager entityManager;

    @Override
    public List<Projeto> findWithFilters(ProjetoFilters filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Projeto> cq = cb.createQuery(Projeto.class);
        Root<Projeto> projeto = cq.from(Projeto.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filters.getNome().isPresent()) {
            String query = filters.getNome().get() + "%";
            Predicate nome = cb.like(projeto.get("nome"), query);
            predicates.add(nome);
        }
        if(filters.getCategoria().isPresent()){
            Predicate categoria = cb.equal(projeto.get("categoria"), filters.getCategoria().get());
            predicates.add(categoria);
        }
        if (filters.getDataCadastro().isPresent()) {
            String query = filters.getDataCadastro().get() + "%";
            Predicate dataCadastro = cb.like(projeto.get("dataCadastro"), query);
            predicates.add(dataCadastro);
        }   if (filters.getDataUltimaAlteracao().isPresent()) {
            String query = filters.getDataUltimaAlteracao().get() + "%";
            Predicate dataUltimaAlteracao = cb.like(projeto.get("dataUltimaAlteracao"), query);
            predicates.add(dataUltimaAlteracao);
        }
        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));
        List<Projeto> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }
    
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
