package com.globalsolution2.fiap.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.globalsolution2.fiap.model.PerguntaModel;
import com.globalsolution2.fiap.repository.PerguntaRepository;
import com.globalsolution2.fiap.service.PerguntaService;

class PerguntaServiceTest {

    @InjectMocks
    private PerguntaService perguntaService;

    @Mock
    private PerguntaRepository perguntaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePergunta() {
        PerguntaModel pergunta = new PerguntaModel();
        pergunta.setIdPergunta(1L);
        pergunta.setNmTitulo("Título Teste");
        pergunta.setTxPergunta("Texto da Pergunta");

        when(perguntaRepository.save(pergunta)).thenReturn(pergunta);

        PerguntaModel createdPergunta = perguntaService.createPergunta(pergunta);

        assertNotNull(createdPergunta);
        assertEquals("Título Teste", createdPergunta.getNmTitulo());
        assertEquals("Texto da Pergunta", createdPergunta.getTxPergunta());
        verify(perguntaRepository, times(1)).save(pergunta);
    }

    @Test
    void testGetAllPerguntas() {
        PerguntaModel pergunta1 = new PerguntaModel();
        PerguntaModel pergunta2 = new PerguntaModel();

        when(perguntaRepository.findAll()).thenReturn(List.of(pergunta1, pergunta2));

        List<PerguntaModel> perguntas = perguntaService.getAllPerguntas();

        assertNotNull(perguntas);
        assertEquals(2, perguntas.size());
        verify(perguntaRepository, times(1)).findAll();
    }

    @Test
    void testGetPerguntaById() {
        PerguntaModel pergunta = new PerguntaModel();
        pergunta.setIdPergunta(1L);
        pergunta.setNmTitulo("Título Teste");

        when(perguntaRepository.findById(1L)).thenReturn(Optional.of(pergunta));

        Optional<PerguntaModel> foundPergunta = perguntaService.getPerguntaById(1L);

        assertTrue(foundPergunta.isPresent());
        assertEquals("Título Teste", foundPergunta.get().getNmTitulo());
        verify(perguntaRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdatePergunta() {
        PerguntaModel existingPergunta = new PerguntaModel();
        existingPergunta.setIdPergunta(1L);
        existingPergunta.setNmTitulo("Título Antigo");

        PerguntaModel updatedDetails = new PerguntaModel();
        updatedDetails.setNmTitulo("Título Atualizado");

        when(perguntaRepository.findById(1L)).thenReturn(Optional.of(existingPergunta));
        when(perguntaRepository.save(existingPergunta)).thenReturn(existingPergunta);

        Optional<PerguntaModel> updatedPergunta = perguntaService.updatePergunta(1L, updatedDetails);

        assertTrue(updatedPergunta.isPresent());
        assertEquals("Título Atualizado", updatedPergunta.get().getNmTitulo());
        verify(perguntaRepository, times(1)).findById(1L);
        verify(perguntaRepository, times(1)).save(existingPergunta);
    }

    @Test
    void testDeletePergunta() {
        PerguntaModel pergunta = new PerguntaModel();
        pergunta.setIdPergunta(1L);

        when(perguntaRepository.findById(1L)).thenReturn(Optional.of(pergunta));
        doNothing().when(perguntaRepository).delete(pergunta);

        boolean isDeleted = perguntaService.deletePergunta(1L);

        assertTrue(isDeleted);
        verify(perguntaRepository, times(1)).findById(1L);
        verify(perguntaRepository, times(1)).delete(pergunta);
    }

    @Test
    void testDeletePergunta_NotFound() {
        when(perguntaRepository.findById(1L)).thenReturn(Optional.empty());

        boolean isDeleted = perguntaService.deletePergunta(1L);

        assertFalse(isDeleted);
        verify(perguntaRepository, times(1)).findById(1L);
        verify(perguntaRepository, never()).delete(any());
    }
}
