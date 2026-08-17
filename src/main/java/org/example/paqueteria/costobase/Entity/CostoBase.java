package org.example.paqueteria.costobase.Entity;
import jakarta.persistence.*;
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
@Table(name="costosbase")

public class CostoBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Double limiteKilos;
    private Double costoBase;
    private Double costoExtra;




}
