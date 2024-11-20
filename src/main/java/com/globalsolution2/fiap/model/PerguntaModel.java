package com.globalsolution2.fiap.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_pergunta")
public class PerguntaModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pergunta")
    private Long idPergunta;

    @Column(name = "nm_titulo", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String nmTitulo;

    @Column(name = "tx_pergunta", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String txPergunta;
    
    @OneToMany(mappedBy = "idPergunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RespostaModel> respostas;

}
