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
    @GetMapping
    public ResponseEntity<List<Colaborador>> getListOf() {
        try {
            var colaboradorResponseList = colaboradorService.fetchAll();

            return ResponseEntity.ok(colaboradorResponseList);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "{colaboradorId}")
    public ResponseEntity<Colaborador> get(@PathVariable("colaboradorId") Integer colaboradorId) {
        try {
            var colaboradorResponse = colaboradorService.fetchOne(colaboradorId);

            return ResponseEntity.ok(colaboradorResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Colaborador colaborador) {
        try {
            colaboradorService.append(colaborador);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaborador);

            return ResponseEntity.created(location).body(colaborador);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping(path = "{colaboradorId}")
    public ResponseEntity<?> delete(@PathVariable("colaboradorId") Integer colaboradorId) {
        try {
            colaboradorService.remove(colaboradorId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(path = "{colaboradorId}")
    public ResponseEntity<?> put(@PathVariable("colaboradorId") Integer colaboradorId, @RequestBody Colaborador colaborador) {
        try {
            colaboradorService.update(colaboradorId, colaborador);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaborador);

            return ResponseEntity.created(location).body(colaborador);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}