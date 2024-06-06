package com.guardioes.funcionarios.exception;

import com.guardioes.funcionarios.exceptions.ExcecaoCampoForaEnum;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfJaCadastrado;
import com.guardioes.funcionarios.exceptions.ExcecaoFuncionarioNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExcecaoManipulador extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExcecaoCpfJaCadastrado.class)
    public final ResponseEntity<MensagemErro> excecaoCpfDuplicado(ExcecaoCpfJaCadastrado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoFuncionarioNaoEncontrado.class)
    public final ResponseEntity<MensagemErro> excecaoPropostaInexistente(ExcecaoFuncionarioNaoEncontrado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoCampoForaEnum.class)
    public final ResponseEntity<MensagemErro> excecaoCampoForaEnum(ExcecaoCampoForaEnum ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
