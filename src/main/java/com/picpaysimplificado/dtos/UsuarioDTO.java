package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.usuarios.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String primeiroNome;
    private String segundoNome;
    private String documento;
    private BigDecimal balanco;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
}
