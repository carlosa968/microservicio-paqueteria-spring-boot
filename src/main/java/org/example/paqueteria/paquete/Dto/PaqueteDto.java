/*
Dto : Data traferent object
Esta clase es especialemtne de traspotrat lo datos necesarios  define que datos viajan hacia el cliente y desde el cliente (API)
este nos ayuda a no exponer nuestrea entidad completa ni su estructura interna en base de datos

su unico trabajo es transportar datos entre el controller y el cliente que hacela epticion jaja
 */

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
    //private boolean esClienteFrecuente;
    private Integer distanciaKm;
    private Double costoEnvio;
    private String prioridad;
    private Long clienteId;
}