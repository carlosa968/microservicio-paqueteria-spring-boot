package org.example.paqueteria.paquete.Repository;

import org.example.paqueteria.paquete.Entity.Descuentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // <--- No olvides este import

@Repository
public interface DescuentosRepository extends JpaRepository<Descuentos, Long> {
    // Método optimizado para buscar descuento sin usar findAll()
    Optional<Descuentos> findByEsClienteFrecuente(Boolean esClienteFrecuente);
}