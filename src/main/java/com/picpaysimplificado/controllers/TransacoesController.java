package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transacoes.Transacoes;
import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.dtos.TransacaoReturnDTO;
import com.picpaysimplificado.dtos.UsuarioDTO;
import com.picpaysimplificado.services.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TransacaoReturnDTO>> todasTransacoes(){
        List<TransacaoReturnDTO> result = this.transacoesService.todasTransacoes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<Transacoes> buscarComId(Long id){
//        Transacoes result = this.transacoesService.buscarComId(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


}
