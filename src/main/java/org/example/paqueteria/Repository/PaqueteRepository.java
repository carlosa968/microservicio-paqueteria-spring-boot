package org.example.paqueteria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.paqueteria.Entity.Paquete; // importacion de la entidad par que el repositorio sepa con que tipos de dato va tabajr


@Repository
public interface PaqueteRepository extends JpaRepository <Paquete, Long>{
}
