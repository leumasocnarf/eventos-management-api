package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Services.LocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/locais")
public class LocaisController implements IBaseController<Local> {

    private final LocalService localService;

    @Autowired
    public LocaisController(LocalService localService) {
        this.localService = localService;
    }

    @Override
    @Operation(summary = "Retorna lista de locais.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Local>> getListOf() {
        var locaisListResponse = localService.retrieveAll();
        return ResponseEntity.ok(locaisListResponse);
    }

    @Override
    @Operation(summary = "Retorna um local especifico por ID.")
    @GetMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<Local> get(@PathVariable("localId") Integer localId) {
        var localResponse = localService.retrieveById(localId);
        return ResponseEntity.ok(localResponse);
    }

    @Override
    @Operation(summary = "Salva um novo local.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<Local> post(@RequestBody Local localRequest) {
        localService.append(localRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(localRequest);
        return ResponseEntity.created(location).body(localRequest);
    }

    @Override
    @Operation(summary = "Atualiza um local já existente.")
    @PutMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<Local> put(@PathVariable("localId") Integer localId,
                                     @RequestBody Local localRequest) {
        localService.alter(localId, localRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(localRequest);
        return ResponseEntity.created(location).body(localRequest);
    }

    @Override
    @Operation(summary = "Deleta um local registrado.")

    @DeleteMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<Optional<Local>> delete(@PathVariable("localId") Integer localId) {
        localService.remove(localId);
        return ResponseEntity.noContent().build();
    }
}
