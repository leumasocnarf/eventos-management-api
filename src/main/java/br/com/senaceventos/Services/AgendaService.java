package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Agenda;
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
    public List<Agenda> fetchAll() {
        var agendaList = agendaRepository.findAll();

        if (agendaList.isEmpty()) {
            throw new IllegalStateException("Nao ha agendas registrados.");
        }
        return agendaList;
    }

    @Override
    public Agenda fetchOne(Integer agendaId) {
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new IllegalStateException("Esse agenda nao esta registrado."));
    }

    @Override
    public void append(Agenda agenda) {
        if (agenda == null) {
            throw new IllegalStateException("valores null");
        }
        agendaRepository.save(agenda);
    }

    @Override
    public void remove(Integer agendaId) {
        var equip = agendaRepository.findById(agendaId);

        if (equip.isEmpty()) {
            throw new IllegalStateException("Nao ha registro para deletar.");
        }
        agendaRepository.deleteById(agendaId);
    }

    @Override
    @Transactional
    public void update(Integer agendaId, Agenda newAgenda) {
        var oldAgenda = agendaRepository
                .findById(agendaId)
                .orElseThrow(() -> new IllegalStateException("Nao ha registro para atualizar."));

        oldAgenda.setObservacao(newAgenda.getObservacao());
    }
}
