package com.picpaysimplificado.services;

import com.picpaysimplificado.dtos.NotificacaoDTO;
import com.picpaysimplificado.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacoesService {
    @Autowired
    private RestTemplate restTemplate;

    public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
        String email = usuario.getEmail();
        NotificacaoDTO solicitarNotificacao = new NotificacaoDTO(email, mensagem);

//        ResponseEntity<String> respostaNotificacao = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", solicitarNotificacao, String.class);
//
//        if (!(respostaNotificacao.getStatusCode() == HttpStatus.OK)) {
//            System.out.println("Erro ao enviar notificação");
//            throw new Exception("Serviço de notificação está fora do ar");
//        }

        System.out.println("Notificação enviada para o usuário: " + usuario.getEmail() + " ,ele é um:" + usuario.getTipoUsuario());
    }
}
