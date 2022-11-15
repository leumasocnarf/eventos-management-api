package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.Exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.Exceptions.RegisterNotFoundException;
import br.com.senaceventos.Repositories.IAgendaRepository;
import br.com.senaceventos.Services.Common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgendaService implements IBaseService<Agenda> {

    private final IAgendaRepository agendaRepository;

    @Autowired
    public AgendaService(IAgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public List<Agenda> retrieveAll() {
        var agendaList = agendaRepository.findAll();

        if (agendaList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha agendas registrados.");
        }
        return agendaList;
    }

    @Override
    public Agenda retrieveById(Integer agendaId) {
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse agenda nao esta registrado."));
    }

    @Override
    public void append(Agenda agenda) {
        if (agenda == null) {
            throw new InvalidParametersAtRequestBodyException("valores null");
        }
        agendaRepository.save(agenda);
    }

    @Override
    @Transactional
    public void alter(Integer agendaId, Agenda newAgenda) {
        var oldAgenda = agendaRepository
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
        var agenda = agendaRepository.findById(agendaId);

        if (agenda.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        agendaRepository.deleteById(agendaId);
    }
}
