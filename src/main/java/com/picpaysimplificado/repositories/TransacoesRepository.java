package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

}
