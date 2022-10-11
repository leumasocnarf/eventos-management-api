package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.Exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.Exceptions.RegisterNotFoundException;
import br.com.senaceventos.Repositories.ILocalRepository;
import br.com.senaceventos.Services.Common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocalService implements IBaseService<Local> {

    private final ILocalRepository localRepository;

    @Autowired
    public LocalService(ILocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public List<Local> retrieveAll() {
        var localList = localRepository.findAll();

        if (localList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha locals registrados.");
        }
        return localList;
    }

    @Override
    public Local retrieveById(Integer localId) {
        return localRepository.findById(localId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse local nao esta registrado."));
    }

    @Override
    public void append(Local local) {
        if (local == null) {
            throw new InvalidParametersAtRequestBodyException("Objeto null");
        }
        localRepository.save(local);
    }

    @Override
    @Transactional
    public void alter(Integer localId, Local newLocal) {
        var oldLocal = localRepository
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
        var local = localRepository.findById(localId);

        if (local.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        localRepository.deleteById(localId);
    }
}
