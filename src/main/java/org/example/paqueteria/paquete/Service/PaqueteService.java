/*
Service:
Aqui es donde viene el cerebro del servicio aqui se aplica la logica la reglas de negocio este usa el
repository para leer guardar consutlar datos y el Mapper para convertir
 */

package org.example.paqueteria.paquete.Service;

import lombok.RequiredArgsConstructor;
import org.example.paqueteria.cliente.Entity.Cliente;
import org.example.paqueteria.cliente.Repository.ClienteRepository;
import org.example.paqueteria.costobase.Entity.CostoBase;
import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;
import org.example.paqueteria.costobase.Repository.CostoBaseRepository;
import org.example.paqueteria.descuento.Repository.DescuentosRepository;
import org.example.paqueteria.paquete.Exceptions.PaqueteNoEncontradoException;
import org.example.paqueteria.paquete.Repository.PaqueteRepository;
import org.example.paqueteria.recargo.Repository.RecargoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // anotacion que le dice a Spring que es un componete de servico
@RequiredArgsConstructor

public class PaqueteService {

    // INYECCIÓN DE TODOS LOS REPOSITORIOS NECESARIOS estoe  sppr constructro no por autowired
    private final PaqueteRepository paqueteRepository;
    private final CostoBaseRepository costoBaseRepository;
    private final RecargoRepository recargoRepository;
    private final DescuentosRepository descuentosRepository;
    private final ClienteRepository clienteRepository; // <--- Añadido para asociar el cliente
/*
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
*/
    public List<Paquete> obtenerTodos() {
        return paqueteRepository.findAll();
    }

    public Paquete obtenerPorId(Long id) {

        return paqueteRepository.findById(id).
                orElseThrow(() -> new PaqueteNoEncontradoException("ID de paquete no encontrado..."));
    }

    public void borrar(Long id) {

        if (!paqueteRepository.existsById(id)) {
            throw new PaqueteNoEncontradoException("No se puede eliminar, el paquete con el ID: " + id + "no existe.");
        }paqueteRepository.deleteById(id);
    }

    public Paquete actualizar(Long id, Long clienteId, PaqueteDto dto) {
        // 1. Validar si el paquete existe en la base de datos (¡la lógica vive aquí, no en el controller!)
        Paquete paqueteExistente = paqueteRepository.findById(id)
                .orElseThrow(() -> new PaqueteNoEncontradoException("Paquete no encontrado con ID: " + id));

        // 2. Modificar los campos con los nuevos valores que vienen del DTO
        paqueteExistente.setPesoKg(dto.getPesoKg());
        paqueteExistente.setZonaDestino(dto.getZonaDestino());
        paqueteExistente.setDistanciaKm(dto.getDistanciaKm());

        // 3. Reutilizamos tu método guardar para que recalcule costos, recargos, distancias y todo lo necesario
        return guardar(paqueteExistente, clienteId);
    }

    // Método guardar optimizado y con relación a Cliente
    public Paquete guardar(Paquete paquete, Long clienteId) {

        // 0. Buscamos y asignamos el Cliente obligatorio
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        paquete.setCliente(cliente);

        // 1. Traemos la regla de Costo Base con Optional
        CostoBase costo1 = costoBaseRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("No se encontró la configuración del costo base"));

        double costoBase = costo1.getCostoBase();
        double limiteKilos = costo1.getLimiteKilos();
        double costoExtra = costo1.getCostoExtra();

        // 2. OBSERVACIÓN 3: Calculamos el descuento de cliente frecuente SOBRE EL COSTO BASE ORIGINAL antes de sumar recargos
        double descuento = 0.0;
        long totalEnvios = paqueteRepository.countByClienteId(clienteId);
        boolean esClienteFrecuente = (totalEnvios >= 2);
        /*

        double descuento = 0.0;
        long totalEnvios = paqueteRepository.countByClienteId(clienteId);
        boolean esClienteFrecuente = (totalEnvios >= 2);

        // Usando programación funcional de Java 21:
        descuento = descuentosRepository.findByEsClienteFrecuente(esClienteFrecuente)
        .map(d -> costoBase * d.getDescuento()) // Si lo encuentra, multiplica de inmediato
        .orElse(0.0); // Si no encuentra nada, regresa 0.0 por defecto
         */
        var descuentoOpt = descuentosRepository.findByEsClienteFrecuente(esClienteFrecuente);
        if (descuentoOpt.isPresent()) {
            double porcentajeDescuento = descuentoOpt.get().getDescuento();
            if (porcentajeDescuento > 0) {
                descuento = costoBase * porcentajeDescuento;
            }
        }

        // 3. Iniciamos el cálculo del costo total con el costo base
        double costoTotal = costoBase;

        // 4. Sumamos los kilos extras si aplica
        if (paquete.getPesoKg() > limiteKilos) {
            double kilosExtras = paquete.getPesoKg() - limiteKilos;
            costoTotal += kilosExtras * costoExtra;
        }

        // 5. Sumamos el recargo por zona
        var recargoOpt = recargoRepository.findByZona(paquete.getZonaDestino());
        if (recargoOpt.isPresent()) {
            costoTotal += recargoOpt.get().getMontoRecargo();
        }

        // 6. Restamos el descuento que calculamos al inicio de forma limpia
        costoTotal -= descuento;

        // 7. Asignamos la prioridad según la distancia
        if (paquete.getDistanciaKm() > 500) {
            paquete.setPrioridad("Alta (Envío Express)");
        } else {
            paquete.setPrioridad("Normal");
        }

        // 8. Guardamos el costo final ya con todo bien calculado
        paquete.setCostoEnvio(costoTotal);

        // 9. Persistimos en la BD
        return paqueteRepository.save(paquete);
    }
}