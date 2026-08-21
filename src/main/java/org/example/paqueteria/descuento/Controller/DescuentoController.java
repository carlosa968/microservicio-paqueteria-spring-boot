package org.example.paqueteria.descuento.Controller;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.costobase.Service.CostoBaseService;
import org.example.paqueteria.descuento.Dto.DescuentoDto;
import org.example.paqueteria.descuento.Service.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
@RequiredArgsConstructor

public class DescuentoController {

    private final DescuentoService descuentoService;


    // GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<DescuentoDto>> obtenerTodos() {
        List<DescuentoDto> lista = descuentoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<DescuentoDto> obtenerPorId(@PathVariable Long id) {
        DescuentoDto dto = descuentoService.buscarPorId(id);
        return ResponseEntity.ok(ResponseEntity.ok(dto).getBody()); // Simplificable, pero directo
    }

    // POST: Crear un nuevo costo base
    @PostMapping
    public ResponseEntity<DescuentoDto> crear(@RequestBody DescuentoDto descuentoDto) {
        DescuentoDto nuevoDescuento = descuentoService.guardar(descuentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDescuento);
    }

    // PUT: Actualizar un costo base existente
    @PutMapping("/{id}")
    public ResponseEntity<DescuentoDto> actualizar(@PathVariable Long id, @RequestBody DescuentoDto descuentoDto) {
        DescuentoDto actualizado = descuentoService.actualizar(id, descuentoDto);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descuentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
