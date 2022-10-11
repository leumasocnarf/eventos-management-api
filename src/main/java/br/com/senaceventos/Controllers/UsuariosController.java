package br.com.senaceventos.Controllers;

import br.com.senaceventos.Controllers.Common.IBaseController;
import br.com.senaceventos.Entities.Usuario;
import br.com.senaceventos.Services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuariosController implements IBaseController<Usuario> {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    @Operation(summary = "Retorna lista de usuarios.")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Usuario>> getListOf() {

        var usuariosListResponse = usuarioService.retrieveAll();

        return ResponseEntity.ok(usuariosListResponse);
    }

    @Override
    @Operation(summary = "Retorna um usuarios especifico por ID.")
    @GetMapping(path = "{usuarioId}", produces = "application/json")
    public ResponseEntity<Usuario> get(@PathVariable("usuarioId") Integer usuarioId) {

        var usuarioResponse = usuarioService.retrieveById(usuarioId);

        return ResponseEntity.ok(usuarioResponse);
    }

    @Override
    @Operation(summary = "Salva um novo usuario.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuarioRequest) {

        usuarioService.append(usuarioRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build(usuarioRequest);

        return ResponseEntity.created(location).body(usuarioRequest);
    }

    @Override
    @Operation(summary = "Atualiza um usuario já existente.")
    @PutMapping(path = "{usuarioId}", produces = "application/json")
    public ResponseEntity<Usuario> put(@PathVariable("usuarioId")Integer usuarioId,
                                       @RequestBody Usuario usuarioRequest) {
        
        usuarioService.alter(usuarioId, usuarioRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build(usuarioRequest);

        return ResponseEntity.created(location).body(usuarioRequest);
    }

    @Override
    @Operation(summary = "Deleta um usuario registrado.")
    @DeleteMapping(path = "{usuarioId}", produces = "application/json")
    public ResponseEntity<Optional<Usuario>> delete(@PathVariable("usuarioId") Integer usuarioId) {

        usuarioService.remove(usuarioId);

        return ResponseEntity.noContent().build();
    }
}
