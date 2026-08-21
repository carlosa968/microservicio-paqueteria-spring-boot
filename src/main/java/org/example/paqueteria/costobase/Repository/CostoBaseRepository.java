package org.example.paqueteria.costobase.Repository;

import org.example.paqueteria.costobase.Entity.CostoBase;
import org.example.paqueteria.descuento.Entity.Descuentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostoBaseRepository extends JpaRepository<CostoBase, Long> {
    Optional<CostoBase> findTopByOrderByIdAsc();

}