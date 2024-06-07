package com.guardioes.funcionarios.web.controller;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exception.MensagemErro;
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
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Operation(summary = "Cadastrar um novo funcionário",
            description = "Endpoint que cadastra um novo funcionário.")
    @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @ApiResponse(responseCode = "409", description = "Funcionário já existente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario) {
        funcionarioService.cadastrar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }


    @Operation(summary = "Buscar funcionário por CPF",
            description = "Endpoint que busca um funcionário por CPF.")
    @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class)))
    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> buscarPorCpf(@PathVariable String cpf) {

        return ResponseEntity.ok(funcionarioService.buscarPorCpf(cpf));
    }

    @Operation(summary = "Inativar funcionário",
            description = "Endpoint que inativa um funcionário.")
    @ApiResponse(responseCode = "200", description = "Funcionário inativado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class)))
    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @PatchMapping("/inativar/{id}")
    public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable String cpf) {
        return ResponseEntity.ok(funcionarioService.inativar(cpf));
    }
}
