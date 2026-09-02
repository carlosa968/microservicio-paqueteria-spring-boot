package org.example.paqueteria.cliente.Controller;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Mapper.ClienteMapper;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.example.paqueteria.cliente.Service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Ajusta según tu configuración CORS

public class ClienteController {

    private final ClienteService clienteService;


    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.obtenerTodos().stream()
                .map(ClienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable Long id) {

        Cliente cliente = clienteService.obtenerPorId(id);
        ClienteDto clienteDto = ClienteMapper.toDto(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(clienteDto);

    }

    @PostMapping
    public ClienteDto crear(@RequestBody ClienteDto dto) {
        return ClienteMapper.toDto(clienteService.guardar(ClienteMapper.toEntity(dto)));
    }
// CORREGUIR NO DEBE HABE RLOGICA DEBE SER LIGERO
@PutMapping("/{id}")
public ResponseEntity<ClienteDto> actualizar(
        @PathVariable Long id,
        @RequestBody ClienteDto dto) {
Cliente actualizado = clienteService.actualizar(id,dto);
ClienteDto clienteDto= ClienteMapper.toDto(actualizado);

    return ResponseEntity.status(HttpStatus.OK).body(clienteDto);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        clienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
    /////////////////////////////////////////////////////////////////////////
    @GetMapping("/buscar")
    public List<ClienteDto> buscarClientes(@RequestParam("query") String query) {
        return clienteService.buscarPorNombreOApellido(query).stream()
                .map(ClienteMapper::toDto)
                .collect(Collectors.toList());
    }
}