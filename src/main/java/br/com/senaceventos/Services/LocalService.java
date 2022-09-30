package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Local;
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
            throw new IllegalStateException("Nao ha locals registrados.");
        }
        return localList;
    }

    @Override
    public Local retrieveById(Integer localId) {
        return localRepository.findById(localId)
                .orElseThrow(() -> new IllegalStateException("Esse local nao esta registrado."));
    }

    @Override
    public void append(Local local) {
        if (local == null) {
            throw new IllegalStateException("valores null");
        }
        localRepository.save(local);
    }

    @Override
    @Transactional
    public void alter(Integer localId, Local newLocal) {
        var oldLocal = localRepository
                .findById(localId)
                .orElseThrow(() -> new IllegalStateException("Nao ha registro para atualizar."));

        oldLocal.setDescricao(newLocal.getDescricao());
        oldLocal.setObservacao(newLocal.getObservacao());
    }

    @Override
    public void remove(Integer localId) {
        var equip = localRepository.findById(localId);

        if (equip.isEmpty()) {
            throw new IllegalStateException("Nao ha registro para deletar.");
        }
        localRepository.deleteById(localId);
    }
}
