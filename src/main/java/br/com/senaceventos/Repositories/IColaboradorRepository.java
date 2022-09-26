package br.com.senaceventos.Repositories;

import br.com.senaceventos.Entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
