package com.guardioes.funcionarios.service;

import com.guardioes.funcionarios.entity.Funcionario;
import com.guardioes.funcionarios.exceptions.ExcecaoCampoForaEnum;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfInvalido;
import com.guardioes.funcionarios.exceptions.ExcecaoCpfJaCadastrado;
import com.guardioes.funcionarios.exceptions.ExcecaoFuncionarioNaoEncontrado;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import com.guardioes.funcionarios.repository.FuncionarioRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario cadastrar(Funcionario funcionario) {
        try {
            funcionarioRepository.findByCpf(funcionario.getCpf())
                    .ifPresent(f -> {
                        throw new ExcecaoCpfJaCadastrado("CPF já cadastrado");
                    });
        } catch (ConstraintViolationException e) {
            throw new ExcecaoCpfInvalido("CPF inválido");
        } catch (HttpMessageNotReadableException e){
            throw new ExcecaoCampoForaEnum("Cargo inválido");
        }
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario buscarPorCpf(String cpf) {
        try{
            return (Funcionario) funcionarioRepository.findByCpf(cpf).orElseThrow(() -> new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado"));
        } catch (IllegalArgumentException e) {
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
    }

    @Transactional
    public Funcionario inativar(String cpf) {
        Funcionario funcionario = buscarPorCpf(cpf);
        if(funcionario == null){
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
        if(funcionario.getAtivo().equals(true)){
            funcionario.setAtivo(false);
        } else {
            funcionario.setAtivo(true);
        }
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario editar(String cpf, Funcionario funcionario) {
        Funcionario funcionarioEncontrado = buscarPorCpf(cpf);
        if(funcionarioEncontrado == null){
            throw new ExcecaoFuncionarioNaoEncontrado("Funcionário não encontrado");
        }
        funcionarioEncontrado.setNome(funcionario.getNome());
        funcionarioEncontrado.setCargo(funcionario.getCargo());
        return funcionarioRepository.save(funcionarioEncontrado);
    }
}
