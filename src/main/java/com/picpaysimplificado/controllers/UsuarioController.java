package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.dtos.UsuarioReturnDTO;
import com.picpaysimplificado.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario user = usuarioService.criarUsuario(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioReturnDTO>> pegarTodosUsuarios() {
        List<UsuarioReturnDTO> usuarios = usuarioService.pegarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

}
