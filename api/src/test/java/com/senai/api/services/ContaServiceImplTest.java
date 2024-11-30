package com.senai.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import com.senai.api.api.requests.CriarContaRequest;
import com.senai.api.api.responses.CriarContaResponse;
import com.senai.api.api.responses.ListarContaResponse;
import com.senai.api.exceptions.BadRequestException;
import com.senai.api.exceptions.NoContentException;
import com.senai.api.exceptions.NotFoundException;
import com.senai.api.mappers.ContaMapper;
import com.senai.api.models.ContaModel;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.ContaRepository;
import com.senai.api.services.impl.ContaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ContaServiceImplTest {

    @InjectMocks
    private ContaServiceImpl contaService;

    @Mock
    private ContaRepository contaRepository;

    private CriarContaRequest criarContaRequest;
    private ContaModel contaModel;
    private CriarContaResponse criarContaResponse;

    private static final String CPF = "12345678901";
    private static final String NUMERO_CONTA = "12345";
    private static final Integer ID_CONTA = 1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        criarContaRequest = new CriarContaRequest();
        criarContaRequest.setCpf(CPF);
        criarContaRequest.setConta(NUMERO_CONTA);

        contaModel = new ContaModel();
        contaModel.setIdConta(ID_CONTA);
        contaModel.setCpf(CPF);
        contaModel.setConta(NUMERO_CONTA);

        criarContaResponse = new CriarContaResponse();
        criarContaResponse.setId(ID_CONTA.toString());
        criarContaResponse.setConta(NUMERO_CONTA);
        criarContaResponse.setMensagem(MensagensEnum.CONTA_CRIADA_COM_SUCESSO.getMensagem());
    }

    @Test
    void whenCriarContaSuccess() {
        when(contaRepository.findByConta(NUMERO_CONTA)).thenReturn(Optional.empty());
        when(contaRepository.save(any(ContaModel.class))).thenReturn(contaModel);

        CriarContaResponse result = contaService.criarConta(criarContaRequest);

        assertNotNull(result);
        assertEquals(ID_CONTA.toString(), result.getId());
        assertEquals(NUMERO_CONTA, result.getConta());
        verify(contaRepository, times(1)).findByConta(NUMERO_CONTA);
        verify(contaRepository, times(1)).save(any(ContaModel.class));
    }

    @Test
    void whenCriarContaAlreadyExists() {
        when(contaRepository.findByConta(NUMERO_CONTA)).thenReturn(Optional.of(contaModel));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> contaService.criarConta(criarContaRequest));

        assertEquals(MensagensEnum.CONTA_JA_EXISTE.getMensagem(), exception.getMessage());
        verify(contaRepository, times(1)).findByConta(NUMERO_CONTA);
        verify(contaRepository, never()).save(any(ContaModel.class));
    }

    @Test
    void whenListarContasPorCpfSuccess() {
        when(contaRepository.findByCpf(CPF)).thenReturn(List.of(contaModel));

        List<ListarContaResponse> result = contaService.listarContasPorCpf(CPF);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(NUMERO_CONTA, result.get(0).getConta());
        verify(contaRepository, times(1)).findByCpf(CPF);
    }

    @Test
    void whenListarContasPorCpfNoContent() {
        when(contaRepository.findByCpf(CPF)).thenReturn(Collections.emptyList());

        NoContentException exception = assertThrows(NoContentException.class,
                () -> contaService.listarContasPorCpf(CPF));

        assertEquals(null, exception.getMessage());
        verify(contaRepository, times(1)).findByCpf(CPF);
    }

    @Test
    void whenListarContasSuccess() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ContaModel> page = new PageImpl<>(List.of(contaModel), pageRequest, 1);
        when(contaRepository.findAll(pageRequest)).thenReturn(page);

        Page<ListarContaResponse> result = contaService.listarContas(pageRequest);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(NUMERO_CONTA, result.getContent().get(0).getConta());
        verify(contaRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void whenDeletarContaSuccess() {
        when(contaRepository.findByConta(NUMERO_CONTA)).thenReturn(Optional.of(contaModel));

        contaService.deletarConta(NUMERO_CONTA);

        verify(contaRepository, times(1)).findByConta(NUMERO_CONTA);
        verify(contaRepository, times(1)).delete(contaModel);
    }

    @Test
    void whenDeletarContaNotFound() {
        when(contaRepository.findByConta(NUMERO_CONTA)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> contaService.deletarConta(NUMERO_CONTA));

        assertEquals(MensagensEnum.CONTA_NAO_ENCONTRADA.getMensagem(), exception.getMessage());
        verify(contaRepository, times(1)).findByConta(NUMERO_CONTA);
    }
}
