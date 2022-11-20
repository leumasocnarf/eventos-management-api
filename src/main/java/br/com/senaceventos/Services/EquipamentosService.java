package br.com.senaceventos.services;

import br.com.senaceventos.models.Agenda;
import br.com.senaceventos.models.Equipamento;
import br.com.senaceventos.exceptions.InvalidParametersAtRequestBodyException;
import br.com.senaceventos.exceptions.NoContentFoundAtCollectionException;
import br.com.senaceventos.exceptions.RegisterNotFoundException;
import br.com.senaceventos.repositories.IAgendasRepository;
import br.com.senaceventos.repositories.IEquipamentosRepository;
import br.com.senaceventos.services.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipamentosService implements IBaseService<Equipamento> {

    private final IEquipamentosRepository equipamentosRepository;
    private final IAgendasRepository agendasRepository;

    @Autowired
    public EquipamentosService(IEquipamentosRepository equipamentosRepository, IAgendasRepository agendasRepository) {
        this.equipamentosRepository = equipamentosRepository;
        this.agendasRepository = agendasRepository;
    }

    @Override
    public List<Equipamento> retrieveAll() {
        var equipamentoList = equipamentosRepository.findAll();

        if (equipamentoList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha equipamentos registrados.");
        }
        return equipamentoList;
    }

    @Override
    public Equipamento retrieveById(Integer equipamentoId) {
        return equipamentosRepository.findById(equipamentoId)
                .orElseThrow(() -> new RegisterNotFoundException("Esse equipamento nao esta registrado."));
    }

    public List<Equipamento> retrieveAllFromAgenda(Integer agendaId) {
        if (!agendasRepository.existsById(agendaId)) {
            throw new RegisterNotFoundException("Nao ha agenda com esse id.");
        }

        var equipamentosInAgendaList = equipamentosRepository
                .findEquipamentosByAgendaId(agendaId);

        if (equipamentosInAgendaList.isEmpty()) {
            throw new NoContentFoundAtCollectionException("Nao ha equipamentos registrados.");
        }

        return equipamentosInAgendaList;
    }

    public List<Agenda> retrieveAllWithEquipamento(Integer equipamentoId) {
        if (!equipamentosRepository.existsById(equipamentoId)) {
            throw new RegisterNotFoundException("Nao ha equipamento registrado com esse id.");
        }

        var agendasInEquipamentosList = agendasRepository
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
        equipamentosRepository.save(equipamento);
    }


    public void appendInto(Integer agendaId, Equipamento equipamento) {
        agendasRepository.findById(agendaId).map(agenda -> {
            Integer equipId = equipamento.getId();

            if (equipId != 0) {
                var _equip = equipamentosRepository.findById(equipId)
                        .orElseThrow(() -> new RegisterNotFoundException("equipamento nao encontrado"));

                agenda.reservarEquipamento(_equip);
                agendasRepository.save(agenda);
                return _equip;
            }
            agenda.reservarEquipamento(equipamento);
            return equipamentosRepository.save(equipamento);
        }).orElseThrow(() -> new RegisterNotFoundException("agenda nao encontrada com id"));
    }

    @Override
    @Transactional
    public void alter(Integer equipamentoId, Equipamento newEquipamento) {
        var oldEquipamento = equipamentosRepository
                .findById(equipamentoId)
                .orElseThrow(() -> new RegisterNotFoundException("Nao ha registro para atualizar."));

        oldEquipamento.setDescricao(newEquipamento.getDescricao());
        oldEquipamento.setObservacao(newEquipamento.getObservacao());
    }

    @Override
    public void remove(Integer equipamentoId) {
        var equip = equipamentosRepository.findById(equipamentoId);

        if (equip.isEmpty()) {
            throw new RegisterNotFoundException("Nao ha registro para deletar.");
        }
        equipamentosRepository.deleteById(equipamentoId);
    }
}
