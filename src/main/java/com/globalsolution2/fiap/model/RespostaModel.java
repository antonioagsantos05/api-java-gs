package com.globalsolution2.fiap.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_resposta")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idResposta")
public class RespostaModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta")
    private Long idResposta;
    
	@Column(name = "id_pergunta")
	private Long idPergunta;
 
    @Column(name = "tx_resposta", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String txResposta;
    
    @JsonProperty("isCorreta")
    @Column(name = "is_correta", nullable = false)    
    private boolean isCorreta;
    
    

}
