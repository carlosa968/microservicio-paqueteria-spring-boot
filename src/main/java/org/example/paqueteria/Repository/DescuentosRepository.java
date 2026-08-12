package org.example.paqueteria.Repository;

import org.example.paqueteria.Entity.Descuentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescuentosRepository extends JpaRepository<Descuentos, Long> {
}