package org.example.paqueteria.Repository;

import org.example.paqueteria.Entity.Recargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecargoRepository extends JpaRepository<Recargo, Long> {
}