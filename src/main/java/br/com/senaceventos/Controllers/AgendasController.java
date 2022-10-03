package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Services.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/agendas")
public class AgendasController implements IBaseController<Agenda> {

    private final AgendaService agendaService;

    @Autowired
    public AgendasController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @Override
    @Operation(summary = "Retorna lista de agendas.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Agenda>> getListOf() {
        var agendasListResponse = agendaService.retrieveAll();
        return ResponseEntity.ok(agendasListResponse);
    }

    @Override
    @Operation(summary = "Retorna uma agenda especifica por ID.")
    @GetMapping(path = "{agendaId}", produces = "application/json")
    public ResponseEntity<Agenda> get(@PathVariable("agendaId") Integer agendaId) {
        var agendaResponse = agendaService.retrieveById(agendaId);
        return ResponseEntity.ok(agendaResponse);
    }

    @Override
    @Operation(summary = "Salva uma nova agenda.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<Agenda> post(@RequestBody Agenda agendaRequest) {
        agendaService.append(agendaRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(agendaRequest);
        return ResponseEntity.created(location).body(agendaRequest);
    }

    @Override
    @Operation(summary = "Atualiza uma agenda já existente.")
    @PutMapping(path = "{agendaId}", produces = "application/json")
    public ResponseEntity<Agenda> put(@PathVariable("agendaId") Integer agendaId,
                                      @RequestBody Agenda agendaRequest) {
        agendaService.alter(agendaId, agendaRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(agendaRequest);
        return ResponseEntity.created(location).body(agendaRequest);
    }

    @Override
    @Operation(summary = "Deleta uma agenda registrada.")
    @DeleteMapping(path = "{agendaId}", produces = "application/json")
    public ResponseEntity<Optional<Agenda>> delete(@PathVariable("agendaId") Integer agendaId) {
        agendaService.remove(agendaId);
        return ResponseEntity.noContent().build();
    }
}
