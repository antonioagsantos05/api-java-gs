package com.globalsolution2.fiap.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.globalsolution2.fiap.controller.PerguntaController;
import com.globalsolution2.fiap.model.PerguntaModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PerguntaModelAssembler implements RepresentationModelAssembler<PerguntaModel, EntityModel<PerguntaModel>> {

    @Override
    public EntityModel<PerguntaModel> toModel(PerguntaModel pergunta) {
        return EntityModel.of(pergunta,
                linkTo(methodOn(PerguntaController.class).getPerguntaById(pergunta.getIdPergunta())).withSelfRel(),
                linkTo(methodOn(PerguntaController.class).getAllPerguntas()).withRel("perguntas"));
    }
}
