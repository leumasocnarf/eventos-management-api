package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Colaborador;
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
    public List<Colaborador> fetchAll() {
        var colaboradorList = colaboradorRepository.findAll();

        if (colaboradorList.isEmpty()) {
            throw new IllegalStateException("Nao ha colaboradores registrados.");
        }
        return colaboradorList;
    }

    @Override
    public Colaborador fetchOne(Integer colaboradorId) {
        return colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new IllegalStateException("Esse colaborador nao esta registrado."));
    }

    @Override
    public void append(Colaborador colaborador) {
        if (colaborador == null) {
            throw new IllegalStateException("valores null");
        }
        colaboradorRepository.save(colaborador);
    }

    @Override
    public void remove(Integer colaboradorId) {
        var equip = colaboradorRepository.findById(colaboradorId);

        if (equip.isEmpty()) {
            throw new IllegalStateException("Nao ha registro para deletar.");
        }
        colaboradorRepository.deleteById(colaboradorId);
    }

    @Override
    @Transactional
    public void update(Integer colaboradorId, Colaborador newColaborador) {
        var oldColaborador = colaboradorRepository
                .findById(colaboradorId)
                .orElseThrow(() -> new IllegalStateException("Nao ha registro para atualizar."));

        oldColaborador.setTipoColaborador(newColaborador.getTipoColaborador());
        oldColaborador.setNome(newColaborador.getNome());
    }
}
