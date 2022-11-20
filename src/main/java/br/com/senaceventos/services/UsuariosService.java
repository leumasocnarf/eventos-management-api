package br.com.senaceventos.services;

import br.com.senaceventos.models.Usuario;
import br.com.senaceventos.exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.exceptions.RegisterNotFoundException;
import br.com.senaceventos.repositories.IUsuariosRepository;
import br.com.senaceventos.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuariosService implements IBaseService<Usuario> {

    private final IUsuariosRepository usuariosRepository;
    
    @Autowired
    public UsuariosService(IUsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuario> retrieveAll() {
        var usuarioList = usuariosRepository.findAll();

        if (usuarioList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha usuarios registrados.");
        }
        return usuarioList;
    }

    @Override
    public Usuario retrieveById(Integer usuarioId) {
        return usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse usuario nao esta registrado."));
    }

    @Override
    public void append(Usuario usuario) {
        if (usuario == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        usuariosRepository.save(usuario);
    }

    @Override
    @Transactional
    public void alter(Integer usuarioId, Usuario newUsuario) {
        var oldUsuario = usuariosRepository
                .findById(usuarioId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        if (newUsuario == null ||
                newUsuario.getNome().isEmpty() &&
                newUsuario.getEmail().isEmpty() &&
                newUsuario.getSenha().isEmpty() &&
                newUsuario.getTelefone().isEmpty()) {
            throw new InvalidParametersAtRequestBodyException("Campos invalidos.");
        }

        oldUsuario.setNome(newUsuario.getNome());
        oldUsuario.setEmail(newUsuario.getEmail());
        oldUsuario.setSenha(newUsuario.getSenha());
        oldUsuario.setTelefone(newUsuario.getTelefone());

    }

    @Override
    public void remove(Integer usuarioId) {
        var usuario = usuariosRepository.findById(usuarioId);

        if (usuario.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        usuariosRepository.deleteById(usuarioId);
    }
}
