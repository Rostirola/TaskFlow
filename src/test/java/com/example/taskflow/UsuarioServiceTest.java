package com.example.taskflow;

import com.example.taskflow.model.Role;
import com.example.taskflow.model.Usuario;
import com.example.taskflow.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar todos os usuarios.")
    public void findAll() {
        List<Usuario> all = usuarioService.findAll();
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Deve retornar todos os usuarios ativos.")
    public void findAllUsuariosAtivos() {
        List<Usuario> all = usuarioService.findAllUsuariosAtivos();
        assertEquals(1, all.size());
    }

    @Test
    @DisplayName("Deve retornar todos os usuarios por status.")
    public void findAllByStatusList() {
        List<Integer> statusList = Arrays.asList(1, 0);
        List<Usuario> all = usuarioService.findAllByStatusList(statusList);
        assertEquals(2, all.size());
    }

    @Test
    @DisplayName("Deve retornar todos os usuarios por roles.")
    public void findAllByRoles() {
        List<Role> roles = Arrays.asList(new Role((short) 1, "ADMIN"), new Role((short) 2, "USER"));
        List<Usuario> all = usuarioService.findAllByRoles(roles);
        assertEquals(1, all.size());
    }
}
