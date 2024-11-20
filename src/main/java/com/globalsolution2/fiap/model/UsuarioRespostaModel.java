package com.globalsolution2.fiap.model;

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
@Table(name = "tb_usuario_resposta")
public class UsuarioRespostaModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_resposta")
    private Long idUsuarioResposta;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "id_pergunta", nullable = false)
    private Long idPergunta;
    
    @Column(name = "id_resposta", nullable = false)
    private Long idResposta;

    @Column(name = "st_resultado")
    private boolean stResultado;

}
