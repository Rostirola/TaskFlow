package com.example.taskflow;

import com.example.taskflow.model.Categoria;
import com.example.taskflow.service.CategoriaService;
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
public class CategoriaServiceTest {

    @Autowired
    CategoriaService categoriaService;

    @Test
    @DisplayName("Deve retornar todos as categorias.")
    public void findAll() {
        List<Categoria> all = categoriaService.findAll();
        assertEquals(3, all.size());
    }

    @Test
    @DisplayName("Deve retornar por ID.")
    public void findById() {
        Optional<Categoria> byId = categoriaService.findById(1L);
        assertTrue(byId.isPresent());
        Optional<Categoria> naoExistente = categoriaService.findById(1000L);
        assertTrue(naoExistente.isEmpty());
    }

}
