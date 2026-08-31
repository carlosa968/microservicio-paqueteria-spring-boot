package org.example.paqueteria.ejercicios;
import java.util.ArrayList;

public class NumeroMenor {
    public  static  void main (String[] args) {
        int[] lista = {45, 12, 89, 3, 67, 23};
        int menor = lista[0];

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] < menor) {
                menor = lista[i];
            }
        }
        System.out.println("El número mmenor de la lista es: " + menor);
    }
}
