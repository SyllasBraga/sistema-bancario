package com.senai.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import com.senai.backend.api.request.AtualizarPessoaRequest;
import com.senai.backend.api.request.CriarPessoaRequest;
import com.senai.backend.api.response.PessoaResponse;
import com.senai.backend.exceptions.BadRequestException;
import com.senai.backend.exceptions.NotFoundException;
import com.senai.backend.models.PessoaModel;
import com.senai.backend.repositories.PessoaRepository;
import com.senai.backend.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

class PessoaServiceImplTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    private CriarPessoaRequest criarPessoaRequest;
    private PessoaModel pessoaModel;
    private PessoaResponse pessoaResponse;

    private static final String CPF = "12345678901";
    private static final Integer ID_PESSOA = 1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        criarPessoaRequest = new CriarPessoaRequest();
        criarPessoaRequest.setCpf(CPF);
        criarPessoaRequest.setNome("João Silva");

        pessoaModel = new PessoaModel();
        pessoaModel.setIdPessoa(ID_PESSOA);
        pessoaModel.setCpf(CPF);
        pessoaModel.setNome("João Silva");

        pessoaResponse = new PessoaResponse();
        pessoaResponse.setIdPessoa(ID_PESSOA);
        pessoaResponse.setCpf(CPF);
        pessoaResponse.setNome("João Silva");
    }

    @Test
    void whenCriarPessoaSuccess() {
        when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        when(pessoaRepository.save(any(PessoaModel.class))).thenReturn(pessoaModel);

        PessoaResponse result = pessoaService.criarPessoa(criarPessoaRequest);

        assertNotNull(result);
        assertEquals(ID_PESSOA, result.getIdPessoa());
        assertEquals(CPF, result.getCpf());
        verify(pessoaRepository, times(1)).findByCpf(CPF);
        verify(pessoaRepository, times(1)).save(any(PessoaModel.class));
    }

    @Test
    void whenCriarPessoaThrowsCpfJaCadastrado() {
        when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.of(pessoaModel));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> pessoaService.criarPessoa(criarPessoaRequest));

        assertEquals("Pessoa já cadastrada", exception.getMessage());
        verify(pessoaRepository, times(1)).findByCpf(CPF);
        verify(pessoaRepository, never()).save(any(PessoaModel.class));
    }

    @Test
    void whenListarPessoasSuccess() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PessoaModel> page = new PageImpl<>(Collections.singletonList(pessoaModel), pageable, 1);
        when(pessoaRepository.findAll(pageable)).thenReturn(page);

        Page<PessoaResponse> result = pessoaService.listarPessoas(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(CPF, result.getContent().get(0).getCpf());
        verify(pessoaRepository, times(1)).findAll(pageable);
    }

    @Test
    void whenAtualizarPessoaSuccess() {
        AtualizarPessoaRequest atualizarPessoaRequest = new AtualizarPessoaRequest();
        atualizarPessoaRequest.setNome("João Atualizado");

        when(pessoaRepository.findById(ID_PESSOA)).thenReturn(Optional.of(pessoaModel));
        when(pessoaRepository.save(any(PessoaModel.class))).thenReturn(pessoaModel);

        PessoaResponse result = pessoaService.atualizarPessoa(ID_PESSOA, atualizarPessoaRequest);

        assertNotNull(result);
        assertEquals(ID_PESSOA, result.getIdPessoa());
        verify(pessoaRepository, times(1)).findById(ID_PESSOA);
        verify(pessoaRepository, times(1)).save(pessoaModel);
    }

    @Test
    void whenAtualizarPessoaThrowsNotFound() {
        AtualizarPessoaRequest atualizarPessoaRequest = new AtualizarPessoaRequest();
        atualizarPessoaRequest.setNome("João Atualizado");

        when(pessoaRepository.findById(ID_PESSOA)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> pessoaService.atualizarPessoa(ID_PESSOA, atualizarPessoaRequest));

        assertEquals("Pessoa não encontrada", exception.getMessage());
        verify(pessoaRepository, times(1)).findById(ID_PESSOA);
        verify(pessoaRepository, never()).save(any(PessoaModel.class));
    }

    @Test
    void whenDeletarPessoaSuccess() {
        when(pessoaRepository.findById(ID_PESSOA)).thenReturn(Optional.of(pessoaModel));
        doNothing().when(pessoaRepository).delete(pessoaModel);

        pessoaService.deletarPessoa(ID_PESSOA);

        verify(pessoaRepository, times(1)).findById(ID_PESSOA);
        verify(pessoaRepository, times(1)).delete(pessoaModel);
    }

    @Test
    void whenDeletarPessoaThrowsNotFound() {
        when(pessoaRepository.findById(ID_PESSOA)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> pessoaService.deletarPessoa(ID_PESSOA));

        assertEquals("Pessoa não encontrada", exception.getMessage());
        verify(pessoaRepository, times(1)).findById(ID_PESSOA);
        verify(pessoaRepository, never()).delete(any(PessoaModel.class));
    }
}

