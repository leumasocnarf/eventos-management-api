package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Services.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1")
public class EquipamentosController implements IBaseController<Equipamento> {

    private final EquipamentoService equipamentoService;

    @Autowired
    public EquipamentosController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @Override
    @Operation(summary = "Retorna lista de equipamentos.")
    @GetMapping(path = "/equipamentos", produces = "application/json")
    public ResponseEntity<List<Equipamento>> getListOf() {
        var equipamentosListResponse = equipamentoService.retrieveAll();
        return ResponseEntity.ok(equipamentosListResponse);
    }

    @Override
    @Operation(summary = "Retorna um equipamento especifico por ID.")
    @GetMapping(path = "/equipamentos/{equipamentoId}", produces = "application/json")
    public ResponseEntity<Equipamento> get(@PathVariable("equipamentoId") Integer equipamentoId) {
        var equipamentoResponse = equipamentoService.retrieveById(equipamentoId);
        return ResponseEntity.ok(equipamentoResponse);
    }

    @Operation(summary = "Retorna lista de equipamentos em uma agenda especifica por ID.")
    @GetMapping(path = "/agendas/{agendaId}/equipamentos", produces = "application/json")
    public ResponseEntity<List<Equipamento>> getAllEquipamentosInThisAgenda(@PathVariable("agendaId") Integer agendaId) {
        var equipamentosListInAgendaResponse = equipamentoService
                .retrieveAllFromAgenda(agendaId);
        return ResponseEntity.ok(equipamentosListInAgendaResponse);
    }

    @Operation(summary = "Retorna agendas com um equipamento especifico por ID.")
    @GetMapping(path = "/equipamentos/{equipamentoId}/agendas", produces = "application/json")
    public ResponseEntity<List<Agenda>> getAllAgendasWithThisEquipamento(@PathVariable("equipamentoId") Integer equipamentoId) {
        var agendasListWithEquipamentoResponse = equipamentoService
                .retrieveAllWithEquipamento(equipamentoId);
        return ResponseEntity.ok(agendasListWithEquipamentoResponse);
    }

    @Override
    @Operation(summary = "Salva um novo equipamento.")
    @PostMapping(path = "/equipamentos", produces = "application/json")
    public ResponseEntity<Equipamento> post(@RequestBody Equipamento equipamentoRequest) {
        equipamentoService.append(equipamentoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);
        return ResponseEntity.created(location).body(equipamentoRequest);
    }

    @Operation(summary = "Salva um novo equipamento em uma agenda especifica por ID.")
    @PostMapping(path = "/agendas/{agendaId}/equipamentos", produces = "application/json")
    public ResponseEntity<Equipamento> postEquipamentoIntoAgenda(@PathVariable("agendaId") Integer agendaId,
                                                                 @RequestBody Equipamento equipamentoRequest) {

        equipamentoService.appendInto(agendaId, equipamentoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);
        return ResponseEntity.created(location).body(equipamentoRequest);

    }

    @Override
    @Operation(summary = "Atualiza um equipamento já existente.")
    @PutMapping(path = "/equipamentos/{equipamentoId}", produces = "application/json")
    public ResponseEntity<Equipamento> put(@PathVariable("equipamentoId") Integer equipamentoId,
                                           @RequestBody Equipamento equipamentoRequest) {
        equipamentoService.alter(equipamentoId, equipamentoRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(equipamentoRequest);
        return ResponseEntity.created(location).body(equipamentoRequest);
    }

    @Override
    @Operation(summary = "Deleta um equipamento registrado.")
    @DeleteMapping(path = "/equipamentos/{equipamentoId}", produces = "application/json")
    public ResponseEntity<Optional<Equipamento>> delete(@PathVariable("equipamentoId") Integer equipamentoId) {
        equipamentoService.remove(equipamentoId);
        return ResponseEntity.noContent().build();
    }
}
