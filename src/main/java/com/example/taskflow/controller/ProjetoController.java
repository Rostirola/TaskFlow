package com.example.taskflow.controller;

import com.example.taskflow.exception.ResourceNotFoundException;
import com.example.taskflow.model.Projeto;
import com.example.taskflow.payload.MessagePayload;
import com.example.taskflow.repository.ProjetoRepository;
import com.example.taskflow.service.impl.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    Logger logger = LoggerFactory.getLogger(ProjetoController.class);
    final ProjetoServiceImpl projetoService;
    final ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoServiceImpl projetoService, ProjetoRepository projetoRepository) {
        this.projetoService = projetoService;
        this.projetoRepository = projetoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> getAll(@RequestParam(required = false) Optional<String> nome){
        if(nome.isEmpty()){
            return ResponseEntity.ok(projetoService.getAll());
        }else {
            List<Projeto> projetos = projetoService.filterByName(nome.get());
            if(projetos.isEmpty()){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.ok(projetos);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            Optional<Projeto> localizado = projetoService.findById(id);
            return ResponseEntity.ok(localizado);
        }catch (ResourceNotFoundException ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Operation(summary = "Salva um projeto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Projeto Salvo",
                    content ={@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Projeto.class))} )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> save(@RequestBody Projeto projeto){
        projetoService.save(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));

    }

    @Operation(summary = "Atualizando um projeto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um Erro",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Projeto projeto){
        try {
            projetoService.update(id,projeto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }

    }

    @Operation(summary = "Deleta um projeto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Deletado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um Erro",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id){
        logger.info("Deletando u projeto");
        try {
            projetoService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Deletado com sucesso"));
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
