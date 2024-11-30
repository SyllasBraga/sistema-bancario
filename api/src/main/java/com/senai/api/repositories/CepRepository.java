package com.senai.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.api.models.CepModel;

public interface CepRepository extends JpaRepository<CepModel, Integer>{
    
}
