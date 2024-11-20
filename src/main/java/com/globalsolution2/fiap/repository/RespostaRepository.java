package com.globalsolution2.fiap.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalsolution2.fiap.model.RespostaModel;

@Repository
public interface RespostaRepository extends JpaRepository<RespostaModel, Long> {
}
