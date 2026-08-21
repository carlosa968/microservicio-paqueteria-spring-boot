package org.example.paqueteria.recargo.Controller;
import lombok.RequiredArgsConstructor;
import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.recargo.Dto.RecargoDto;
import org.example.paqueteria.recargo.Entity.Recargo;
import org.example.paqueteria.recargo.Service.RecargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recargos")
@RequiredArgsConstructor
public class RecargoController {
    private final RecargoService recargoService;



    // GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<RecargoDto>> obtenerTodos() {
        List<RecargoDto> lista = recargoService.listasTodos();
        return ResponseEntity.ok(lista);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecargoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recargoService.buscarPorId(id));
    }

    // POST: Crear un nuevo costo base
    @PostMapping
    public ResponseEntity<RecargoDto> crear(@RequestBody RecargoDto recargoDto) {
        RecargoDto nuevoRecargo = recargoService.guardar(recargoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecargo);
    }

    // PUT: Actualizar un costo base existente
    @PutMapping("/{id}")
    public ResponseEntity<RecargoDto> actualizar(@PathVariable Long id, @RequestBody RecargoDto recargoDto) {
        // El controller ya no modifica IDs ni busca nada. Solo delega al service:
        RecargoDto actualizado = recargoService.actualizar(id, recargoDto);
        return ResponseEntity.ok(actualizado);
    }


    // DELETE: Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recargoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }}
