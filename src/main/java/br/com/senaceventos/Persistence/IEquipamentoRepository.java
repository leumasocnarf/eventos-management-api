package br.com.senaceventos.Persistence;

import br.com.senaceventos.Models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}
