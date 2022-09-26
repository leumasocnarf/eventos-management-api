package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/agendas")
public class AgendasController implements IBaseController<Agenda> {

    private final AgendaService agendaService;

    @Autowired
    public AgendasController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Agenda>> getListOf() {
        try {
            var agendasResponseList = agendaService.fetchAll();

            return ResponseEntity.ok(agendasResponseList);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "{agendaId}")
    public ResponseEntity<Agenda> get(@PathVariable("agendaId") Integer agendaId) {
        try {
            var agendaResponse = agendaService.fetchOne(agendaId);

            return ResponseEntity.ok(agendaResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Agenda agenda) {
        try {
            agendaService.append(agenda);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(agenda);

            return ResponseEntity.created(location).body(agenda);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    @DeleteMapping(path = "{agendaId}")
    public ResponseEntity<?> delete(@PathVariable("agendaId") Integer agendaId) {
        try {
            agendaService.remove(agendaId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(path = "{agendaId}")
    public ResponseEntity<?> put(@PathVariable("agendaId") Integer agendaId,
                                 @RequestBody Agenda agenda) {
        try {
            agendaService.update(agendaId, agenda);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(agenda);

            return ResponseEntity.created(location).body(agenda);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}