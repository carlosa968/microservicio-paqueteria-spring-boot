package org.example.paqueteria.paquete.Mapper;

import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;

public class PaqueteMapper {

    /*
     * Convierte una Entidad Paquete en un PaqueteDto.
     *
     * Se utiliza principalmente cuando obtenemos información
     * de la base de datos y queremos devolverla al cliente.
     */
    public static PaqueteDto toDto(Paquete paquete) {

        if (paquete == null) {
            return null;
        }

        PaqueteDto dto = new PaqueteDto();

        dto.setId(paquete.getId());
        dto.setPesoKg(paquete.getPesoKg());
        dto.setZonaDestino(paquete.getZonaDestino());
        dto.setDistanciaKm(paquete.getDistanciaKm());
        dto.setCostoEnvio(paquete.getCostoEnvio());
        dto.setPrioridad(paquete.getPrioridad());

        /*
         * La Entidad tiene un objeto Cliente,
         * mientras que el DTO solamente guarda el ID del cliente.
         */
        if (paquete.getCliente() != null) {
            dto.setClienteId(paquete.getCliente().getId());
        }

        return dto;
    }

    /*
     * Convierte un PaqueteDto en una Entidad Paquete.
     *
     * Se utiliza principalmente cuando recibimos información
     * del cliente y necesitamos convertirla en una Entidad
     * para trabajar con ella.
     */
    public static Paquete toEntity(PaqueteDto dto) {

        if (dto == null) {
            return null;
        }

        Paquete paquete = new Paquete();

        paquete.setId(dto.getId());
        paquete.setPesoKg(dto.getPesoKg());
        paquete.setZonaDestino(dto.getZonaDestino());
        paquete.setDistanciaKm(dto.getDistanciaKm());
        paquete.setCostoEnvio(dto.getCostoEnvio());
        paquete.setPrioridad(dto.getPrioridad());

        /*
         * clienteId NO se convierte aquí en Cliente.
         *
         * El Mapper solamente transforma datos.
         * La búsqueda del Cliente por su ID debe hacerse
         * posteriormente en el Service.
         */

        return paquete;
    }
}