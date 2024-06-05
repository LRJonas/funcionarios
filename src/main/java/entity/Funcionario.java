package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "funcionarios")
public class Funcionario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(unique = true)
    @CPF
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;


    public enum Cargo {
        GERENTE,
        VENDEDOR,
        CAIXA
    }

}
