package com.senai.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.api.models.MovimentacaoModel;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Integer>{
    
}
