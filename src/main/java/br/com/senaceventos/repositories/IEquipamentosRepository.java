package br.com.senaceventos.repositories;

import br.com.senaceventos.models.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEquipamentosRepository extends JpaRepository<Equipamento, Integer> {
    @Query("SELECT e FROM Equipamento e JOIN e.agendas a WHERE a.id = ?1")
    List<Equipamento> findEquipamentosByAgendaId(Integer agendaId);
}
