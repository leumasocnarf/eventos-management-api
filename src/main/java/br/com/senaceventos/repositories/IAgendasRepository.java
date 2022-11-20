package br.com.senaceventos.repositories;

import br.com.senaceventos.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAgendasRepository extends JpaRepository<Agenda, Integer> {
    @Query("SELECT a FROM Agenda a JOIN a.equipamentos e WHERE e.id = ?1")
    List<Agenda> findAgendasByEquipamentosId(Integer equipamentoId);
}
