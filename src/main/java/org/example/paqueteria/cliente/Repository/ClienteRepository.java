package org.example.paqueteria.cliente.Repository;

import org.example.paqueteria.cliente.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface ClienteRepository  extends  JpaRepository<Cliente, Long>{
}
