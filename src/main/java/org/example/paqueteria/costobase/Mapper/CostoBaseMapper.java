package org.example.paqueteria.costobase.Mapper;

import org.example.paqueteria.costobase.Dto.CostoBaseDto;
import org.example.paqueteria.costobase.Entity.CostoBase;
import org.springframework.stereotype.*;

public class CostoBaseMapper {

    // Convierte de Entidad (Base de Datos) a DTO (lo que viaja por HTTP)
    public static CostoBaseDto toDto(CostoBase entity) {
        if (entity == null) {
            return null;
        }

        CostoBaseDto dto = new CostoBaseDto();
        dto.setId(entity.getId());
        dto.setCostoBase(entity.getCostoBase());
        dto.setLimiteKilos(entity.getLimiteKilos());
        dto.setCostoExtra(entity.getCostoExtra());

        return dto;
    }

    // Convierte de DTO (lo que llega de Postman) a Entidad (para guardarla en BD)
    public static CostoBase toEntity(CostoBaseDto dto) {
        if (dto == null) {
            return null;
        }

        CostoBase entity = new CostoBase();
        entity.setId(dto.getId());
        entity.setCostoBase(dto.getCostoBase());
        entity.setLimiteKilos(dto.getLimiteKilos());
        entity.setCostoExtra(dto.getCostoExtra());

        return entity;
    }
}