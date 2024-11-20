package com.globalsolution2.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalsolution2.fiap.model.RespostaModel;
import com.globalsolution2.fiap.repository.RespostaRepository;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    public RespostaModel createResposta(RespostaModel resposta) {
        return respostaRepository.save(resposta);
    }

    public List<RespostaModel> createRespostas(List<RespostaModel> respostas) {
        return respostaRepository.saveAll(respostas);
    }

    public List<RespostaModel> getAllRespostas() {
        return respostaRepository.findAll();
    }

    public Optional<RespostaModel> getRespostaById(Long id) {
        return respostaRepository.findById(id);
    }

    public Optional<RespostaModel> updateResposta(Long id, RespostaModel respostaDetails) {
        return respostaRepository.findById(id).map(resposta -> {
            resposta.setTxResposta(respostaDetails.getTxResposta());
            resposta.setCorreta(respostaDetails.isCorreta());
            return respostaRepository.save(resposta);
        });
    }

    public boolean deleteResposta(Long id) {
        return respostaRepository.findById(id).map(resposta -> {
            respostaRepository.delete(resposta);
            return true;
        }).orElse(false);
    }
}
