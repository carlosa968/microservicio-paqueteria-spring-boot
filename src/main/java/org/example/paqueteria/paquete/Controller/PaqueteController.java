/*
Controller:
Esta clase es la puerta de entrada HTTP Recibe las peticones (GET POST PUT DELETE)
llmaa al servicio y devulve la respuesta (normalmente un Dto ) al cliente es como el mesero que solo
toma la orden y se la lleva al la conicna que es el servico
 */

package org.example.paqueteria.paquete.Controller;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.paquete.Mapper.PaqueteMapper;
import org.example.paqueteria.paquete.Service.PaqueteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // Anotación limpia en su propia línea sireve par que una alcion externa peuda hacer peticones a mi API sin bloques d eseguridad jaja
@RestController // Esta anotacion le dice a Spring que es un controlador que la clase manejra peticones web y que devokvera datos automatiamnte convertidos en JSON
@RequestMapping("/api/paquetes")// Con esta anotacion definimos la ruta par atodo los metods de este contolador todas la peticiones aqui cominezan

@RequiredArgsConstructor
public class PaqueteController {

    private final PaqueteService paqueteService; //Inyecta el servicio para poder delegarle toda la lógica pesada y los cálculos.

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

    // CAMBIO CLAVE: Ahora acepta tanto /api/paquetes?clienteId=1 como /api/paquetes/1
    @PostMapping
    public PaqueteDto crear(@RequestBody PaqueteDto dto, @RequestParam Long clienteId) {
        Paquete paquete = PaqueteMapper.toEntity(dto);
        Paquete guardado = paqueteService.guardar(paquete, clienteId);
        return PaqueteMapper.toDto(guardado);

        /*
        Se activa con un POST. @RequestBody lee el JSON que manda el cliente y lo convierte en DTO,
         mientras que @RequestParam extrae el ID del cliente. Convierte el DTO a entidad,
         llama al servicio para calcular costos y guarda, devolviendo el resultado convertido
         otra vez en DTO.
         */
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