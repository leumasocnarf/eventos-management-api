package br.com.senaceventos.Controllers;

import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/equipamentos")
public class EquipamentosController {

    private final EquipamentoService equipamentoService;

    @Autowired
    public EquipamentosController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> getEquipamentos() {
        try {
            var equipamentosResponseList = equipamentoService.seeAllEquipamentos();

            return ResponseEntity.ok(equipamentosResponseList);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "{equipamentoId}")
    public ResponseEntity<Equipamento> getOneEquipamento(@PathVariable("equipamentoId") Integer equipamentoId) {
        try {
            var equipamentoResponse = equipamentoService.seeSpecificEquipamento(equipamentoId);

            return ResponseEntity.ok(equipamentoResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveEquipamento(@RequestBody Equipamento equipamento) {
        try {
            equipamentoService.addEquipamento(equipamento);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamento);

            return ResponseEntity.created(location).body(equipamento);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "{equipamentoId}")
    public ResponseEntity<?> deleteEquipamento(@PathVariable("equipamentoId") Integer equipamentoId) {
        try {
            equipamentoService.deleteEquipamento(equipamentoId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "{equipamentoId}")
    public ResponseEntity<?> updateEquipamento(@PathVariable("equipamentoId") Integer equipamentoId, @RequestBody Equipamento equipamento) {
        try {
            equipamentoService.updateEquipamento(equipamentoId, equipamento);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamento);

            return ResponseEntity.created(location).body(equipamento);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
