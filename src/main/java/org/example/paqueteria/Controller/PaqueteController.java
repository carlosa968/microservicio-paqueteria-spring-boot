package org.example.paqueteria.Controller;
import org.example.paqueteria.Entity.Paquete;
import org.example.paqueteria.Service.PaqueteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;
    public PaqueteController(PaqueteService paqueteService){
        this.paqueteService = paqueteService;
    }

    //Endpoints

    //get para ver todos los paquetes ok
    @GetMapping
    public List <Paquete> lista(){
        return paqueteService.obtenerTodos();
    }
    @GetMapping("/{id}")
    public Paquete buscarPorId(@PathVariable Long id ){
        return paqueteService.obtenerPorId(id);
    }
    @PostMapping
    public Paquete crear(@RequestBody Paquete paquete){
        return  paqueteService.guardar(paquete);
    }
    @PutMapping("/{id}")
    public Paquete actualizar(@PathVariable Long id, @RequestBody Paquete paqueteDetalles){
        Paquete paqueteExistente = paqueteService.obtenerPorId(id);
        if(paqueteExistente != null){
            paqueteExistente.setPesoKg(paqueteDetalles.getPesoKg());
            paqueteExistente.setZonaDestino(paqueteDetalles.getZonaDestino());
            paqueteExistente.setEsClienteFrecuente(paqueteDetalles.getEsClienteFrecuente());
            paqueteExistente.setDistanciaKm(paqueteDetalles.getDistanciaKm());

            return paqueteService.guardar(paqueteExistente);

        }

        return null;



    }
    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id ){
        if(paqueteService.obtenerPorId(id) != null) {
            paqueteService.borrar(id);
        }    }
}
