/*
En Spring Boot, es la pieza clave que se comunica directamente con la base de datos.
 No necesitas escribir código SQL
 ni métodos como guardar, buscar por ID o eliminar,
 porque Spring Data JPA los genera automáticamente solo con extender de JpaRepository.
 */

package org.example.paqueteria.paquete.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.paqueteria.paquete.Entity.Paquete; // importacion de la entidad par que el repositorio sepa con que tipos de dato va tabajr


@Repository// Anotacion que le indica a Spring que es esta intreface es un repositorio
// una iterface es: Es un contrato  o un lista de reglas que defin que debe hacer una clase peor sin decir ocmo hacerlo
public interface PaqueteRepository extends JpaRepository <Paquete, Long>{
    long countByClienteId(Long cliente_id);// esto es un meteodo de consulta
}
