package org.example.paqueteria.costobase.Dto;

import lombok.Data;

@Data
public class CostoBaseDto {
    private Long id;
    private Double costoFijo;      // El costo inicial
    private Double limiteKilos;    // Kilos base incluidos
    private Double costoPorKiloExtra; // Cuánto cuesta pasarse del límite
}