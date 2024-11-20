package com.globalsolution2.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.globalsolution2.fiap.model.PerguntaModel;
import com.globalsolution2.fiap.repository.PerguntaRepository;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;
    
    public PerguntaModel createPergunta(PerguntaModel pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public List<PerguntaModel> createPerguntas(List<PerguntaModel> perguntas) {
        return perguntaRepository.saveAll(perguntas);
    }

    public List<PerguntaModel> getAllPerguntas() {
        return perguntaRepository.findAll();
    }

    public Optional<PerguntaModel> getPerguntaById(Long id) {
        return perguntaRepository.findById(id);
    }

    public Optional<PerguntaModel> updatePergunta(Long id, PerguntaModel perguntaDetails) {
        return perguntaRepository.findById(id).map(pergunta -> {
            pergunta.setNmTitulo(perguntaDetails.getNmTitulo());
            pergunta.setTxPergunta(perguntaDetails.getTxPergunta());
            return perguntaRepository.save(pergunta);
        });
    }

    public boolean deletePergunta(Long id) {
        return perguntaRepository.findById(id).map(pergunta -> {
            perguntaRepository.delete(pergunta);
            return true;
        }).orElse(false);
    }
}