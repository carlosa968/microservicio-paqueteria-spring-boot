package org.example.paqueteria.ejercicios;

public class ValidarAcceso {
    public static void main(String[] args) {
        // 1. Entradas (puedes cambiar estos valores para hacer pruebas)
        int edad = 17;
        boolean tieneVip = true;
        boolean tieneBoletoDorado = false;

        // 2. Variables bandera dinámicas (calculan el resultado por ti)
        boolean esAdulto = (edad >= 18);
        boolean paseValido = (tieneVip || tieneBoletoDorado);

        // 3. El if limpio y elegante usando la Estrategia 1
        if (esAdulto && paseValido) {
            System.out.println("¡Acceso concedido! Bienvenido al evento.");
        } else {
            System.out.println("Acceso denegado: No cumples con la edad mínima o te falta un pase válido.");
        }
    }
}