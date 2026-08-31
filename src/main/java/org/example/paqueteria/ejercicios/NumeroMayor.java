package org.example.paqueteria.ejercicios;
import java.util.ArrayList;

public class NumeroMayor {
    public  static  void main (String[] args) {
        int[] lista = {34, 213, 53, 53, 235, 634, 235, 634,};
        int mayor = lista[0];

        for (int i = 0; i < lista.length; i++) {
            if (lista[i] > mayor) {
                mayor = lista[i];
            }
        }
        System.out.println("El número mayor de la lista es: " + mayor);
    }
}
