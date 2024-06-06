package com.guardioes.funcionarios.service;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfJaCadastrado;
import com.guardioes.funcionarios.exceptions.ExcecaoFuncionarioNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.guardioes.funcionarios.repository.FuncionarioRepository;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

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

    public Funcionario buscarPorId(Long id) {
        try{
            return funcionarioRepository.findById(id).orElseThrow(() -> new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado"));
        } catch (IllegalArgumentException e) {
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
    }

    public Funcionario buscarPorCpf(String cpf) {
        try{
            return (Funcionario) funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado"));
        } catch (IllegalArgumentException e) {
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
    }
}
