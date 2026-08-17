package org.example.paqueteria.cliente.Controller;

import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Mapper.ClienteMapper;
import org.example.paqueteria.cliente.Service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.obtenerTodos().stream()
                .map(ClienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(cliente -> ResponseEntity.ok(ClienteMapper.toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteDto crear(@RequestBody ClienteDto dto) {
        return ClienteMapper.toDto(clienteService.guardar(ClienteMapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable Long id, @RequestBody ClienteDto dto) {
        return clienteService.obtenerPorId(id).map(clienteExistente -> {
            clienteExistente.setNombre(dto.getNombre());
            clienteExistente.setTelefono(dto.getTelefono());
            clienteExistente.setDireccion(dto.getDireccion());
            return ResponseEntity.ok(ClienteMapper.toDto(clienteService.guardar(clienteExistente)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteService.obtenerPorId(id).isPresent()) {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}