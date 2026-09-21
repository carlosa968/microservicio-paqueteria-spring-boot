package org.example.paqueteria.cliente.Mapper;

import org.example.paqueteria.cliente.Dto.ClienteDto;
import org.example.paqueteria.cliente.Entity.Cliente;

public class ClienteMapper {

    public static ClienteDto toDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setEmail(cliente.getEmail()); // 👈 Corrección: Del cliente hacia el DTO
        // La contraseña por seguridad NO se debe regresar nunca en el DTO hacia el frontend
        return dto;
    }

    public static Cliente toEntity(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setEmail(dto.getEmail());         // 👈 Pasamos el email
        cliente.setPassword(dto.getPassword());   // 👈 Pasamos el password (el servicio lo hasheará antes de guardar)
        return cliente;
    }
}