package br.com.senaceventos.Models;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Equipamento {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private String observacao;

    public Equipamento(String descricao, String observacao) {
        this.descricao = descricao;
        this.observacao = observacao;
    }
}
