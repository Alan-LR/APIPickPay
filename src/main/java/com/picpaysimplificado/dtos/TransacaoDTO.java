package com.picpaysimplificado.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoDTO {

    private BigDecimal valor;
    private Long clienteId;
    private Long lojistaId;

}
