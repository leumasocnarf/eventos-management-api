package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1")
public class EquipamentosController implements IBaseController<Equipamento> {

    private final EquipamentoService equipamentoService;

    @Autowired
    public EquipamentosController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @Override
    @GetMapping(path = "/equipamentos")
    public ResponseEntity<List<Equipamento>> getListOf() {
        try {
            var equipamentosListResponse = equipamentoService.fetchAll();

            return ResponseEntity.ok(equipamentosListResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "/equipamentos/{equipamentoId}")
    public ResponseEntity<Equipamento> get(@PathVariable("equipamentoId") Integer equipamentoId) {
        try {
            var equipamentoResponse = equipamentoService.fetchOne(equipamentoId);

            return ResponseEntity.ok(equipamentoResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/agendas/{agendaId}/equipamentos")
    public ResponseEntity<List<Equipamento>> getAllEquipamentosInThisAgenda(@PathVariable("agendaId") Integer agendaId) {
        try {
            var equipamentosListInAgendaResponse = equipamentoService
                    .fetchAllFrom(agendaId);

            return ResponseEntity.ok(equipamentosListInAgendaResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/equipamentos/{equipamentoId}/agendas")
    public ResponseEntity<List<Agenda>> getAllAgendasWithThisEquipamento(
            @PathVariable("equipamentoId") Integer equipamentoId) {
        try {
            var agendasListWithEquipamentoResponse = equipamentoService
                    .fetchAllWith(equipamentoId);

            return ResponseEntity.ok(agendasListWithEquipamentoResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping(path = "/equipamentos")
    public ResponseEntity<?> post(@RequestBody Equipamento equipamentoRequest) {
        try {
            equipamentoService.append(equipamentoRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);

            return ResponseEntity.created(location).body(equipamentoRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/agendas/{agendaId}/equipamentos")
    public ResponseEntity<?> postEquipamentoIntoAgenda(@PathVariable("agendaId") Integer agendaId,
                                                       @RequestBody Equipamento equipamentoRequest) {
        try {
            equipamentoService.appendInto(agendaId, equipamentoRequest);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);

            return ResponseEntity.created(location).body(equipamentoRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    @DeleteMapping(path = "/equipamentos/{equipamentoId}")
    public ResponseEntity<?> delete(@PathVariable("equipamentoId") Integer equipamentoId) {
        try {
            equipamentoService.remove(equipamentoId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // correct mapping
    @Override
    @PutMapping(path = "/equipamentos/{equipamentoId}")
    public ResponseEntity<?> put(@PathVariable("equipamentoId") Integer equipamentoId,
                                 @RequestBody Equipamento equipamentoRequest) {
        try {
            equipamentoService.update(equipamentoId, equipamentoRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);

            return ResponseEntity.created(location).body(equipamentoRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
