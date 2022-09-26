package br.com.senaceventos.Entities;


import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "agenda_id")
    private Integer id;

    @Column(name = "evento_titulo", length = 40, nullable = false)
    private String titulo;

    @Column(name = "data_inicio", nullable = false)
    private Date inicio;

    @Column(name = "data_termino", nullable = false)
    private Date termino;

    @Column(name = "hora_inicio", nullable = false)
    private Time horaInicio;

    @Column(name = "hora_termino", nullable = false)
    private Time horaTermino;

    @Column(name = "agenda_observacao", columnDefinition = "TEXT", nullable = false)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "agenda_equipamentos",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private Set<Equipamento> equipamentos = new HashSet<>();

    public void reservarEquipamento(Equipamento equipamento) {
        this.equipamentos.add(equipamento);
        equipamento.getAgendas().add(this);
    }

    public void removerEquipamento(Integer equipamentoId) {
        var equipamento = this.equipamentos.stream()
                .filter(equipamento1 -> Objects.equals(equipamento1.getId(), equipamentoId))
                .findFirst()
                .orElse(null);

        if (equipamento != null) {
            this.equipamentos.remove(equipamento);
            equipamento.getAgendas().remove(this);
        }
    }
}
