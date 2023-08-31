package com.picpaysimplificado.services;

import com.picpaysimplificado.dtos.TransacaoDTO;
import com.picpaysimplificado.domain.transacoes.Transacoes;
import com.picpaysimplificado.domain.usuarios.Usuario;
import com.picpaysimplificado.dtos.TransacaoReturnDTO;
import com.picpaysimplificado.repositories.TransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransacoesService {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificacoesService notificacoesService;

    public Transacoes criarTransacao(TransacaoDTO transacao) throws Exception {
        Usuario cliente = usuarioService.findUserById(transacao.getClienteId());
        Usuario lojista = usuarioService.findUserById(transacao.getLojistaId());
        usuarioService.validarTransacao(cliente, transacao.getValor());

        //vamos verificar se estamos autorizados a realizar a transferência, se não, lançamos uma exceção
        if (!this.autorizarTransacao(cliente, transacao.getValor())) {
            throw new Exception("Transação não autorizada");
        }

        Transacoes novaTransacao = new Transacoes();
        novaTransacao.setValor(transacao.getValor());
        novaTransacao.setCliente(cliente);
        novaTransacao.setLojista(lojista);
        novaTransacao.setDataTransacao(LocalDateTime.now());

        cliente.setBalanco(cliente.getBalanco().subtract(transacao.getValor()));
        lojista.setBalanco(lojista.getBalanco().add(transacao.getValor()));

        this.transacoesRepository.save(novaTransacao);
        this.usuarioService.saveUser(cliente);
        this.usuarioService.saveUser(lojista);

        this.notificacoesService.enviarNotificacao(cliente, "Transação realizada com sucesso!");
        this.notificacoesService.enviarNotificacao(lojista, "Transação recebida com sucesso!");

        return novaTransacao;
    }

    public boolean autorizarTransacao(Usuario cliente, BigDecimal valor) {
        ResponseEntity<Map> respostaAutorizacao = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (respostaAutorizacao.getStatusCode() == HttpStatus.OK) {
            String mensagem = (String) respostaAutorizacao.getBody().get("message");
            //verifica se a mensagem retornou autorizado, se sim retorna true
            return "Autorizado".equalsIgnoreCase(mensagem);
        } else return false;
    }

    public List<TransacaoReturnDTO> todasTransacoes() {
        List<Transacoes> listaT = transacoesRepository.findAll();
        List<TransacaoReturnDTO> result = listaT.stream()
                .map(transacao -> {
                    TransacaoReturnDTO obj = new TransacaoReturnDTO();
                    obj.setTransacaoId(transacao.getId());
                    obj.setValor(transacao.getValor());
                    obj.setCliente(transacao.getCliente().getPrimeiroNome() + " " + transacao.getCliente().getSegundoNome());
                    obj.setLojista(transacao.getLojista().getPrimeiroNome() + " " + transacao.getLojista().getSegundoNome());

                    return obj;
                })
                .collect(Collectors.toList());

        return result;
    }

//    public Transacoes buscarComId(Long id) {
//        return this.transacoesRepository.findById(id);
//    }
}
