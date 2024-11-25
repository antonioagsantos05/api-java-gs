package com.globalsolution2.fiap.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalsolution2.fiap.assembler.UsuarioModelAssembler;
import com.globalsolution2.fiap.model.UsuarioModel;
import com.globalsolution2.fiap.service.UsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<UsuarioModel>> getAllUsuarios() {
        List<EntityModel<UsuarioModel>> usuarios = usuarioService.getAllUsuarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioModel>> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioModel>> updateUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuarioDetails) {
        return usuarioService.updateUsuario(id, usuarioDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
