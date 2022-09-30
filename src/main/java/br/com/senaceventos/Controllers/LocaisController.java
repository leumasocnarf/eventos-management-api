package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Services.LocalService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/locais")
public class LocaisController implements IBaseController<Local> {

    private final LocalService localService;

    @Autowired
    public LocaisController(LocalService localService) {
        this.localService = localService;
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Local>> getListOf() {
        try {
            var locaisListResponse = localService.retrieveAll();

            return ResponseEntity.ok(locaisListResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<Local> get(@PathVariable("localId") Integer localId) {
        try {
            var localResponse = localService.retrieveById(localId);

            return ResponseEntity.ok(localResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(@RequestBody Local localRequest) {
        try {
            localService.append(localRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(localRequest);

            return ResponseEntity.created(location).body(localRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PutMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<?> put(@PathVariable("localId") Integer localId,
                                 @RequestBody Local localRequest) {
        try {
            localService.alter(localId, localRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(localRequest);

            return ResponseEntity.created(location).body(localRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping(path = "{localId}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("localId") Integer localId) {
        try {
            localService.remove(localId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
