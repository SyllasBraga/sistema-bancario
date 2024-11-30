package com.senai.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.api.models.ContaModel;

public interface ContaRepository extends JpaRepository<ContaModel, Integer>{
    
    Optional<ContaModel> findByConta(String conta);
    List<ContaModel> findByCpf(String cpf);
    Page<ContaModel> findAll(Pageable pageable);
}
