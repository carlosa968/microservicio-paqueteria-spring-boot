package org.example.paqueteria.descuento.Mapper;

import org.example.paqueteria.descuento.Dto.DescuentoDto;
import org.example.paqueteria.descuento.Entity.Descuentos;

public class DescuentoMapper {

    // Convierte de Entidad a DTO (para mandárselo al cliente en las respuestas)
    public static DescuentoDto toDto(Descuentos entidad) {
        if (entidad == null) {
            return null;
        }
        DescuentoDto dto = new DescuentoDto();
        dto.setId(entidad.getId());
        dto.setEsClienteFrecuente(entidad.isEsClienteFrecuente());
        dto.setDescuento(entidad.getDescuento());
        return dto;
    }

    // Convierte de DTO a Entidad (para cuando guardes o actualices desde Postman)
    public static Descuentos toEntity(DescuentoDto dto) {
        if (dto == null) {
            return null;
        }
        Descuentos entidad = new Descuentos();
        entidad.setId(dto.getId());
        entidad.setEsClienteFrecuente(dto.isEsClienteFrecuente());
        entidad.setDescuento(dto.getDescuento());
        return entidad;
    }
}