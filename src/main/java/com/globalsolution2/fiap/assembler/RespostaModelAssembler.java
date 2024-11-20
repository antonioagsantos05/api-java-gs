package com.globalsolution2.fiap.assembler;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.globalsolution2.fiap.controller.RespostaController;
import com.globalsolution2.fiap.model.RespostaModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RespostaModelAssembler implements RepresentationModelAssembler<RespostaModel, EntityModel<RespostaModel>> {

    @Override
    public EntityModel<RespostaModel> toModel(RespostaModel resposta) {
        return EntityModel.of(resposta,
                linkTo(methodOn(RespostaController.class).getRespostaById(resposta.getIdResposta())).withSelfRel(),
                linkTo(methodOn(RespostaController.class).getAllRespostas()).withRel("respostas"));
    }
}

