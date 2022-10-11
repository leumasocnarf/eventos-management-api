package br.com.senaceventos.Repositories;

import br.com.senaceventos.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
