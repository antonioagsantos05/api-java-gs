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

import com.globalsolution2.fiap.assembler.RespostaModelAssembler;
import com.globalsolution2.fiap.model.RespostaModel;
import com.globalsolution2.fiap.service.RespostaService;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private RespostaModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<RespostaModel>> createRespostas(@Validated @RequestBody List<RespostaModel> respostas) {
        List<EntityModel<RespostaModel>> respostaModels = respostaService.createRespostas(respostas).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(respostaModels,
                linkTo(methodOn(RespostaController.class).getAllRespostas()).withSelfRel());
    }

    @GetMapping
    public CollectionModel<EntityModel<RespostaModel>> getAllRespostas() {
        List<EntityModel<RespostaModel>> respostas = respostaService.getAllRespostas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(respostas,
                linkTo(methodOn(RespostaController.class).getAllRespostas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RespostaModel>> getRespostaById(@PathVariable Long id) {
        return respostaService.getRespostaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RespostaModel>> updateResposta(@PathVariable Long id, @RequestBody RespostaModel respostaDetails) {
        return respostaService.updateResposta(id, respostaDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResposta(@PathVariable Long id) {
        if (respostaService.deleteResposta(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
