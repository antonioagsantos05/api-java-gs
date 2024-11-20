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

import com.globalsolution2.fiap.assembler.PerguntaModelAssembler;
import com.globalsolution2.fiap.model.PerguntaModel;
import com.globalsolution2.fiap.service.PerguntaService;


@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private PerguntaModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<PerguntaModel>> createPerguntas(@Validated @RequestBody List<PerguntaModel> perguntas) {
        List<EntityModel<PerguntaModel>> perguntaModels = perguntaService.createPerguntas(perguntas).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(perguntaModels,
                linkTo(methodOn(PerguntaController.class).getAllPerguntas()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<PerguntaModel>> getAllPerguntas() {
        List<EntityModel<PerguntaModel>> perguntas = perguntaService.getAllPerguntas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(perguntas,
                linkTo(methodOn(PerguntaController.class).getAllPerguntas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PerguntaModel>> getPerguntaById(@PathVariable Long id) {
        return perguntaService.getPerguntaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PerguntaModel>> updatePergunta(@PathVariable Long id, @RequestBody PerguntaModel perguntaDetails) {
        return perguntaService.updatePergunta(id, perguntaDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePergunta(@PathVariable Long id) {
        if (perguntaService.deletePergunta(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

