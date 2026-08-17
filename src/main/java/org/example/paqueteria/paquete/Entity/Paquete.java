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
@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // <--- ¡Esto es clave!
    private Cliente cliente;

    private Double pesoKg;
    private String zonaDestino = "";
    private boolean esClienteFrecuente;
    private Integer distanciaKm = 0;
    private Double costoEnvio;
    private String prioridad = "";

    // ¡Adiós a todos los getters, setters y constructores manuales!
    // Lombok se encarga de ellos por debajo al compilar.
}