package com.guardioes.funcionarios.springdoc;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exception.MensagemErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Funcionários", description = "Contém todas as operações relativas aos recursos para cadastrar, buscar, inativar e editar um funcionário")
public interface SpringDoc {
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
    ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody Funcionario funcionario) throws Exception;

    @Operation(summary = "Buscar funcionário por CPF",
            description = "Endpoint que busca um funcionário por CPF.")
    @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class)))
    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    ResponseEntity<Funcionario> buscarPorCpf(@PathVariable String cpf);

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
    ResponseEntity<Funcionario> inativarFuncionario(@PathVariable String cpf);

    @Operation(summary = "Editar funcionário",
            description = "Endpoint que edita um funcionário.")
    @ApiResponse(responseCode = "200", description = "Funcionário editado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class)))
    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MensagemErro.class)))
    ResponseEntity<Funcionario> editarFuncionario(@PathVariable String cpf, @Valid @RequestBody Funcionario funcionario);
}
