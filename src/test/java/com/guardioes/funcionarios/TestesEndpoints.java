package com.guardioes.funcionarios;


import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfJaCadastrado;
import com.guardioes.funcionarios.repository.FuncionarioRepository;
import com.guardioes.funcionarios.service.FuncionarioService;
import com.guardioes.funcionarios.web.controller.FuncionarioController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestesEndpoints {

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;

    @Test
    void testarCadastrarFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.GERENTE);

        when(funcionarioService.cadastrar(any(Funcionario.class))).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.cadastrar(funcionario);

        assertEquals(HttpStatus.CREATED, ((ResponseEntity<?>) response).getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(funcionario.getId(), response.getBody().getId());
        assertEquals(funcionario.getNome(), response.getBody().getNome());
        assertEquals(funcionario.getCpf(), response.getBody().getCpf());
        assertEquals(funcionario.getCargo(), response.getBody().getCargo());

        verify(funcionarioService, times(1)).cadastrar(any(Funcionario.class));
    }

    @Test
    void testarInativarFuncionario() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.CAIXA);

        when(funcionarioService.inativar(funcionario.getCpf())).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.inativarFuncionario(funcionario.getCpf());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(funcionarioService, times(1)).inativar(funcionario.getCpf());
    }

    @Test
    void testarCadastrarFuncionarioComExcecao() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.GERENTE);

        when(funcionarioService.cadastrar(any(Funcionario.class))).thenThrow(new ExcecaoCpfJaCadastrado("Funcionário já existe"));

       ExcecaoCpfJaCadastrado thrown = assertThrows(ExcecaoCpfJaCadastrado.class, () -> {
            funcionarioController.cadastrar(funcionario);
        });

        assertEquals("Funcionário já existe", thrown.getMessage());
    }

    @Test
    void testarBuscarFuncionarioPorCpf() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.CAIXA);

        when(funcionarioService.buscarPorCpf(funcionario.getCpf())).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.buscarPorCpf(funcionario.getCpf());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(funcionario.getId(), response.getBody().getId());
        assertEquals(funcionario.getNome(), response.getBody().getNome());
        assertEquals(funcionario.getCpf(), response.getBody().getCpf());
        assertEquals(funcionario.getCargo(), response.getBody().getCargo());

        verify(funcionarioService, times(1)).buscarPorCpf(funcionario.getCpf());
    }

    @Test
    void testarEditarFuncionario() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.CAIXA);

        when(funcionarioService.editar(funcionario.getCpf(), funcionario)).thenReturn(funcionario);

        ResponseEntity<Funcionario> response = funcionarioController.editarFuncionario(funcionario.getCpf(), funcionario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(funcionario.getId(), response.getBody().getId());
        assertEquals(funcionario.getNome(), response.getBody().getNome());
        assertEquals(funcionario.getCpf(), response.getBody().getCpf());
        assertEquals(funcionario.getCargo(), response.getBody().getCargo());

        verify(funcionarioService, times(1)).editar(funcionario.getCpf(), funcionario);
    }

    @Test
    void testarEditarFuncionarioComExcecao() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setCargo(Funcionario.Cargo.GERENTE);

        when(funcionarioService.editar(funcionario.getCpf(), funcionario)).thenThrow(new ExcecaoCpfJaCadastrado("Funcionário não encontrado"));

        ExcecaoCpfJaCadastrado thrown = assertThrows(ExcecaoCpfJaCadastrado.class, () -> {
            funcionarioController.editarFuncionario(funcionario.getCpf(), funcionario);
        });

        assertEquals("Funcionário não encontrado", thrown.getMessage());
    }
}
