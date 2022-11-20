package br.com.senaceventos.repositories;

import br.com.senaceventos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosRepository extends JpaRepository<Usuario, Integer> {
}
