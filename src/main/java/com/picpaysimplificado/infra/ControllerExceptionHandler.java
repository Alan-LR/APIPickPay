package com.picpaysimplificado.infra;

import com.picpaysimplificado.dtos.ExcecoesDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

//Classe para tratar exceções
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity ameacaDuplicada(DataIntegrityViolationException exception){
        ExcecoesDTO excecoesDTO = new ExcecoesDTO("Usuário já cadastrado", "400");
        return ResponseEntity.badRequest().body(excecoesDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ameaca404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity ameacaGeralExceptions(Exception exception){
        ExcecoesDTO excecoesDTO = new ExcecoesDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(excecoesDTO);
    }

}
