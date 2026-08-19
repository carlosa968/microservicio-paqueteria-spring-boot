package org.example.paqueteria.paquete.Controller;

import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.paquete.Mapper.PaqueteMapper;
import org.example.paqueteria.paquete.Service.PaqueteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

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
        Paquete paqueteExistente = paqueteService.obtenerPorId(id);
        if (paqueteExistente != null) {
            paqueteExistente.setPesoKg(dto.getPesoKg());
            paqueteExistente.setZonaDestino(dto.getZonaDestino());
            //paqueteExistente.setEsClienteFrecuente(dto.isEsClienteFrecuente());
            paqueteExistente.setDistanciaKm(dto.getDistanciaKm());

            Paquete actualizado = paqueteService.guardar(paqueteExistente, clienteId);
            return PaqueteMapper.toDto(actualizado);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (paqueteService.obtenerPorId(id) != null) {
            paqueteService.borrar(id);
        }
    }
}