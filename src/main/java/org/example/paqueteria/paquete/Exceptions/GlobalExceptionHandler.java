package org.example.paqueteria.paquete.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaqueteNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(PaqueteNoEncontradoException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("error", ex.getMessage());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND); // Devuelve HTTP 404
    }
}