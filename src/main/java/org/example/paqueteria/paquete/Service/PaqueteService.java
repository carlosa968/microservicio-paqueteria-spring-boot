package org.example.paqueteria.paquete.Service;

import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.costobase.Repository.CostoBaseRepository;
import org.example.paqueteria.descuento.Repository.DescuentosRepository;
import org.example.paqueteria.paquete.Repository.PaqueteRepository;
import org.example.paqueteria.recargo.Repository.RecargoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaqueteService {

    // INYECCIÓN DE TODOS LOS REPOSITORIOS NECESARIOS
    private final PaqueteRepository paqueteRepository;
    private final CostoBaseRepository costoBaseRepository;
    private final RecargoRepository recargoRepository;
    private final DescuentosRepository descuentosRepository;
    private final ClienteRepository clienteRepository; // <--- Añadido para asociar el cliente

    public PaqueteService(PaqueteRepository paqueteRepository,
                          CostoBaseRepository costoBaseRepository,
                          RecargoRepository recargoRepository,
                          DescuentosRepository descuentosRepository,
                          ClienteRepository clienteRepository) {
        this.paqueteRepository = paqueteRepository;
        this.costoBaseRepository = costoBaseRepository;
        this.recargoRepository = recargoRepository;
        this.descuentosRepository = descuentosRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Paquete> obtenerTodos() {
        return paqueteRepository.findAll();
    }

    public Paquete obtenerPorId(Long id) {
        return paqueteRepository.findById(id).orElse(null);
    }

    public void borrar(Long id) {
        paqueteRepository.deleteById(id);
    }

    // Método guardar optimizado y con relación a Cliente
    public Paquete guardar(Paquete paquete, Long clienteId) {

        // 0. Buscamos y asignamos el Cliente obligatorio
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        paquete.setCliente(cliente);

        // 1. Traemos la regla de Costo Base (asumiendo que hay un registro principal o usamos findFirst)
        var costoBaseList = costoBaseRepository.findAll();
        double costoBase = 0.0;
        double limiteKilos = 0.0;
        double costoExtra = 0.0;

        if (!costoBaseList.isEmpty()) {
            costoBase = costoBaseList.get(0).getCostoBase();
            limiteKilos = costoBaseList.get(0).getLimiteKilos();
            costoExtra = costoBaseList.get(0).getCostoExtra();
        }

        // 2. Calculamos según el peso
        double costoTotal = costoBase;
        if (paquete.getPesoKg() > limiteKilos) {
            double kilosExtras = paquete.getPesoKg() - limiteKilos;
            costoTotal += kilosExtras * costoExtra;
        }

        // 3. OPTIMIZACIÓN: Buscamos el recargo directamente por zona (Sin listar toda la tabla)
        var recargoOpt = recargoRepository.findByZona(paquete.getZonaDestino());
        if (recargoOpt.isPresent()) {
            costoTotal += recargoOpt.get().getMontoRecargo();
        }

        // 4. OPTIMIZACIÓN: Buscamos el descuento directamente por estatus de frecuencia
        var descuentoOpt = descuentosRepository.findByEsClienteFrecuente(paquete.isEsClienteFrecuente());
        if (descuentoOpt.isPresent()) {
            double porcentajeDescuento = descuentoOpt.get().getDescuento();
            if (porcentajeDescuento > 0) {
                costoTotal -= (costoTotal * porcentajeDescuento);
            }
        }

        // 5. Asignamos la prioridad según la distancia
        if (paquete.getDistanciaKm() > 500) {
            paquete.setPrioridad("Alta (Envío Express)");
        } else {
            paquete.setPrioridad("Normal");
        }

        // 6. Guardamos el costo final calculado
        paquete.setCostoEnvio(costoTotal);

        // 7. Persistimos en la BD
        return paqueteRepository.save(paquete);
    }
}