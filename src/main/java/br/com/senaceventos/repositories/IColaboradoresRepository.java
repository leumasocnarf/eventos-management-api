package br.com.senaceventos.repositories;

import br.com.senaceventos.models.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IColaboradoresRepository extends JpaRepository<Colaborador, Integer> {
}
