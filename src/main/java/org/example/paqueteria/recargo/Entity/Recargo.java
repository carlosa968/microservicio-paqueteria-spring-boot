package org.example.paqueteria.recargo.Entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // ¡Esta anotación te genera automáticamente todos los Getters, Setters, toString y equals/hashCode!
@NoArgsConstructor // Te genera el constructor vacío que pide Hibernate
@AllArgsConstructor // Te genera el constructor con todos los atributos
@Entity
@Table(name="recargos")

public class Recargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Double montoRecargo ;
    private String zona ;


}
