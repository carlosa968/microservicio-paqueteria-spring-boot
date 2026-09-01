package org.example.paqueteria.paquete.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND) // <-- Esto le avisa a Spring que mande un 404
public class PaqueteNoEncontradoException extends  RuntimeException {
    public PaqueteNoEncontradoException(String mensaje){
        super(mensaje);

    }
}
