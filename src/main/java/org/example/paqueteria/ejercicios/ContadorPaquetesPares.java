package org.example.paqueteria.ejercicios;

public class ContadorPaquetesPares {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int totalPares = 0;

        // El for recorre los índices del arreglo desde 0 hasta el tamaño total
        for(int i = 0; i < numeros.length; i++){
            // Evaluamos el número que está guardado en esa posición del arreglo
            if(numeros[i] % 2 == 0){
                totalPares++;
            }

        }

        System.out.println(totalPares + " Son los pares");
    }
}