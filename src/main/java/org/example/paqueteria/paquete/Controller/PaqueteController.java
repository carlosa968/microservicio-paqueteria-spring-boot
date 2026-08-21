package org.example.paqueteria.paquete.Controller;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.paquete.Mapper.PaqueteMapper;
import org.example.paqueteria.paquete.Service.PaqueteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/paquetes")
@RequiredArgsConstructor
public class PaqueteController {

    private final PaqueteService paqueteService;


    @GetMapping
    public List<PaqueteDto> listarTodos() {
        return paqueteService.obtenerTodos().stream()
                .map(PaqueteMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PaqueteDto buscarPorId(@PathVariable Long id) {
        Paquete paquete = paqueteService.obtenerPorId(id);
        return PaqueteMapper.toDto(paquete);
    }

    @PostMapping("/{clienteId}")
    public PaqueteDto crear(@RequestBody PaqueteDto dto, @PathVariable Long clienteId) {
        Paquete paquete = PaqueteMapper.toEntity(dto);
        Paquete guardado = paqueteService.guardar(paquete, clienteId);
        return PaqueteMapper.toDto(guardado);
    }

    @PutMapping("/{id}/{clienteId}")
    public PaqueteDto actualizar(@PathVariable Long id, @PathVariable Long clienteId, @RequestBody PaqueteDto dto) {


        Paquete actualizado = paqueteService.actualizar(id, clienteId, dto);
        return PaqueteMapper.toDto(actualizado);


    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (paqueteService.obtenerPorId(id) != null) {
            paqueteService.borrar(id);
        }
    }
}