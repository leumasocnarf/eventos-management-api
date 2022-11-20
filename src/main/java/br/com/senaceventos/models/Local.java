package br.com.senaceventos.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Local {
    @Id
    @SequenceGenerator(name = "local_seq", sequenceName = "local_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_seq")
    @Column(name = "local_id")
    private Integer id;

    @Column(name = "local_descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "local_observacao", columnDefinition = "TEXT", nullable = false)
    private String observacao;

}
