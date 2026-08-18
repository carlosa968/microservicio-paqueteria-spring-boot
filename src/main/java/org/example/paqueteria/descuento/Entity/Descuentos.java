package org.example.paqueteria.descuento.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // ¡Esta anotación te genera automáticamente todos los Getters, Setters, toString y equals/hashCode!
@NoArgsConstructor // Te genera el constructor vacío que pide Hibernate
@AllArgsConstructor

@Entity
@Table(name = "descuentos")
public class Descuentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean esClienteFrecuente;
    private Double descuento;

}
