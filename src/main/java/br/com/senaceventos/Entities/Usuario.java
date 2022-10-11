package br.com.senaceventos.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "usuario_nome", length = 40, nullable = false)
    private String nome;

    @Column(name = "usuario_email", length = 40, nullable = false)
    private String email;

    @Column(name = "usuario_senha", length = 40, nullable = false)
    private String senha;

    @Column(name = "usuario_telefone", length = 12, nullable = false)
    private String telefone;

}
