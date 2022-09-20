package br.com.senaceventos.Repositories;

import br.com.senaceventos.Entities.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipamentoRepository extends JpaRepository<Equipamento, Integer> {
}
