package com.picpaysimplificado.domain.usuarios;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import com.picpaysimplificado.dtos.UsuarioDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
//cria um construtor com todos os parametros da classe
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Usuario(UsuarioDTO data) {
        this.primeiroNome = data.getPrimeiroNome();
        this.segundoNome = data.getSegundoNome();
        this.documento = data.getDocumento();
        this.email = data.getEmail();
        this.senha = data.getSenha();
        this.balanco = data.getBalanco();
        this.tipoUsuario = data.getTipoUsuario();
    }


}
