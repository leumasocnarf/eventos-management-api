package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Usuario;
import br.com.senaceventos.Exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.Exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.Exceptions.RegisterNotFoundException;
import br.com.senaceventos.Repositories.IUsuarioRepository;
import br.com.senaceventos.Services.Common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService implements IBaseService<Usuario> {

    private final IUsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> retrieveAll() {
        var usuarioList = usuarioRepository.findAll();

        if (usuarioList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha usuarios registrados.");
        }
        return usuarioList;
    }

    @Override
    public Usuario retrieveById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse usuario nao esta registrado."));
    }

    @Override
    public void append(Usuario usuario) {
        if (usuario == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void alter(Integer usuarioId, Usuario newUsuario) {
        var oldUsuario = usuarioRepository
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
        var usuario = usuarioRepository.findById(usuarioId);

        if (usuario.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        usuarioRepository.deleteById(usuarioId);
    }
}
