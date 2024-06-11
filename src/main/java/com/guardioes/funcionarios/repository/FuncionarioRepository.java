package com.guardioes.funcionarios.repository;

import com.guardioes.funcionarios.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Object> findByCpf(String cpf);
}
