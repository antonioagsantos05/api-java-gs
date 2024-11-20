package com.globalsolution2.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalsolution2.fiap.model.PerguntaModel;

@Repository
public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {
}
