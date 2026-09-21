package org.example.paqueteria.cliente.Repository;

import org.example.paqueteria.cliente.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository  extends  JpaRepository<Cliente, Long>{
    // Busca por nombre o apellido que contenga el texto (ignore case)
    List<Cliente> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
    Optional<Cliente> findByEmail(String email);
}
