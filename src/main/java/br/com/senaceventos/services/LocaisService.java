package br.com.senaceventos.services;

import br.com.senaceventos.models.Local;
import br.com.senaceventos.exception.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exception.NoContentFoundAtCollectionException;
import br.com.senaceventos.exception.RegisterNotFoundException;
import br.com.senaceventos.repositories.ILocaisRepository;
import br.com.senaceventos.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocaisService implements IBaseService<Local> {

    private final ILocaisRepository locaisRepository;

    @Autowired
    public LocaisService(ILocaisRepository locaisRepository) {
        this.locaisRepository = locaisRepository;
    }

    @Override
    public List<Local> retrieveAll() {
        var localList = locaisRepository.findAll();

        if (localList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha locals registrados.");
        }
        return localList;
    }

    @Override
    public Local retrieveById(Integer localId) {
        return locaisRepository.findById(localId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse local nao esta registrado."));
    }

    @Override
    public void append(Local local) {
        if (local == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        locaisRepository.save(local);
    }

    @Override
    @Transactional
    public void alter(Integer localId, Local newLocal) {
        var oldLocal = locaisRepository
                .findById(localId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        if (newLocal == null || newLocal.getDescricao().isEmpty() && newLocal.getObservacao().isEmpty()) {
            throw new InvalidParametersAtRequestBodyException("Campos invalidos.");
        }

        oldLocal.setDescricao(newLocal.getDescricao());
        oldLocal.setObservacao(newLocal.getObservacao());
    }

    @Override
    public void remove(Integer localId) {
        var local = locaisRepository.findById(localId);

        if (local.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        locaisRepository.deleteById(localId);
    }
}
