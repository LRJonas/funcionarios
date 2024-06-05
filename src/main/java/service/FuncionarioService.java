package service;

import entity.Funcionario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.FuncionarioRepository;

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
