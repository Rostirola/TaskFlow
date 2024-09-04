package com.example.taskflow;

import com.example.taskflow.model.Categoria;
import com.example.taskflow.model.Projeto;
import com.example.taskflow.service.ProjetoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ProjetoServiceTest {

    @Autowired
    ProjetoService projetoService;

    @Test
    @DisplayName("Deve retornar todos os projetos.")
    public void getAll() {
        List<Projeto> all = projetoService.getAll();
        assertEquals(3, all.size());
    }

    @Test
    @DisplayName("Deve retornar por id.")
    public void findById() {
        Optional<Projeto> projeto = projetoService.findById(1L);
        assertTrue(projeto.isPresent());
    }

    @Test
    @DisplayName("Deve retornar por nome.")
    public void filterByName() {
        List<Projeto> projetos = projetoService.filterByName("TP1");
        assertEquals(1, projetos.size());
    }

    @Test
    @DisplayName("Deve deletar por id.")
    public void deleteById() {
        projetoService.deleteById(1L);
        Optional<Projeto> projeto = projetoService.findById(1L);
        assertTrue(projeto.isPresent());
    }

    @Test
    @DisplayName("Deve criar um novo registro.")
    public void save() {
        Categoria categoria = Categoria.builder().id(1L).build();
        List<Projeto> all = projetoService.getAll();
        int estadoInicial = all.size();
        Projeto projeto = new Projeto();
        projeto.setNome("Tp 02");
        projeto.setDescricao("Desenvolver uma Camada de Persistência Real");
        projeto.setCategoria(categoria);
        projeto.setDataCadastro("04/09/2024");
        projeto.setDataUltimaAlteracao("04/09/2024");
        projetoService.save(projeto);
        all = projetoService.getAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1,estadoFinal);
    }

    @Test
    @DisplayName("Deve atualizar um registro.")
    public void update() {
        Optional<Projeto> projeto = projetoService.findById(1L);
        Categoria categoria = Categoria.builder().id(1L).build();
        Projeto projetoAtt = new Projeto();
        projetoAtt.setNome("Tp 02");
        projetoAtt.setDescricao("Desenvolver uma Camada de Persistência Real");
        projetoAtt.setCategoria(categoria);
        projetoAtt.setDataCadastro("04/09/2024");
        projetoAtt.setDataUltimaAlteracao("04/09/2024");
        projetoService.update(1L, projetoAtt);
        Optional<Projeto> projetoAtualizado = projetoService.findById(1L);
        assertEquals(projeto, projetoAtualizado);
    }
}
