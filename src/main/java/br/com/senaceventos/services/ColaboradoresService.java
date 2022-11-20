package br.com.senaceventos.services;

import br.com.senaceventos.models.Colaborador;
import br.com.senaceventos.exception.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exception.NoContentFoundAtCollectionException;
import br.com.senaceventos.exception.RegisterNotFoundException;
import br.com.senaceventos.repositories.IColaboradoresRepository;
import br.com.senaceventos.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ColaboradoresService implements IBaseService<Colaborador> {

    private final IColaboradoresRepository colaboradoresRepository;

    @Autowired
    public ColaboradoresService(IColaboradoresRepository colaboradoresRepository) {
        this.colaboradoresRepository = colaboradoresRepository;
    }

    @Override
    public List<Colaborador> retrieveAll() {
        var colaboradorList = colaboradoresRepository.findAll();

        if (colaboradorList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha colaboradores registrados.");
        }
        return colaboradorList;
    }

    @Override
    public Colaborador retrieveById(Integer colaboradorId) {
        return colaboradoresRepository.findById(colaboradorId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse colaborador nao esta registrado."));
    }

    @Override
    public void append(Colaborador colaborador) {
        if (colaborador == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        colaboradoresRepository.save(colaborador);
    }

    @Override
    @Transactional
    public void alter(Integer colaboradorId, Colaborador newColaborador) {
        var oldColaborador = colaboradoresRepository
                .findById(colaboradorId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        oldColaborador.setTipoColaborador(newColaborador.getTipoColaborador());
        oldColaborador.setNome(newColaborador.getNome());
    }

    @Override
    public void remove(Integer colaboradorId) {
        var colaborador = colaboradoresRepository.findById(colaboradorId);

        if (colaborador.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        colaboradoresRepository.deleteById(colaboradorId);
    }
}
