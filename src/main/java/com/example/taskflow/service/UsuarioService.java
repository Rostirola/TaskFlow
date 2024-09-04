package com.example.taskflow.service;

import com.example.taskflow.model.Role;
import com.example.taskflow.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    List<Usuario> findAllUsuariosAtivos();
    List<Usuario> findAllByStatusList(List<Integer> status);
    List<Usuario> findAllByRoles(List<Role> roles);
}
