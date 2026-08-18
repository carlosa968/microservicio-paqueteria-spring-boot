package org.example.paqueteria.recargo.Repository;

import org.example.paqueteria.recargo.Entity.Recargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // <--- No olvides este import

@Repository
public interface RecargoRepository extends JpaRepository<Recargo, Long> {
    // Método optimizado para buscar recargo por zona sin usar findAll()
    Optional<Recargo> findByZona(String zona);
}