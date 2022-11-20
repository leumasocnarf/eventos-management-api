package br.com.senaceventos.services;

import br.com.senaceventos.models.Agenda;
import br.com.senaceventos.exception.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exception.NoContentFoundAtCollectionException;
import br.com.senaceventos.exception.RegisterNotFoundException;
import br.com.senaceventos.repositories.IAgendasRepository;
import br.com.senaceventos.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgendasService implements IBaseService<Agenda> {

    private final IAgendasRepository agendasRepository;

    @Autowired
    public AgendasService(IAgendasRepository agendasRepository) {
        this.agendasRepository = agendasRepository;
    }

    @Override
    public List<Agenda> retrieveAll() {
        var agendaList = agendasRepository.findAll();

        if (agendaList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha agendas registrados.");
        }
        return agendaList;
    }

    @Override
    public Agenda retrieveById(Integer agendaId) {
        return agendasRepository.findById(agendaId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse agenda nao esta registrado."));
    }

    @Override
    public void append(Agenda agenda) {
        if (agenda == null) {
            throw new InvalidParametersAtRequestBodyException("valores null");
        }
        agendasRepository.save(agenda);
    }

    @Override
    @Transactional
    public void alter(Integer agendaId, Agenda newAgenda) {
        var oldAgenda = agendasRepository
                .findById(agendaId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        oldAgenda.setTitulo(newAgenda.getTitulo());
        oldAgenda.setInicio(newAgenda.getInicio());
        oldAgenda.setTermino(newAgenda.getTermino());
        oldAgenda.setHoraInicio(newAgenda.getHoraInicio());
        oldAgenda.setHoraTermino(newAgenda.getHoraTermino());
        oldAgenda.setObservacao(newAgenda.getObservacao());
        oldAgenda.setColaborador(newAgenda.getColaborador());
        oldAgenda.setLocal(newAgenda.getLocal());
        oldAgenda.setEquipamentos(newAgenda.getEquipamentos());
    }

    @Override
    public void remove(Integer agendaId) {
        var agenda = agendasRepository.findById(agendaId);

        if (agenda.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        agendasRepository.deleteById(agendaId);
    }
}
