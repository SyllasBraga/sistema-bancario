package com.senai.api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.senai.api.api.requests.CriarCepRequest;
import com.senai.api.api.responses.CriarCepResponse;
import com.senai.api.api.responses.ListarCepResponse;
import com.senai.api.exceptions.BadRequestException;
import com.senai.api.models.CepModel;
import com.senai.api.models.enums.MensagensEnum;
import com.senai.api.repositories.CepRepository;
import com.senai.api.services.impl.CepServiceImpl;

class CepServiceImplTest {

    @InjectMocks
    private CepServiceImpl cepService;

    @Mock
    private CepRepository cepRepository;

    private CriarCepRequest criarCepRequest;
    private CepModel cepModel;
    private CriarCepResponse criarCepResponse;
    private ListarCepResponse listarCepResponse;

    private static final String CEP_VALIDO = "12345678";
    private static final Integer CEP_ID = 1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        initMocks();
    }

    private void initMocks() {
        criarCepRequest = new CriarCepRequest();
        criarCepRequest.setCep(CEP_VALIDO);

        cepModel = new CepModel();
        cepModel.setIdCep(CEP_ID);
        cepModel.setCep(CEP_VALIDO);

        criarCepResponse = new CriarCepResponse();
        criarCepResponse.setCep(CEP_VALIDO);
        criarCepResponse.setMensagem(MensagensEnum.CEP_CRIADO_COM_SUCESSO.getMensagem());

        listarCepResponse = new ListarCepResponse();
        listarCepResponse.setCep(CEP_VALIDO);
    }

    @Test
    void whenCriarCepReturnsSuccessResponse() {
        when(cepRepository.findByCep(CEP_VALIDO)).thenReturn(Optional.empty());
        when(cepRepository.save(any(CepModel.class))).thenReturn(cepModel);

        CriarCepResponse result = cepService.criarCep(criarCepRequest);

        ArgumentCaptor<CepModel> captor = ArgumentCaptor.forClass(CepModel.class);
        verify(cepRepository, times(1)).save(captor.capture());
        CepModel savedModel = captor.getValue();

        assertNotNull(savedModel);
        assertEquals(CEP_VALIDO, savedModel.getCep());
        assertEquals(CEP_ID.toString(), result.getId());
        assertEquals(MensagensEnum.CEP_CRIADO_COM_SUCESSO.getMensagem(), result.getMensagem());

        verify(cepRepository, times(1)).findByCep(CEP_VALIDO);
    }

    @Test
    void whenCriarCepThrowsBadRequestExceptionForExistingCep() {
        when(cepRepository.findByCep(CEP_VALIDO)).thenReturn(Optional.of(cepModel));

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> cepService.criarCep(criarCepRequest));

        assertEquals(MensagensEnum.CEP_JA_CADASTRADO.getMensagem(), exception.getMessage());
        verify(cepRepository, times(1)).findByCep(CEP_VALIDO);
        verify(cepRepository, never()).save(any(CepModel.class));
    }

    @Test
    void whenBuscarCepReturnsCepResponse() {
        when(cepRepository.findByCep(CEP_VALIDO)).thenReturn(Optional.of(cepModel));

        ListarCepResponse result = cepService.buscarCep(CEP_VALIDO);

        assertNotNull(result);
        assertEquals(CEP_VALIDO, result.getCep());
        verify(cepRepository, times(1)).findByCep(CEP_VALIDO);
    }

    @Test
    void whenBuscarCepThrowsRuntimeExceptionForNonExistentCep() {
        when(cepRepository.findByCep(CEP_VALIDO)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> cepService.buscarCep(CEP_VALIDO));

        assertEquals(MensagensEnum.CEP_NAO_ENCONTRADO.getMensagem(), exception.getMessage());
        verify(cepRepository, times(1)).findByCep(CEP_VALIDO);
    }
}
