package org.example.paqueteria.cliente.Dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private String nombre;
    private  String apellido;
    private String telefono;
    private String direccion;
}