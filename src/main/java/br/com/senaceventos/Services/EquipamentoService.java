package br.com.senaceventos.Services;

import br.com.senaceventos.Models.Equipamento;
import br.com.senaceventos.Persistence.IEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipamentoService {

    private final IEquipamentoRepository equipamentoRepository;

    @Autowired
    public EquipamentoService(IEquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> seeAllEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento seeSpecificEquipamento(Integer equipamentoId) {
        return equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new IllegalStateException("Esse equipamento nao esta registrado."));
    }

    public void addEquipamento(Equipamento equipamento) {
        equipamentoRepository.save(equipamento);
    }

    public void deleteEquipamento(Integer equipamentoId) {
        var equip = equipamentoRepository.findById(equipamentoId);

        if (equip.isEmpty()) {
            throw new IllegalStateException("Nao ha registro para deletar.");
        }
        equipamentoRepository.deleteById(equipamentoId);
    }

    @Transactional
    public void updateEquipamento(Integer equipamentoId, Equipamento newEquipamento) {
        var oldEquipamento = equipamentoRepository
                .findById(equipamentoId)
                .orElseThrow(() -> new IllegalStateException("Nao ha registro para atualizar."));

        oldEquipamento.setDescricao(newEquipamento.getDescricao());
        oldEquipamento.setObservacao(newEquipamento.getObservacao());
    }
}
