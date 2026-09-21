package org.example.paqueteria.paquete.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaqueteDto {

    private Long id;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor que 0")
    private Double pesoKg;

    @NotBlank(message = "La zona de destino es obligatoria")
    @Size(max = 100, message = "La zona de destino no puede superar los 100 caracteres")
    private String zonaDestino;

    @NotNull(message = "La distancia es obligatoria")
    @Positive(message = "La distancia debe ser mayor que 0")
    private Integer distanciaKm;

    private Double costoEnvio;

    @NotBlank(message = "La prioridad es obligatoria")
    private String prioridad;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;
}
