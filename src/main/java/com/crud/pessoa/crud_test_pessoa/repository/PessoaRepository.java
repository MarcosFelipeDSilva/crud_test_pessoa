package com.crud.pessoa.crud_test_pessoa.repository;

import com.crud.pessoa.crud_test_pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
