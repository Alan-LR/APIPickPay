package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

     Optional<Transacoes> findById(Long id);



}
