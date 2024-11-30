package com.senai.api.repositories;

import com.senai.api.models.ContaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.api.models.MovimentacaoModel;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Integer>{

    Page<MovimentacaoModel> findByConta(ContaModel conta, Pageable pageable);
}
