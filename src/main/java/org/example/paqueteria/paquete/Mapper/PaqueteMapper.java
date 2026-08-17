package org.example.paqueteria.paquete.Mapper;

import org.example.paqueteria.paquete.Dto.PaqueteDto;
import org.example.paqueteria.paquete.Entity.Paquete;

public class PaqueteMapper {

    public static PaqueteDto toDto(Paquete paquete) {
        if (paquete == null) {
            return null;
        }

        PaqueteDto dto = new PaqueteDto();
        dto.setId(paquete.getId());
        dto.setPesoKg(paquete.getPesoKg());
        dto.setZonaDestino(paquete.getZonaDestino());
        dto.setEsClienteFrecuente(paquete.isEsClienteFrecuente());
        dto.setDistanciaKm(paquete.getDistanciaKm());
        dto.setCostoEnvio(paquete.getCostoEnvio());
        dto.setPrioridad(paquete.getPrioridad());

        if (paquete.getCliente() != null) {
            dto.setClienteId(paquete.getCliente().getId());
        }

        return dto;
    }

    public static Paquete toEntity(PaqueteDto dto) {
        if (dto == null) {
            return null;
        }

        Paquete paquete = new Paquete();
        paquete.setId(dto.getId());
        paquete.setPesoKg(dto.getPesoKg());
        paquete.setZonaDestino(dto.getZonaDestino());
        paquete.setEsClienteFrecuente(dto.isEsClienteFrecuente());
        paquete.setDistanciaKm(dto.getDistanciaKm());
        paquete.setCostoEnvio(dto.getCostoEnvio());
        paquete.setPrioridad(dto.getPrioridad());

        return paquete;
    }
}