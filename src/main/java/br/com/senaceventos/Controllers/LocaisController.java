package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Services.LocalService;
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
    @GetMapping
    public ResponseEntity<List<Local>> getListOf() {
        try {
            var locaisListResponse = localService.fetchAll();

            return ResponseEntity.ok(locaisListResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    @GetMapping(path = "{localId}")
    public ResponseEntity<Local> get(@PathVariable("localId") Integer localId) {
        try {
            var localResponse = localService.fetchOne(localId);

            return ResponseEntity.ok(localResponse);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
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
    @DeleteMapping(path = "{localId}")
    public ResponseEntity<?> delete(@PathVariable("localId") Integer localId) {
        try {
            localService.remove(localId);

            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PutMapping(path = "{localId}")
    public ResponseEntity<?> put(@PathVariable("localId") Integer localId,
                                 @RequestBody Local localRequest) {
        try {
            localService.update(localId, localRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build(localRequest);

            return ResponseEntity.created(location).body(localRequest);

        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
