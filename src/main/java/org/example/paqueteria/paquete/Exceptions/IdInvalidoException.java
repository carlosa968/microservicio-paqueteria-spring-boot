package org.example.paqueteria.paquete.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdInvalidoException extends RuntimeException {
  public IdInvalidoException(String mensaje) {
    super(mensaje);
  }
}