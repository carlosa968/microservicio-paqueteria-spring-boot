package org.example.paqueteria.costobase.Repository;

import org.example.paqueteria.costobase.Entity.CostoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoBaseRepository extends JpaRepository<CostoBase, Long> {
}