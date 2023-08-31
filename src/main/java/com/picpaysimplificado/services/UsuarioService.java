package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.usuarios.TipoUsuario;
import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.dtos.UsuarioReturnDTO;
import com.picpaysimplificado.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public void validarTransacao(Usuario cliente, BigDecimal valor) throws Exception {
        if (cliente.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar uma transação");
        }
        if (cliente.getBalanco().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public Usuario findUserById(Long id) throws Exception {
        //tento retornar meu usuário, caso não encontre lanço uma exceção
        return this.usuariosRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(Usuario usuario) {
        this.usuariosRepository.save(usuario);
    }

    public Usuario criarUsuario(UsuarioDTO data) {
        Usuario novoUsuario = new Usuario(data);
        this.saveUser(novoUsuario);
        return novoUsuario;
    }

    //Retornando todos usuários, SOMENTE informações que queremos
    public List<UsuarioReturnDTO> pegarTodosUsuarios() {
        List<Usuario> usuarios = usuariosRepository.findAll();
        List<UsuarioReturnDTO> usuariosReturnDTOS = usuarios.stream()
                .map(usuario -> {
                    UsuarioReturnDTO user = new UsuarioReturnDTO();
                    user.setPrimeiroNome(usuario.getPrimeiroNome());
                    user.setSegundoNome(usuario.getSegundoNome());
                    user.setDocumento(usuario.getDocumento());
                    user.setEmail(usuario.getEmail());
                    user.setTipoUsuario(usuario.getTipoUsuario());
                    user.setBalanco(usuario.getBalanco());
                    return user;
                })
                .collect(Collectors.toList());
        return usuariosReturnDTOS;
    }
}
