package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Colaborador;
import br.com.senaceventos.Services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/colaboradores")
public class ColaboradoresController implements IBaseController<Colaborador> {
    
    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradoresController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Colaborador>> getListOf() {
        try {
            var colaboradoresListResponse = colaboradorService.retrieveAll();

            return ResponseEntity.ok(colaboradoresListResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<Colaborador> get(@PathVariable("colaboradorId") Integer colaboradorId) {
        try {
            var colaboradorResponse = colaboradorService.retrieveById(colaboradorId);

            return ResponseEntity.ok(colaboradorResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(@RequestBody Colaborador colaboradorRequest) {
        try {
            colaboradorService.append(colaboradorRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaboradorRequest);

            return ResponseEntity.created(location).body(colaboradorRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PutMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<?> put(@PathVariable("colaboradorId") Integer colaboradorId,
                                 @RequestBody Colaborador colaboradorRequest) {
        try {
            colaboradorService.alter(colaboradorId, colaboradorRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaboradorRequest);

            return ResponseEntity.created(location).body(colaboradorRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("colaboradorId") Integer colaboradorId) {
        try {
            colaboradorService.remove(colaboradorId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
