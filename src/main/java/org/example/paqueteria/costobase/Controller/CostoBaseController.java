package org.example.paqueteria.costobase.Controller;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.costobase.Service.CostoBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costos-base")
@RequiredArgsConstructor

public class CostoBaseController {

    private final CostoBaseService costoBaseService;


    // GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<CostoBaseDto>> obtenerTodos() {
        List<CostoBaseDto> lista = costoBaseService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<CostoBaseDto> obtenerPorId(@PathVariable Long id) {
        CostoBaseDto dto = costoBaseService.buscarPorId(id);
        return ResponseEntity.ok(costoBaseService.buscarPorId(id));    }

    // POST: Crear un nuevo costo base
    @PostMapping
    public ResponseEntity<CostoBaseDto> crear(@RequestBody CostoBaseDto costoBaseDto) {
        CostoBaseDto nuevoCosto = costoBaseService.guardar(costoBaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCosto);
    }

    // PUT: Actualizar un costo base existente
    @PutMapping("/{id}")
    public ResponseEntity<CostoBaseDto> actualizar(@PathVariable Long id, @RequestBody CostoBaseDto costoBaseDto) {
        CostoBaseDto actualizado = costoBaseService.actualizar(id, costoBaseDto);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE: Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        costoBaseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}