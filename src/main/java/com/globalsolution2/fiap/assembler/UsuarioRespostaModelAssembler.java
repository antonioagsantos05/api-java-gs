package com.globalsolution2.fiap.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.globalsolution2.fiap.controller.UsuarioRespostaController;
import com.globalsolution2.fiap.model.UsuarioRespostaModel;

@Component
public class UsuarioRespostaModelAssembler implements RepresentationModelAssembler<UsuarioRespostaModel, EntityModel<UsuarioRespostaModel>> {

    @Override
    public EntityModel<UsuarioRespostaModel> toModel(UsuarioRespostaModel usuarioResposta) {
        return EntityModel.of(usuarioResposta,
                linkTo(methodOn(UsuarioRespostaController.class).getUsuarioRespostaById(usuarioResposta.getIdUsuarioResposta())).withSelfRel(),
                linkTo(methodOn(UsuarioRespostaController.class).getAllUsuarioRespostas()).withRel("usuario-respostas"));
    }
}

