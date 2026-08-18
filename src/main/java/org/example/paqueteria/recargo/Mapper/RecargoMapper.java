package org.example.paqueteria.recargo.Mapper;
import org.example.paqueteria.recargo.Dto.RecargoDto;
import org.example.paqueteria.recargo.Entity.Recargo;
import org.springframework.stereotype.*;
public class RecargoMapper {

    // Convierte de Entidad (Base de Datos) a DTO (lo que viaja por HTTP)
    public static RecargoDto toDto(Recargo entity) {
        if (entity == null) {
            return null;
        }

        RecargoDto dto = new RecargoDto();
        dto.setId(entity.getId());
        dto.setMontoRecargo(entity.getMontoRecargo());
        dto.setZona(entity.getZona());

        return dto;
    }

    // Convierte de DTO (lo que llega de Postman) a Entidad (para guardarla en BD)
    public static Recargo toEntity(RecargoDto dto) {
        if (dto == null) {
            return null;
        }

        Recargo entity = new Recargo();
        entity.setId(dto.getId());
        entity.setMontoRecargo(dto.getMontoRecargo());
        entity.setZona(dto.getZona());

        return entity;
    }
}
