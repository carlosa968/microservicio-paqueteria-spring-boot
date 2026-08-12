package org.example.paqueteria.Service;

import org.example.paqueteria.Entity.Paquete;
import  org.example.paqueteria.Repository.PaqueteRepository;
import  org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PaqueteService {

    //INYECCION
    private final PaqueteRepository paqueteRepository;
    public PaqueteService(PaqueteRepository paqueteRepository){
        this.paqueteRepository =paqueteRepository;
    }

    //metodos
    public List<Paquete> obtenerTodos() {
        return paqueteRepository.findAll();
    }

    public Paquete obtenerPorId(Long id){
        return  paqueteRepository.findById(id).orElse(null);
    }


    public void borrar (Long id ){
        paqueteRepository.deleteById(id);
    }









///////////////////////////////////////////////////////////////////////
    public Paquete guardar(Paquete paquete){
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }

        double costoEnvio = 0;
        double pesoExtra = 0;
        String prioridad = "";
         if(paquete.getPesoKg() <= 5){
             costoEnvio = 50.0;
         }else{
             pesoExtra =(paquete.getPesoKg()-5)*10;
             costoEnvio= 50.0 + pesoExtra;
         }
        if("Internacional".equalsIgnoreCase(paquete.getZonaDestino())){
         //if(paquete.getZonaDestino().equalsIgnoreCase("Internacional")){
             costoEnvio = costoEnvio + 200.0;
             //costoEnvio += 200.0;

         }

         if(paquete.getEsClienteFrecuente()){
             costoEnvio = costoEnvio - (costoEnvio * 0.15);
             // costoEnvio -= (costoEnvio * 0.15);
         }

         if(paquete.getDistanciaKm() > 500){
                    prioridad  = "Alta(EnIVISO Express)";
         }else {
                prioridad = "Normal";
         }


        paquete.setCostoEnvio(costoEnvio);
        paquete.setPrioridad(prioridad);
        return  paqueteRepository.save(paquete);
    }
}
