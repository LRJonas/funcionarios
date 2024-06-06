package com.guardioes.funcionarios.service;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exception.MensagemErro;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfJaCadastrado;
import com.guardioes.funcionarios.exceptions.ExcecaoFuncionarioNaoEncontrado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.guardioes.funcionarios.repository.FuncionarioRepository;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Cadastrar um novo funcionário",
            description = "Endpoint que cadastra um novo funcionário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class))),
            @ApiResponse(responseCode = "409", description = "Funcionário já existente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    public Funcionario cadastrar(Funcionario funcionario) {
        try {
            funcionarioRepository.findByCpf(funcionario.getCpf())
                    .ifPresent(f -> {
                        throw new ExcecaoCpfJaCadastrado("CPF já cadastrado");
                    });
        } catch (IllegalArgumentException e) {
            throw new ExcecaoCpfJaCadastrado("CPF já cadastrado");
        }
        return funcionarioRepository.save(funcionario);
    }

    @Operation(summary = "Buscar funcionário por ID",
            description = "Endpoint que busca um funcionário por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    public Funcionario buscarPorId(Long id) {
        try{
            return funcionarioRepository.findById(id).orElseThrow(() -> new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado"));
        } catch (IllegalArgumentException e) {
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
    }

    @Operation(summary = "Buscar funcionário por CPF",
            description = "Endpoint que busca um funcionário por CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MensagemErro.class)))
    })
    public Funcionario buscarPorCpf(String cpf) {
        try{
            return (Funcionario) funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado"));
        } catch (IllegalArgumentException e) {
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
    }
}
