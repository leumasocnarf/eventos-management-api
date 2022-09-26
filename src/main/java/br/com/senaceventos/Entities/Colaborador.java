package br.com.senaceventos.Entities;

import br.com.senaceventos.Entities.Enums.TipoColaborador;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Colaborador {
    @Id
    @SequenceGenerator(name = "colab_seq", sequenceName = "colab_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colab_seq")
    @Column(name = "colaborador_id")
    private Integer id;

    @Column(name = "colaborador_tipo", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoColaborador tipoColaborador;

    @Column(name = "colaborador_nome", length = 40, nullable = false)
    private String nome;

}
