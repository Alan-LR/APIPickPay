package com.picpaysimplificado.domain.usuarios;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="usuarios")
@Table(name="usuarios")
@Getter
@Setter
//cria um construtor com todos os parametros da classe
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {
    @Id
    //gera de forma incremental, 1,2,3,4...
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String primeiroNome;
    private String segundoNome;
    //Unique true quer dizer que o valor do atributo não pode se repetir
    @Column(unique = true)
    private String documento;
    @Column(unique = true)
    private String email;
    private String senha;
    private BigDecimal balanco;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
//    @OneToMany
//    private Transacoes transacao;





}
