package br.com.senaceventos.Repositories;

import br.com.senaceventos.Entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocalRepository extends JpaRepository<Local, Integer> {
}
