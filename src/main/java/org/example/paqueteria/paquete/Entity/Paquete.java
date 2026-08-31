/*
QUE ES ESTA CLASE Y APR QUE SIRVE
MODELO= ENTIDAD:
Esta es la clase que represnta una tbla de bd se declarna uss tributos o campos que tendra
 */





package org.example.paqueteria.paquete.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paqueteria.cliente.Entity.Cliente;

@Data // ¡Esta anotación te genera automáticamente todos los Getters, Setters, toString y equals/hashCode!
@NoArgsConstructor // Te genera el constructor vacío que pide Hibernate
@AllArgsConstructor // Te genera el constructor con todos los atributos
@Entity // anotacion para que Spirng sepa que es un modelo
@Table(name = "paquetes") // nombre de la tabla
public class Paquete {

    @Id // antoacion que deifne el campo como llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// este es para generar un id autoicrementable
    private Long id;  // campo is
    @ManyToOne(fetch = FetchType.LAZY) // relcion de muhcoo a uno
    @JoinColumn(name = "cliente_id")// relaicon con la tbal lciente mendinte el id cliente
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // <--- ¡Esto es clave!
    private Cliente cliente;

    private Double pesoKg;
    private String zonaDestino = "";
    //private boolean esClienteFrecuente;
    private Integer distanciaKm = 0;
    private Double costoEnvio;
    private String prioridad = "";

    // ¡Adiós a todos los getters, setters y constructores manuales!
    // Lombok se encarga de ellos por debajo al compilar.
}