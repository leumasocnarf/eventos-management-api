package br.com.senaceventos.repositories;

import br.com.senaceventos.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocaisRepository extends JpaRepository<Local, Integer> {
}
