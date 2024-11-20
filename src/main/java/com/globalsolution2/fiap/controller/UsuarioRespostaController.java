package com.globalsolution2.fiap.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalsolution2.fiap.assembler.UsuarioRespostaModelAssembler;
import com.globalsolution2.fiap.model.UsuarioRespostaModel;
import com.globalsolution2.fiap.service.UsuarioRespostaService;

@RestController
@RequestMapping("/usuario-respostas")
public class UsuarioRespostaController {

    @Autowired
    private UsuarioRespostaService usuarioRespostaService;

    @Autowired
    private UsuarioRespostaModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<UsuarioRespostaModel>> createUsuarioRespostas(@Validated @RequestBody List<UsuarioRespostaModel> usuarioRespostas) {
        List<EntityModel<UsuarioRespostaModel>> usuarioRespostaModels = usuarioRespostaService.createUsuarioRespostas(usuarioRespostas).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarioRespostaModels,
                linkTo(methodOn(UsuarioRespostaController.class).getAllUsuarioRespostas()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<UsuarioRespostaModel>> getAllUsuarioRespostas() {
        List<EntityModel<UsuarioRespostaModel>> usuarioRespostas = usuarioRespostaService.getAllUsuarioRespostas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarioRespostas,
                linkTo(methodOn(UsuarioRespostaController.class).getAllUsuarioRespostas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioRespostaModel>> getUsuarioRespostaById(@PathVariable Long id) {
        return usuarioRespostaService.getUsuarioRespostaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioRespostaModel>> updateUsuarioResposta(@PathVariable Long id, @RequestBody UsuarioRespostaModel usuarioRespostaDetails) {
        return usuarioRespostaService.updateUsuarioResposta(id, usuarioRespostaDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioResposta(@PathVariable Long id) {
        if (usuarioRespostaService.deleteUsuarioResposta(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
