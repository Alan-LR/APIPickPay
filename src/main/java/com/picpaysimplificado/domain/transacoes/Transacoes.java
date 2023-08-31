package com.picpaysimplificado.domain.transacoes;

import com.picpaysimplificado.domain.usuarios.Usuario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transacoes")
@Table(name="transacoes")
@Getter
@Setter
@AllArgsConstructor
//Cria um construtor vázio
@NoArgsConstructor
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
