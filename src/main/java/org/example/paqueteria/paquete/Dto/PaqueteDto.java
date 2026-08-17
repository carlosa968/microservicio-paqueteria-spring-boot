package org.example.paqueteria.paquete.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteDto {
    private Long id;
    private Double pesoKg;
    private String zonaDestino;
    private boolean esClienteFrecuente;
    private Integer distanciaKm;
    private Double costoEnvio;
    private String prioridad;
    private Long clienteId;
}