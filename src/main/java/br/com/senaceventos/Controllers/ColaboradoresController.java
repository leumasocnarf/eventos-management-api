package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Colaborador;
import br.com.senaceventos.Services.ColaboradorService;
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
@RequestMapping(path = "api/v1/colaboradores")
public class ColaboradoresController implements IBaseController<Colaborador> {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradoresController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @Override
    @Operation(summary = "Retorna lista de colaborares.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Colaborador>> getListOf() {
        var colaboradoresListResponse = colaboradorService.retrieveAll();
        return ResponseEntity.ok(colaboradoresListResponse);
    }

    @Override
    @Operation(summary = "Retorna um colaborador especifico por ID.")
    @GetMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<Colaborador> get(@PathVariable("colaboradorId") Integer colaboradorId) {
        var colaboradorResponse = colaboradorService.retrieveById(colaboradorId);
        return ResponseEntity.ok(colaboradorResponse);
    }

    @Override
    @Operation(summary = "Salva um novo colaborador.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<Colaborador> post(@RequestBody Colaborador colaboradorRequest) {
        colaboradorService.append(colaboradorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaboradorRequest);
        return ResponseEntity.created(location).body(colaboradorRequest);
    }

    @Override
    @Operation(summary = "Atualiza um colaborador já existente.")
    @PutMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<Colaborador> put(@PathVariable("colaboradorId") Integer colaboradorId,
                                           @RequestBody Colaborador colaboradorRequest) {
        colaboradorService.alter(colaboradorId, colaboradorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(colaboradorRequest);
        return ResponseEntity.created(location).body(colaboradorRequest);
    }

    @Override
    @Operation(summary = "Deleta um colaborador registrado.")
    @DeleteMapping(path = "{colaboradorId}", produces = "application/json")
    public ResponseEntity<Optional<Colaborador>> delete(@PathVariable("colaboradorId") Integer colaboradorId) {
        colaboradorService.remove(colaboradorId);
        return ResponseEntity.noContent().build();
    }
}
