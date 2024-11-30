package com.senai.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.api.models.CepModel;

import java.util.Optional;

public interface CepRepository extends JpaRepository<CepModel, Integer>{

    Optional<CepModel> findByCep(String cep);
}
