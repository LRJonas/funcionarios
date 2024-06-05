package web.controller;

import entity.Funcionario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FuncionarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionario) {
        funcionarioService.cadastrar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }


}
