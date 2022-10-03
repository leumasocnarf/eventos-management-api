package br.com.senaceventos.Services;

import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.Exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.Exceptions.RegisterNotFoundException;
import br.com.senaceventos.Repositories.IAgendaRepository;
import br.com.senaceventos.Repositories.IEquipamentoRepository;
import br.com.senaceventos.Services.Common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipamentoService implements IBaseService<Equipamento> {

    private final IEquipamentoRepository equipamentoRepository;
    private final IAgendaRepository agendaRepository;

    @Autowired
    public EquipamentoService(IEquipamentoRepository equipamentoRepository, IAgendaRepository agendaRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.agendaRepository = agendaRepository;
    }

    @Override
    public List<Equipamento> retrieveAll() {
        var equipamentoList = equipamentoRepository.findAll();

        if (equipamentoList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha equipamentos registrados.");
        }
        return equipamentoList;
    }

    @Override
    public Equipamento retrieveById(Integer equipamentoId) {
        return equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse equipamento nao esta registrado."));
    }

    public List<Equipamento> retrieveAllFromAgenda(Integer agendaId) {
        if (!agendaRepository.existsById(agendaId)) {
            throw new RegisterNotFoundException("Nao ha agenda com esse id.");
        }

        var equipamentosInAgendaList = equipamentoRepository
                .findEquipamentosByAgendaId(agendaId);

        if (equipamentosInAgendaList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha equipamentos registrados.");
        }

        return equipamentosInAgendaList;
    }

    public List<Agenda> retrieveAllWithEquipamento(Integer equipamentoId) {
        if (!equipamentoRepository.existsById(equipamentoId)) {
            throw new RegisterNotFoundException("Nao ha equipamento registrado com esse id.");
        }

        var agendasInEquipamentosList = agendaRepository
                .findAgendasByEquipamentosId(equipamentoId);

        if (agendasInEquipamentosList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha agendas registradas para esse equipamento.");
        }

        return agendasInEquipamentosList;
    }

    @Override
    public void append(Equipamento equipamento) {
        if (equipamento == null) {
            throw new InvalidParametersAtRequestBodyException("valores null");
        }
        equipamentoRepository.save(equipamento);
    }


    public void appendInto(Integer agendaId, Equipamento equipamento) {
        agendaRepository.findById(agendaId).map(agenda -> {
            Integer equipId = equipamento.getId();

            if (equipId != 0) {
                var _equip = equipamentoRepository.findById(equipId)
                        .orElseThrow(() -> new RegisterNotFoundException("equipamento nao encontrado"));

                agenda.reservarEquipamento(_equip);
                agendaRepository.save(agenda);
                return _equip;
            }
            agenda.reservarEquipamento(equipamento);
            return equipamentoRepository.save(equipamento);
        }).orElseThrow(() -> new RegisterNotFoundException("agenda nao encontrada com id"));
    }

    @Override
    @Transactional
    public void alter(Integer equipamentoId, Equipamento newEquipamento) {
        var oldEquipamento = equipamentoRepository
                .findById(equipamentoId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        oldEquipamento.setDescricao(newEquipamento.getDescricao());
        oldEquipamento.setObservacao(newEquipamento.getObservacao());
    }

    @Override
    public void remove(Integer equipamentoId) {
        var equip = equipamentoRepository.findById(equipamentoId);

        if (equip.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        equipamentoRepository.deleteById(equipamentoId);
    }
}
