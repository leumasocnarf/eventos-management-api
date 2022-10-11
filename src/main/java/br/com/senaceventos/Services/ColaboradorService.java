package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Colaborador;
import br.com.senaceventos.Exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.Exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.Exceptions.RegisterNotFoundException;
import br.com.senaceventos.Repositories.IColaboradorRepository;
import br.com.senaceventos.Services.Common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ColaboradorService implements IBaseService<Colaborador> {

    private final IColaboradorRepository colaboradorRepository;

    @Autowired
    public ColaboradorService(IColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public List<Colaborador> retrieveAll() {
        var colaboradorList = colaboradorRepository.findAll();

        if (colaboradorList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha colaboradores registrados.");
        }
        return colaboradorList;
    }

    @Override
    public Colaborador retrieveById(Integer colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse colaborador nao esta registrado."));
    }

    @Override
    public void append(Colaborador colaborador) {
        if (colaborador == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        colaboradorRepository.save(colaborador);
    }

    @Override
    @Transactional
    public void alter(Integer colaboradorId, Colaborador newColaborador) {
        var oldColaborador = colaboradorRepository
                .findById(colaboradorId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        oldColaborador.setTipoColaborador(newColaborador.getTipoColaborador());
        oldColaborador.setNome(newColaborador.getNome());
    }

    @Override
    public void remove(Integer colaboradorId) {
        var colaborador = colaboradorRepository.findById(colaboradorId);

        if (colaborador.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        colaboradorRepository.deleteById(colaboradorId);
    }
}
