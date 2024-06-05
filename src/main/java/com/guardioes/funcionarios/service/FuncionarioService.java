package com.guardioes.funcionarios.service;

import com.guardioes.funcionarios.entity.Funcionario;
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
                        throw new IllegalArgumentException("CPF já cadastrado");
                    });
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return funcionarioRepository.save(funcionario);
    }
}
