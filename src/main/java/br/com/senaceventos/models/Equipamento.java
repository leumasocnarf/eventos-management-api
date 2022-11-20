package br.com.senaceventos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipamento {
    @Id
    @SequenceGenerator(name = "equip_seq", sequenceName = "equip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equip_seq")
    @Column(name = "equipamento_id")
    private Integer id;

    @Column(name = "equipamento_descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "equipamento_observacao", columnDefinition = "TEXT", nullable = false)
    private String observacao;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE},
            mappedBy = "equipamentos")
    @JsonIgnore
    private Set<Agenda> agendas = new HashSet<>();

}
