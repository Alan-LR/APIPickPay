package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.services.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @Autowired
    private TransacoesService transacoesService;

    @PostMapping
    public ResponseEntity<Transacoes> criarTransacao(@RequestBody TransacaoDTO transacao) throws Exception {
        Transacoes info = this.transacoesService.criarTransacao(transacao);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }


}
