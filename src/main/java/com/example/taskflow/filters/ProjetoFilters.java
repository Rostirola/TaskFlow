package com.example.taskflow.filters;

import com.example.taskflow.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoFilters {
    private Optional<String> nome;
    private Optional<String> descricao;
    private Optional<Categoria> categoria;
    private Optional<String> dataCadastro;
    private Optional<String> dataUltimaAlteracao;
}
