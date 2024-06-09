package com.guardioes.funcionarios.web.controller;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exception.MensagemErro;
import com.guardioes.funcionarios.springdoc.SpringDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.guardioes.funcionarios.service.FuncionarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController implements SpringDoc {

    private final FuncionarioService funcionarioService;


    @Override
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario) throws Exception {
        funcionarioService.cadastrar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }


    @Override
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> buscarPorCpf(@PathVariable String cpf) {

        return ResponseEntity.ok(funcionarioService.buscarPorCpf(cpf));
    }

    @Override
    @PatchMapping("/inativar/{cpf}")
    public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable String cpf) {
        return ResponseEntity.ok(funcionarioService.inativar(cpf));
    }

    @Override
    @PatchMapping("/editar/{cpf}")
    public ResponseEntity<Funcionario> editarFuncionario(@PathVariable String cpf, @Valid @RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(funcionarioService.editar(cpf, funcionario));
    }
}
