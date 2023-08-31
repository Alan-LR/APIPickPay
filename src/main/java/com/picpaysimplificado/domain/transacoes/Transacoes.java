package com.picpaysimplificado.domain.transacoes;

import com.picpaysimplificado.domain.usuarios.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transacoes")
@Table(name="transacoes")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Usuario cliente;
    @ManyToOne
    @JoinColumn(name="lojista_id")
    private Usuario lojista;
    private LocalDateTime dataTransacao;


}
