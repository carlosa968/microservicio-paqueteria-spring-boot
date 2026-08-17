package org.example.paqueteria.cliente.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paqueteria.paquete.Entity.Paquete;

import java.util.List;

@Data // ¡Esta anotación te genera automáticamente todos los Getters, Setters, toString y equals/hashCode!
@NoArgsConstructor // Te genera el consatructor vacío que pide Hibernate
@AllArgsConstructor // Te genera el constructor con todos los atributos
@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long id;
    // En tu clase Cliente.java
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore // <--- ¡Añade esta anotación!
    private List<Paquete> paquetes;
    private String nombre;
    private String telefono;
    private String direccion;
}
