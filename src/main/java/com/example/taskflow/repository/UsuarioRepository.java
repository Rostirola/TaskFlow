package com.example.taskflow.repository;

import com.example.taskflow.model.Role;
import com.example.taskflow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u WHERE u.status =1")
    List<Usuario> findAllAtivos();

    @Query(value = "SELECT u from Usuario u where u.email=?1 OR u.nome=?2 ORDER BY u.nome asc limit 5")
    List<Usuario> findAllBy(String email, String nome);

    @Query(value = "SELECT u FROM Usuario u where u.email=:emailUsuario or u.nome=:nome ORDER BY u.email desc limit 10")
    List<Usuario> findAllByNamed(@Param("nome") String nome, @Param("emailUsuario") String email);

    @Query("SELECT u from Usuario u where u.status IN :statusList")
    List<Usuario> findAllByStatusIn(@Param("statusList") List<Integer> status);

    @Query("SELECT u from Usuario u inner join u.roles roles where roles in :rolesList")
    List<Usuario> findAllByRoles(@Param("rolesList") List<Role> roles);

}