package com.senai.backend.repositories;

import com.senai.backend.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaModel, Integer> {

    Optional<PessoaModel> findByCpf(String cpf);
}
